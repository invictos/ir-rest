package fr.tvmp.irrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/hello-world")
public class HelloResource {
    @GET
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("{name}")
    public String helloName(@PathParam("name") String name){
        return "Hello " + name;
    }

    @GET
    @Path("build/{a}/{b}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buildObject(@PathParam("a") String prenom, @PathParam("b") String nom){
        if(Objects.equals(prenom, "diable")){
            return Response.status(401)
                    .build();
        }
        return Response.ok(new HelloObject(nom, prenom))
                .build();
    }
}