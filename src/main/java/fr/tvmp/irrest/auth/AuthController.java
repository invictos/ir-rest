package fr.tvmp.irrest.auth;

import fr.tvmp.irrest.common.AbstractController;
import fr.tvmp.irrest.dto.CredentialsDTO;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthController extends AbstractController {

    @Inject
    AuthService authService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAuthToken(@Valid CredentialsDTO credentialsDTO){
        var token = authService.createToken(credentialsDTO);

        if(token.isEmpty()){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        //We return only the string for simplicity
        return Response.ok(token.get().getToken()).build();
    }
}