package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.catalina.mapper.Mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import database.SolutionModel;
import database.SolutionP;

//http://localhost:8080/SokoREST/updateSolver/get/{jsonSolution}
@Path("/updatesolver")
public class UpdateSolutionService extends Arena
{
	@GET
	@Path("/get/{jsonSolution}")
	@Produces("text/html")
	public String getSolution(@PathParam("jsonSolution") String jsonSolution)
	{
		GsonBuilder b = new GsonBuilder();
		Gson gson = b.create();
		String fullJsonSolution = "{"+jsonSolution+"}";
		SolutionModel model = gson.fromJson(fullJsonSolution, SolutionModel.class);
		SolutionP p = new SolutionP(model.getLevelMap(), model.getLevelSolution(), model.getLevelSteps());
		if(mapper.isEntityExist(p)==null)
		{
			mapper.savePOJO(p);
		}
		return fullJsonSolution;
	}
}
