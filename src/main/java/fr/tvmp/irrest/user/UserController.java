package fr.tvmp.irrest.user;


import fr.tvmp.irrest.common.Adresse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.UUID;
import java.util.logging.Logger;

@Path("user")
public class UserController {

    @Inject
    Logger logger;

    @Inject
    UserService userService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUUID(@PathParam("id") UUID id) {
        UserEntity user = userService.getUserByUUID(id);

        if(user == null){
            return Response.status(404).build();
        }

        return Response.ok(user).build();
    }

    @GET
    @Path("{id}/prenom")
    public Response getPrenomByUUID(@PathParam("id") UUID id) {
        UserEntity user = userService.getUserByUUID(id);

        if(user == null){
            return Response.status(404).build();
        }

        return Response.ok(user.getPrenom()).build();
    }

    @GET
    @Path("random")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity addRandom(){
        UserEntity user = new UserEntity();
        user.setPrenom("jean");
        user.setNom("de la fontaine");
        user.setAdresse(new Adresse("rue du chateau", "paris"));

        userService.addUser(user);

        return user;
    }
}