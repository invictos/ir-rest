package fr.tvmp.irrest.remboursement;

import fr.tvmp.irrest.auth.roles.Secured;
import fr.tvmp.irrest.dto.TraitementNewDTO;
import fr.tvmp.irrest.traitement.TraitementEntity;
import fr.tvmp.irrest.user.UserRole;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("reimbursement")
public class RemboursementController {
    @POST
    @Path("{pat_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(UserRole.MEDECIN)
    public TraitementEntity createTreatment(@PathParam("pat_id") UUID patient_id, @Valid TraitementNewDTO traitementNewDTO){
        return traitementService.createTraitement(traitementNewDTO, getContextUUID(), patient_id)
                .orElseThrow(NotFoundException::new);
    }
}
