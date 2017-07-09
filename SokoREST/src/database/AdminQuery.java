package database;

public class AdminQuery implements IAdminQuery
{

	private String adminName;
	private String password;
	private Integer authorithy;
	private int maxResult;
	
	public AdminQuery() 
	{
		adminName=null;
		password=null;
		authorithy =null;
		maxResult=10;
	}
	@Override
	public String getAdminName() 
	{
		return adminName;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public Integer getAuthorithy() 
	{
		return authorithy;
	}

	@Override
	public void setAdminName(String adminName)
	{
		this.adminName=adminName;
	}

	@Override
	public void setPassword(String password)
	{
		this.password=password;
	}

	@Override
	public void setAuthorithy(Integer authorithy) 
	{
		this.authorithy=authorithy;
	}

	@Override
	public void setMaxResults(int max)
	{
		if(max <1)
			return;
		this.maxResult=max;
	}

	@Override
	public int getMaxResults() 
	{
		return maxResult;
	}

}
