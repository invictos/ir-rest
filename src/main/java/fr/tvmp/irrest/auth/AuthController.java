package fr.tvmp.irrest.auth;

import fr.tvmp.irrest.stub.Credentials;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @Inject
    Logger logger;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAuthToken(@Valid Credentials credentials){
        var token = authService.auth(credentials);

        if(token.isEmpty()){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        //We return only the string for simplicity
        return Response.ok(token.get().getToken()).build();
    }
}