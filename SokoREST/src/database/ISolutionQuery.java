package database;

public interface ISolutionQuery 
{
	public String getLevelMap();
	public Integer getMinSteps();
	public String getSolution();
	public String getOrderBy();
	public void setLevelMap(String levelMap);
	public void setLevelSolution(String Solution);
	public void setMaxResults(int max);
	public int getMaxResults();
	public boolean isDESC();
	public void setDESC(boolean desc);
}
