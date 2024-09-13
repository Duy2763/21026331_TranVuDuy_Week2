package rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
public class UserResource {
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String Test() {
		return "Hello World";
	}
}
