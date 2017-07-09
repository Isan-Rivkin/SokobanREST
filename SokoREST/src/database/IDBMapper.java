package database;

import java.util.List;

public interface IDBMapper 
{
	public List<HighScoreP> searchHighScore(IQuery query);
	public List<SolutionP> searchSolutions(ISolutionQuery query);
	public List<AdminP> searchAdmins(IAdminQuery query);
	public List<LevelP> getAllLevels();
	public List<PlayerP> getAllPlayers();
	public List<HighScoreP> getAllHighScores();
	public List<SolutionP> getAllSolutions();
	public List<AdminP> getAllAdmins();
	public boolean savePOJO(POJO pojo);
	public boolean deletePOJO(POJO pojo);
	List<? extends POJO> getAllrows(String tableName);
	public POJO isEntityExist(POJO pojo);	
	public boolean updateSolution(SolutionP solution);
	public boolean updateAdmin(AdminP admin);

}
