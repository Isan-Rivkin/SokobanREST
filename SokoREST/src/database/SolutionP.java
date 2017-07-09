package database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "Solutions")
@Table(name="Solutions", catalog="SokobanDB", schema="DBO")
public class SolutionP implements POJO
{

	@Id
	private String levelMap;
	
	@Column(name="levelMinSteps")
	private Integer levelMinSteps;
	
	@Column(name="levelSolution")
	private String levelSolution;
	
	public SolutionP() {}
	public SolutionP(String levelMap, String levelSolution, Integer levelMinSteps)
	{
		this.levelMap= levelMap;
		this.levelSolution= levelSolution;
		this.levelMinSteps = levelMinSteps;
	}
	
	public String getLevelMap() 
	{
		return levelMap;
	}
	public void setLevelMap(String levelMap) 
	{
		this.levelMap = levelMap;
	}
	public Integer getLevelMinSteps() 
	{
		return levelMinSteps;
	}
	public void setLevelMinSteps(Integer levelMinSteps) 
	{
		this.levelMinSteps = levelMinSteps;
	}
	public String getLevelSolution()
	{
		return levelSolution;
	}
	public void setLevelSolution(String levelSolution) 
	{
		this.levelSolution = levelSolution;
	}
	@Override
	public String toString()
	{
		return "SolutionP [levelMap=" + levelMap + ", levelMinSteps=" + levelMinSteps + ", levelSolution="
				+ levelSolution + "]";
	}
	@Override
	public String getName() 
	{
		return getLevelMap();
	}
	
}
