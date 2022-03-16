package fr.tvmp.irrest.user.patient;

import fr.tvmp.irrest.auth.roles.Secured;
import fr.tvmp.irrest.common.AbstractController;
import fr.tvmp.irrest.traitement.TraitementEntity;
import fr.tvmp.irrest.dto.PatientNewDTO;
import fr.tvmp.irrest.user.UserRole;
import fr.tvmp.irrest.user.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.UUID;

@Path("patient")
public class PatientController extends AbstractController {
    @Inject
    UserService userService;

    @Inject
    PatientService patientService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured({UserRole.ADMINISTRATIF})
    public Response add(@Valid PatientNewDTO user){
        return Response.ok(userService.addUser(new PatientEntity(user))).build();
    }

    @GET
    @Path("{id}/traitements")
    @Secured({UserRole.PATIENT, UserRole.MEDECIN})
    public Response getTraitements(@PathParam("id") UUID id){
        authIsUUIDOrRole(id, UserRole.MEDECIN);

        Set<TraitementEntity> traitements = patientService.getTraitementsByPatientUUID(id)
                .orElseThrow(NotFoundException::new);

        return Response.ok(traitements).build();
    }
}
