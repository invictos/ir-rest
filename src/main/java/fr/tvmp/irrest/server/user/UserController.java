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
    @Path("allUsers")
    public Response getAllUsers(){
        getLogger().info("Listed all users");
        return Response.ok(
                userService.getAllUsers().stream()
                        .map(UserEntity::toUserSmall)
                        .collect(Collectors.toList())
        ).build();
    }

    @GET
    @Path("{id}")
    @Secured({UserRole.PATIENT})
    public UserEntity getByUUID(@PathParam("id") UUID id){
        authIsUUIDOrRole(id, UserRole.ADMINISTRATIF);

        UserEntity user = userService.getUserByUUID(id).orElseThrow(NotFoundException::new);

        return user;
    }
}