package fr.tvmp.irrest.server.user;


import fr.tvmp.irrest.server.auth.roles.Secured;
import fr.tvmp.irrest.server.common.AbstractController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.UUID;
import java.util.stream.Collectors;

@Path("user")
public class UserController extends AbstractController {

    @Inject
    UserService userService;

    @GET
    @Path("all")
    public Response getAllUsers(){
        getLogger().info("Listed all users");
        return ok(
                userService.getAllUsers()
        );
    }

    @GET
    @Path("{id}")
    @Secured({UserRole.PATIENT, UserRole.ADMINISTRATIF, UserRole.MEDECIN})
    public Response getByUUID(@PathParam("id") UUID id){
        authIsUUIDOrRole(id, UserRole.ADMINISTRATIF);

        return ifFound(
                userService.getUserByUUID(id)
        );
    }
}