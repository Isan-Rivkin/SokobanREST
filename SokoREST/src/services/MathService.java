package services;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/Math/")
public class MathService 
{
	@GET
	public String getMessage(@QueryParam("a") String a , @QueryParam("b") String b)
	{
		double result = Double.parseDouble(a) + Double.parseDouble(b);
		return "result: "+ a + " + "+b+" = " +result ;
	}
}
