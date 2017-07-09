package services;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class Application extends ResourceConfig
{
	public Application() 
	{
		super();
		register(EchoService.class);
		register(MathService.class);
		register(SolutionService.class);
		register(UpdateSolutionService.class);
	}
}
