package database;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class SokoDBMapper implements IDBMapper 
{
	final private static String PLAYERS_TABLE="Players";
	final private static String LEVELS_TABLE="Levels";
	final private static String HIGHSCORE_TABLE="HighScores";
	final private static String SOLUTION_TABLE ="Solutions";
	final private static String ADMIN_TABLE = "AdminsTable";
	private SessionFactory factory;
	public SokoDBMapper() 
	{
		Configuration config = new Configuration();
		config.configure();
		factory = config.buildSessionFactory();
	}
	@Override
	public List<AdminP> searchAdmins(IAdminQuery query) 
	{
		Session session = factory.openSession();
		SolutionP[] sol = null;
		Query q = null;
		if(query.getAdminName() != null)
		{
			q = session.createQuery("From " +ADMIN_TABLE + " adm WHERE adm.adminName LIKE :adminName ");
			q.setParameter("adminName", query.getAdminName());
		}
		else
		{
			return null;
		}
		q.setMaxResults(query.getMaxResults());
		List<AdminP> list  = (List<AdminP>)q.list();
		return list;
	}
	
	@Override
	public List<SolutionP> searchSolutions(ISolutionQuery query) 
	{
		Session session = factory.openSession();
		SolutionP[] sol = null;
		Query q = null;
		if(query.getLevelMap() != null)
		{
			q = session.createQuery("From " +SOLUTION_TABLE + " sol WHERE sol.levelMap LIKE :levelMap "
					+"ORDER BY sol." + query.getOrderBy());
			q.setParameter("levelMap", query.getLevelMap());
		}
		else
		{
			return null;
		}
		q.setMaxResults(query.getMaxResults());
		List<SolutionP> list  = (List<SolutionP>)q.list();
		return list;
	}


	@Override
	public List<HighScoreP> searchHighScore(IQuery query)
	{
		Session session=factory.openSession(); 
		HighScoreP[] res = null;
		Query q=null;
		if(query.getPlayerName() == null && query.getLevelName() != null)
		{
			q = session.createQuery("From " +HIGHSCORE_TABLE + " hs WHERE hs.levelName LIKE :levelName "
					+"ORDER BY hs." + query.getOrderBy());
			q.setParameter("levelName", query.getLevelName());
		}
		else if(query.getLevelName() == null && query.getPlayerName() != null)
		{
			 q = session.createQuery("From "+HIGHSCORE_TABLE+" hs WHERE "
			+ "hs.playerName LIKE :playerName ORDER BY hs."
			+(query.getOrderBy()));
			q.setParameter("playerName", "%"+query.getPlayerName()+"%");
		}
		else
		{
			 q = session.createQuery("From "+HIGHSCORE_TABLE+" hs WHERE hs.levelName LIKE :levelName AND "
			+ "hs.playerName LIKE :playerName ORDER BY hs."
			+(query.getOrderBy()));
			 q.setParameter("playerName", "%"+query.getPlayerName()+"%");
			 q.setParameter("levelName", "%"+query.getLevelName()+"%");
		}
		
		q.setMaxResults(query.getMaxResults());
		List<HighScoreP> list  = (List<HighScoreP>)q.list();
		return list;
	}

	@Override
	public List<LevelP> getAllLevels()
	{
		return (List<LevelP>) this.getAllrows(LEVELS_TABLE);
	}

	@Override
	public List<PlayerP> getAllPlayers()
	{
		return (List<PlayerP>) this.getAllrows(PLAYERS_TABLE);
	}

	@Override
	public List<HighScoreP> getAllHighScores()
	{
		return (List<HighScoreP>) this.getAllrows(HIGHSCORE_TABLE);
	}
	@Override
	public List<SolutionP> getAllSolutions() 
	{
		return (List<SolutionP>) this.getAllrows(SOLUTION_TABLE);
	}
	@Override
	public List<AdminP> getAllAdmins() 
	{
		return (List<AdminP>) this.getAllrows(ADMIN_TABLE);
	}	
	@Override
	public synchronized boolean savePOJO(POJO pojo)
	{
		Session session=null;
		Transaction tx=null;
		boolean worked = true;
		try
		{
			
			session=factory.openSession();
			tx=session.beginTransaction();
			session.save(pojo);
			tx.commit();
		}
		catch(HibernateException e)
		{
			if(tx != null)
			{
				tx.rollback();
				worked = false;
			}
		}
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
		return worked;
	}

	
	@Override
	public synchronized boolean deletePOJO(POJO pojo) 
	{
		Transaction tx = null;
		boolean worked = true;
		Session session = null;
		worked =false ;
		if(isEntityExist(pojo) != null)
		{
			worked = true;
		}
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			if (worked || pojo instanceof HighScoreP)
			{
			session.delete(pojo);
			tx.commit();
			worked=true;
			}
			else 
			{
				worked = false;
			}
		}
		catch(HibernateException he)
		{
			if(tx!=null)
			{
			tx.rollback();
			}
			worked = false;
		}
		finally
		{
			if (session!=null)
			{
			      session.close();
			}
		}
		
		return worked;
	}

	@Override
	public POJO isEntityExist(POJO pojo) 
	{
	
		boolean flag=false;
		Session session=null;
		Transaction tx=null;
		POJO pp=null;
		try
		{
			session=factory.openSession();
			tx=session.beginTransaction();
			if(pojo instanceof HighScoreP)
			{
				System.out.println("[SokoMapper]: Cannot search HighScoreP - id is not String");
				return null;
			}
				pp= session.get(pojo.getClass(),pojo.getName());

			if(pp != null)
			{
				flag=true;
			}
		}
		catch(HibernateException e)
		{
			if(tx!= null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
		return pp;
	}

	@Override
	public List<? extends POJO> getAllrows(String tableName)
	{
		Transaction tx=null;
		Session session = null;
		List<POJO> res = null;
		try
		{
		session=factory.openSession(); 
	     tx=session.beginTransaction();
		Query q = session.createQuery("from "+tableName+" p");
		res  = q.list();
		tx.commit();
	}
	catch(HibernateException e)
	{
		if(tx != null)
		{
			tx.rollback();
		}
	}
	finally
	{
		if(session != null)
		{
			session.close();
		}
	}
		return res;
	}
	@Override
	public synchronized boolean updateSolution(SolutionP solution)
	{ 
		String hql = "UPDATE "+SOLUTION_TABLE+" set levelMinSteps = :levelMinSteps "  + 
	             ",levelSolution = :levelSolution "+"WHERE levelMap = :levelMap";
		Session session = factory.openSession();
		session.getTransaction().begin();
		Query query = session.createQuery(hql);
		query.setParameter("levelMinSteps", solution.getLevelMinSteps());
		query.setParameter("levelMap", solution.getLevelMap());
		query.setParameter("levelSolution", solution.getLevelSolution());
		int result = query.executeUpdate();
		session.getTransaction().commit();
		if(result ==1)
		{
			return true;
		}
		else
		{
			return false;		
		}
	}
	@Override
	public synchronized boolean updateAdmin(AdminP admin) 
	{
		String hql = "UPDATE "+ADMIN_TABLE+" set password = :password "  + 
	             ",authorithy = :authorithy "+"WHERE adminName = :adminName";
		Session session = factory.openSession();
		session.getTransaction().begin();
		Query query = session.createQuery(hql);
		query.setParameter("password", admin.getPassword());
		query.setParameter("adminName",admin.getAdminName());
		query.setParameter("authorithy", admin.getAuthority());
		int result = query.executeUpdate();
		session.getTransaction().commit();
		if(result ==1)
		{
			return true;
		}
		else
		{
			return false;		
		}
	}
	

}
