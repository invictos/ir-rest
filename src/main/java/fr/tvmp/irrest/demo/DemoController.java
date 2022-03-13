package fr.tvmp.irrest.demo;

import fr.tvmp.irrest.auth.roles.Secured;
import fr.tvmp.irrest.common.AbstractController;
import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.user.UserEntity;
import fr.tvmp.irrest.user.UserRole;
import fr.tvmp.irrest.user.UserService;
import fr.tvmp.irrest.user.patient.PatientEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

/**
 * Utilisé pour la demonstration du produit partiel
 */
@Path("demo")
public class DemoController extends AbstractController {

    @Inject
    private UserService userService;

    /**
     * Retourne une liste d'utilisateurs (pour un login facile)
     * @return UserSmall[]
     */
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

    /**
     * Adds a mock Patient
     * @return PatientEntity
     */
    @GET
    @Path("random")
    @Secured({UserRole.ADMINISTRATIF})
    public PatientEntity addRandom(){
        PatientEntity user = new PatientEntity(
                "jean",
                "de la fontaine",
                "1234",
                new Adresse("rue du chateau", "paris"),
                100000
        );

        userService.addUser(user);

        return user;
    }
}
