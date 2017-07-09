package database;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity (name = "AdminsTable")
@Table(name="AdminsTable", catalog="SokobanDB", schema="DBO")
public class AdminP implements POJO
{
	@Id
	private String adminName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="authorithy")
	private Integer authority;
	
	public AdminP() {}
	public AdminP(String adminName, String password, Integer authorithy)
	{
		this.adminName=adminName;
		this.password=password;
		this.authority=authorithy;
	}
	
	public String getAdminName() 
	{
		return adminName;
	}
	public void setAdminName(String adminName)
	{
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public Integer getAuthority()
	{
		return authority;
	}
	public void setAuthority(Integer authority)
	{
		this.authority = authority;
	}
	@Override
	public String getName()
	{
		return adminName;
	}
	@Override
	public String toString() 
	{
		return "AdminP [adminName=" + adminName + ", password=" + password + ", authority=" + authority + "]";
	}
	

}
