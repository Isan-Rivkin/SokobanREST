package database;
/**
 * A Leve solution query class.
 * @author Isan Rivkin and Daniel Hake.
 *
 */
public class SolutionQuery implements ISolutionQuery 
{
	private String levelMap;
	private String levelSolution;
	private Integer minSteps;
	private int maxResults;
	private String orderBy;
	private boolean desc;
	
	public SolutionQuery() 
	{
		levelMap=null;
		levelSolution=null;
		minSteps=null;
		orderBy="levelMinSteps asc";
		maxResults=10;
	}
	@Override
	public String getLevelMap() 
	{
		return levelMap;
	}

	@Override
	public Integer getMinSteps() 
	{
		return minSteps;
	}

	@Override
	public String getSolution()
	{
		return levelSolution;
	}

	@Override
	public String getOrderBy() 
	{
		return orderBy;
	}

	@Override
	public void setLevelMap(String levelMap)
	{
		this.levelMap=levelMap;
	}

	@Override
	public void setLevelSolution(String Solution) 
	{
		this.levelSolution=Solution;
	}

	@Override
	public void setMaxResults(int max)
	{
		this.maxResults=max;
	}

	@Override
	
	public int getMaxResults() 
	{
		return maxResults;
	}

	@Override
	public boolean isDESC()
	{
		return desc;
	}

	@Override
	public void setDESC(boolean desc) 
	{
		this.desc=desc;
	}

}
