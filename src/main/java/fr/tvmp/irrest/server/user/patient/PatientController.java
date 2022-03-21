package fr.tvmp.irrest.server.user.patient;

import fr.tvmp.irrest.server.auth.roles.Secured;
import fr.tvmp.irrest.server.common.AbstractController;
import fr.tvmp.irrest.server.remboursement.RemboursementEntity;
import fr.tvmp.irrest.server.traitement.TraitementEntity;
import fr.tvmp.irrest.common.dto.utilisateur.PatientNewDTO;
import fr.tvmp.irrest.server.user.UserRole;
import fr.tvmp.irrest.server.user.UserService;

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

    @GET
    @Path("{id}/remboursements")
    @Secured({UserRole.PATIENT, UserRole.ADMINISTRATIF})
    public Response getRemboursements(@PathParam("id") UUID id){
        authIsUUIDOrRole(id, UserRole.ADMINISTRATIF);

        Set<RemboursementEntity> remboursements = patientService.getRemboursementsByPatientUUID(id)
                .orElseThrow(NotFoundException::new);

        return Response.ok(remboursements).build();
    }
}
