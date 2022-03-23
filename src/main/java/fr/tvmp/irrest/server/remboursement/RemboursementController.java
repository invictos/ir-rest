package fr.tvmp.irrest.server.remboursement;

import fr.tvmp.irrest.server.auth.roles.Secured;
import fr.tvmp.irrest.server.common.AbstractController;
import fr.tvmp.irrest.common.dto.utilisateur.UserRole;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("remboursements")
public class RemboursementController extends AbstractController {
    @Inject
    RemboursementService remboursementService;

    @GET
    @Path("{remb_id}")
    @Secured({UserRole.PATIENT, UserRole.ADMINISTRATIF})
    public Response getRemboursement(@PathParam("remb_id") UUID remboursement_id){
        RemboursementEntity remboursement = remboursementService.getRemboursementByUUID(remboursement_id)
                .orElseThrow(ForbiddenException::new); //Retourner Forbidden pour ne pas exposer la présence ou non du remboursement

        authIsUUIDOrRole(remboursement.getPatient().getId(), UserRole.ADMINISTRATIF);

        return ok(remboursement);
    }

    @GET
    @Path("/patient/{pat_id}")
    @Secured({UserRole.PATIENT, UserRole.ADMINISTRATIF})
    public Response listPatientRemboursements(@PathParam("pat_id") UUID patient_id){
        authIsUUIDOrRole(patient_id, UserRole.ADMINISTRATIF);

        return ifFoundCollection(
            remboursementService.getRemboursementsByPatientUUID(patient_id)
        );
    }

    @POST
    @Path("/patient/{pat_id}/consultation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(UserRole.MEDECIN)
    public Response createRemboursementConsultation(@PathParam("pat_id") UUID patient_id){
        return ifFound(
                remboursementService.createRemboursementConsultation(getContextUUID(), patient_id)
        );
    }
}