package fr.tvmp.irrest.user;


import fr.tvmp.irrest.auth.roles.Secured;
import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.stub.PatientForm;
import fr.tvmp.irrest.user.patient.PatientEntity;
import lombok.Data;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    Logger logger;

    @Inject
    UserService userService;

    @GET
    @Path("all")
    public Response getAllUsers(){
        return Response.ok(
                userService.getAllUsers().stream()
                        .map(UserEntity::toUserSmall)
                        .collect(Collectors.toList())
        ).build();
    }

    @GET
    @Path("{id}")
    @Secured({UserRole.ADMINISTRATIF, UserRole.PATIENT})
    public Response getByUUID(@PathParam("id") UUID id, @Context SecurityContext context) {
        if(userService.getUserByUUID(context.getUserPrincipal().getName()))

        UserEntity user = userService.getUserByUUID(id).orElseThrow(NotFoundException::new);

        return Response.ok(user).build();
    }

    @GET
    @Path("{id}/verif/{password}")
    public boolean isPasswordOk(@PathParam("id") UUID id, @PathParam("password") String password){
        UserEntity user = userService.getUserByUUID(id).orElseThrow(NotFoundException::new);
        return userService.validateUserPassword(user, password);
    }
}