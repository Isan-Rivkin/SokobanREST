package database;

public interface IAdminQuery
{
	public String getAdminName();
	public String getPassword();
	public Integer getAuthorithy();
	public void setAdminName(String adminName);
	public void setPassword(String password);
	public void setAuthorithy(Integer authorithy);
	public void setMaxResults(int max);
	public int getMaxResults();
}
