package fr.tvmp.irrest.auth;

import fr.tvmp.irrest.stub.Credentials;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("auth")
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAuthToken(@Valid Credentials credentials){
        return authService.auth(credentials)
                .orElseThrow(() -> new NotFoundException("Bad credentials"));
    }
}