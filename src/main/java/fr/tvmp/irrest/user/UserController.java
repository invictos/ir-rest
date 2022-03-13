package fr.tvmp.irrest.user;


import fr.tvmp.irrest.auth.roles.Secured;
import fr.tvmp.irrest.common.AbstractController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.UUID;

@Path("user")
public class UserController extends AbstractController {

    @Inject
    UserService userService;

    @GET
    @Path("{id}")
    @Secured({UserRole.ADMINISTRATIF, UserRole.PATIENT})
    public Response getByUUID(@PathParam("id") UUID id){
        authIsUUIDOrRole(id, UserRole.ADMINISTRATIF);

        UserEntity user = userService.getUserByUUID(id).orElseThrow(NotFoundException::new);

        return Response.ok(user).build();
    }
}