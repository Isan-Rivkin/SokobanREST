package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/Echo/{query}")
public class EchoService 
{
	
	@GET
	@Produces("text/html")
	public String getMessage(@PathParam("query") String q)
	{
		return "Echo " +q;
	}
}