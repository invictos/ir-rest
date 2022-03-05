package fr.tvmp.irrest.user;


import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.stub.UserForm;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.UUID;
import java.util.logging.Logger;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    Logger logger;

    @Inject
    UserService userService;

    @GET
    @Path("{id}")
    public Response getByUUID(@PathParam("id") UUID id) {
        UserEntity user = userService.getUserByUUID(id).orElseThrow(NotFoundException::new);

        return Response.ok(user).build();
    }

    @GET
    @Path("{id}/prenom")
    public Response getPrenomByUUID(@PathParam("id") UUID id) {

        UserEntity user = userService.getUserByUUID(id).orElseThrow(NotFoundException::new);

        return Response.ok(user.getPrenom()).build();
    }

    @GET
    @Path("random")
    public UserEntity addRandom(){
        UserEntity user = new UserEntity(
                null,
                "jean",
                "de la fontaine",
                "1234",
                new Adresse("rue du chateau", "paris")
        );

        userService.addUser(user);

        return user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(@Valid UserForm user){
        return Response.ok(userService.addUser(user.toEntity())).build();
    }

    @GET
    @Path("{id}/verif/{password}")
    public boolean isPasswordOk(@PathParam("id") UUID id, @PathParam("password") String password){
        UserEntity user = userService.getUserByUUID(id).orElseThrow(NotFoundException::new);
        return userService.validateUserPassword(user, password);
    }
}