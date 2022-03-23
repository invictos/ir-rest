package fr.tvmp.irrest.server.traitement;

import fr.tvmp.irrest.common.dto.traitement.DonneeMedicaleNewDTO;
import fr.tvmp.irrest.server.auth.roles.Secured;
import fr.tvmp.irrest.server.common.AbstractController;
import fr.tvmp.irrest.common.dto.traitement.TraitementNewDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserRole;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("traitements")
public class TraitementController extends AbstractController {
    @Inject
    TraitementService traitementService;

    @GET
    @Path("/patient/{pat_id}")
    @Secured({UserRole.MEDECIN, UserRole.PATIENT})
    public Response getTraitementsForPatient(@PathParam("pat_id") UUID patient_id){
        authIsUUIDOrRole(patient_id, UserRole.MEDECIN);

        return ifFoundCollection(
                traitementService.getTraitementsByPatientUUID(patient_id)
        );
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(UserRole.MEDECIN)
    public Response createTreatment(@Valid TraitementNewDTO traitementNewDTO){
        authIsUUID(traitementNewDTO.getMedecin());

        return ifFound(
                traitementService.createTraitement(traitementNewDTO, getContextUUID(), traitementNewDTO.getPatient()),
                Response.Status.CREATED
        );
    }

    @GET
    @Path("{traitement_id}")
    @Secured({UserRole.MEDECIN, UserRole.PATIENT})
    public Response getTraitement(@PathParam("traitement_id") UUID traitement_id){
        TraitementEntity traitement = traitementService.getTraitementByUUID(traitement_id)
                .orElseThrow(ForbiddenException::new); //On cache le traitement avant l'authentification complète

        authIsUUIDOrRole(traitement.getPatient().getId(), UserRole.MEDECIN); //Le médecin authentifié doit avoir préscrit le traitement

        return ok(traitement);
    }

    @GET
    @Path("{traitement_id}/donnees/{donnee_id}")
    @Secured({UserRole.MEDECIN, UserRole.PATIENT})
    public Response getDonneeMedicale(@PathParam("traitement_id") UUID traitement_id, @PathParam("donnee_id") UUID donnee_id){
        TraitementEntity traitement = traitementService.getTraitementByUUID(traitement_id)
                .orElseThrow(ForbiddenException::new);

        authIsUUIDOrRole(traitement.getPatient().getId(), UserRole.MEDECIN);

        return ifFound(
                traitementService.getDonneeOfTraitement(traitement, donnee_id)
        );
    }

    @POST
    @Path("/{traitement_id}/donnees")
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(UserRole.MEDECIN)
    public Response addDonneToTraitement(@PathParam("traitement_id") UUID traitement_id, @Valid DonneeMedicaleNewDTO donneeMedicaleNewDTO){
        TraitementEntity traitement = traitementService.getTraitementByUUID(traitement_id)
                        .orElseThrow(NotFoundException::new);

        authIsUUID(traitement.getMedecin().getId());

        return ifFound(
                traitementService.addDonneeToTraitement(traitement, donneeMedicaleNewDTO),
                Response.Status.CREATED
        );
    }

    @DELETE
    @Path("/{traitement_id}/donnees/{donnee_id}")
    @Secured(UserRole.MEDECIN)
    public Response deleteDonnee(@PathParam("traitement_id") UUID traitement_id,
                                 @PathParam("donnee_id") UUID donnee_id){
        TraitementEntity traitement = traitementService.getTraitementByUUID(traitement_id)
                .orElseThrow(NotFoundException::new);

        authIsUUID(traitement.getMedecin().getId());

        traitementService.deleteDonneeOfTraitement(traitement, donnee_id);

        return createResponse(Response.Status.NO_CONTENT);
    }

}
