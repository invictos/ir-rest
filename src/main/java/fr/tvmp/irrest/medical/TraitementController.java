package fr.tvmp.irrest.medical;

import fr.tvmp.irrest.auth.roles.Secured;
import fr.tvmp.irrest.common.AbstractController;
import fr.tvmp.irrest.dto.TraitementNewDTO;
import fr.tvmp.irrest.user.UserRole;
import fr.tvmp.irrest.user.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("treatments")
public class TraitementController extends AbstractController {
    @Inject
    UserService userService;

    @Inject
    TraitementService traitementService;

    @POST
    @Path("{pat_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(UserRole.MEDECIN)
    public TraitementEntity createTreatment(@PathParam("pat_id") UUID patient_id, @Valid TraitementNewDTO traitementNewDTO){
        return traitementService.createTraitement(traitementNewDTO, getContextUUID(), patient_id)
                .orElseThrow(NotFoundException::new);
    }
}
