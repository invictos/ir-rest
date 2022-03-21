package fr.tvmp.irrest.server.auth;

import fr.tvmp.irrest.server.common.AbstractController;
import fr.tvmp.irrest.common.dto.auth.CredentialsDTO;

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
    public Response getAuthToken(@Valid CredentialsDTO credentials){
        return ifFound(
                authService.createToken(credentials)
        );
    }
}