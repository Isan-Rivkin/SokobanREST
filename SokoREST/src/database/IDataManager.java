package database;

import java.util.List;

public interface IDataManager
{
	public List<HighScoreP> search(String classify, String lName,String pName,String orderType);
	public void signUpHighScore(String pName, String lName, Integer currentSteps, Long currentTime);
	public PlayerP signUpUser(String pName);
	public boolean deleteQuery(POJO idPOJO,String ... delete);
	public void save(POJO line);
	public List<HighScoreP> getCurrentHighScoreList();
}
