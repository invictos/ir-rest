package fr.tvmp.irrest.user.patient;

import fr.tvmp.irrest.auth.roles.Secured;
import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.stub.PatientForm;
import fr.tvmp.irrest.user.UserEntity;
import fr.tvmp.irrest.user.UserRole;
import fr.tvmp.irrest.user.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("patient")
@Produces(MediaType.APPLICATION_JSON)
public class PatientController {
    @Inject
    PatientService patientService;

    @Inject
    UserService userService;

    @GET
    @Path("{id}/adresse")
    @Secured({UserRole.PATIENT})
    public Response getAdresseByUUID(@PathParam("id") UUID id) {
        PatientEntity patient = patientService.getPatientByUUID(id).orElseThrow(NotFoundException::new);

        return Response.ok(patient.getAdresse()).build();
    }

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured({UserRole.ADMINISTRATIF})
    public Response add(@Valid PatientForm user){
        return Response.ok(userService.addUser(new PatientEntity(user))).build();
    }
}
