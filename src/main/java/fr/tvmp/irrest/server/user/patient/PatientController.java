package fr.tvmp.irrest.server.user.patient;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import fr.tvmp.irrest.server.auth.roles.Secured;
import fr.tvmp.irrest.server.common.AbstractController;
import fr.tvmp.irrest.server.remboursement.RemboursementEntity;
import fr.tvmp.irrest.server.traitement.TraitementEntity;
import fr.tvmp.irrest.common.dto.utilisateur.PatientNewDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserRole;
import fr.tvmp.irrest.server.user.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.UUID;

@Path("patients")
public class PatientController extends AbstractController {
    @Inject
    PatientService patientService;

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured({UserRole.ADMINISTRATIF})
    public Response addPatient(@Valid PatientNewDTO user){
        return ok(
                patientService.addPatient(user)
        );
    }

    @GET
    @Path("{nss}")
    @Secured({UserRole.ADMINISTRATIF, UserRole.PATIENT})
    public Response getPatientByNSS(@PathParam("nss") String nss){
        PatientEntity patient = patientService.getPatientByNSS(nss)
                        .orElseThrow(ForbiddenException::new);

        authIsUUIDOrRole(patient.getId(), UserRole.ADMINISTRATIF);

        return ok(
                patient
        );
    }

    @GET
    @Path("{pat_id}/banque")
    @Secured({UserRole.PATIENT, UserRole.ADMINISTRATIF})
    public Response getPatientBanque(@PathParam("pat_id") UUID patient_id){
        authIsUUIDOrRole(patient_id, UserRole.ADMINISTRATIF);

        return Response.ok(
                patientService.getPatientByUUID(patient_id)
                        .map(PatientEntity::getBanque)
        ).build();
    }

    @PUT
    @Path("{pat_id}/banque")
    @Secured({UserRole.PATIENT, UserRole.ADMINISTRATIF})
    public Response setPatientBanque(@PathParam("pat_id") UUID patient_id, @Valid Banque banque){
        authIsUUIDOrRole(patient_id, UserRole.ADMINISTRATIF);

        PatientEntity patient = patientService.getPatientByUUID(patient_id)
                .orElseThrow(NotFoundException::new);

        return Response.ok(
            patientService.setPatientBanque(patient, banque)
        ).build();
    }

    @GET
    @Path("{pat_id}/adresse")
    @Secured({UserRole.PATIENT, UserRole.ADMINISTRATIF})
    public Response getPatientAdresse(@PathParam("pat_id") UUID patient_id){
        authIsUUIDOrRole(patient_id, UserRole.ADMINISTRATIF);

        return Response.ok(
                userService.getUserByUUID(patient_id, PatientEntity.class)
                        .map(PatientEntity::getAdresse)
        ).build();
    }

    @PUT
    @Path("{pat_id}/adresse")
    @Secured({UserRole.PATIENT, UserRole.ADMINISTRATIF})
    public Response setPatientAdresse(@PathParam("pat_id") UUID patient_id, @Valid Adresse adresse){
        authIsUUIDOrRole(patient_id, UserRole.ADMINISTRATIF);

        PatientEntity patient = patientService.getPatientByUUID(patient_id)
                .orElseThrow(NotFoundException::new);

        return Response.ok(
                patientService.setPatientAdresse(patient, adresse)
        ).build();
    }
}
