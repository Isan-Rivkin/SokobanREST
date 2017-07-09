package services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import database.ISolutionQuery;
import database.SolutionModel;
import database.SolutionP;
import database.SolutionQuery;
//http://localhost:8080/SokoREST/solver/get/{jsonSolutionQuery}
@Path("/solver")
public class SolutionService extends Arena
{
	
	@GET
	@Path("/get/{jsonSolutionQuery}")
	@Produces("text/html")
	public String getSolution(@PathParam("jsonSolutionQuery") String jsonSolutionQuery)
	{
		GsonBuilder b = new GsonBuilder();
		Gson gson = b.create();
		jsonSolutionQuery="{"+jsonSolutionQuery+"}";
		System.out.println("enter the resource: " + jsonSolutionQuery);
		SolutionQuery query = gson.fromJson(jsonSolutionQuery, SolutionQuery.class);
		List<SolutionP> solutions = mapper.searchSolutions(query);
		if(solutions!= null && solutions.size()>0)
		{
			SolutionP solP = solutions.get(0);
			SolutionModel model = new SolutionModel(solP.getLevelMap(), solP.getLevelSolution(), solP.getLevelMinSteps());
			String jsonSolutionModel = gson.toJson(model);
			return jsonSolutionModel;
		}
		SolutionP solP = new SolutionP("", "", 0);
		SolutionModel model = new SolutionModel(solP.getLevelMap(), solP.getLevelSolution(), solP.getLevelMinSteps());
		String jsonSolutionModel = gson.toJson(model);
		return jsonSolutionModel;
	}
}
