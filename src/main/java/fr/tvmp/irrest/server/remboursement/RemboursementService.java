package fr.tvmp.irrest.server.remboursement;

import fr.tvmp.irrest.server.common.AbstractService;
import fr.tvmp.irrest.server.user.UserService;
import fr.tvmp.irrest.server.user.medecin.ConsultationEntity;
import fr.tvmp.irrest.server.user.medecin.MedecinEntity;
import fr.tvmp.irrest.server.user.patient.PatientEntity;
import fr.tvmp.irrest.server.user.patient.PatientService;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.*;

public class RemboursementService extends AbstractService {

    @Inject
    RemboursementDAO remboursementDAO;

    @Inject
    UserService userService;

    @Inject
    PatientService patientService;

    public Optional<RemboursementEntity> getRemboursementByUUID(@NonNull UUID remboursement){
        return Optional.ofNullable(remboursementDAO.getByUUID(remboursement));
    }

    public Optional<Set<RemboursementEntity>> getRemboursementsByPatientUUID(@NonNull UUID patient){
        return userService.getUserByUUID(patient, PatientEntity.class)
                .map(PatientEntity::getRemboursements);
    }

    public Optional<RemboursementEntity> createRemboursementConsultation(@NonNull UUID medecin, @NonNull UUID patient){
        Optional<PatientEntity> pat = userService.getUserByUUID(patient, PatientEntity.class);
        Optional<MedecinEntity> med = userService.getUserByUUID(medecin, MedecinEntity.class);

        if(pat.isEmpty() || med.isEmpty()){
            getLogger().info("Patient or Medic shouldn't be empty"+ pat + med);
            return Optional.empty();
        }

        return createRemboursement(med.get(), pat.get(), new HashSet<>(List.of(
                new ConsultationEntity(med.get())
        )));

    }

    protected Optional<RemboursementEntity> createRemboursement(@NonNull MedecinEntity medecin, @NonNull PatientEntity patient, @NonNull Set<ARemboursable> remboursables){

        RemboursementEntity remboursement = new RemboursementEntity(patient, medecin, remboursables);

        remboursement = remboursementDAO.add(remboursement);

        processRemboursement(remboursement);

        return Optional.of(remboursement);
    }

    public void processRemboursement(RemboursementEntity remboursement){
        remboursement.getRemboursables()
                .forEach(remboursable ->
                        patientService.rembourser(remboursement.getPatient(), remboursable)
                );
    }
}
