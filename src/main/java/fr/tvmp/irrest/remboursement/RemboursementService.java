package fr.tvmp.irrest.remboursement;

import fr.tvmp.irrest.common.AbstractService;
import fr.tvmp.irrest.user.UserService;
import fr.tvmp.irrest.user.medecin.ConsultationEntity;
import fr.tvmp.irrest.user.medecin.MedecinEntity;
import fr.tvmp.irrest.user.patient.PatientEntity;
import fr.tvmp.irrest.user.patient.PatientService;
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

    public Optional<RemboursementEntity> createConsultation(@NonNull UUID medecin, @NonNull UUID patient){
        Optional<PatientEntity> pat = userService.getUserByUUID(patient, PatientEntity.class);
        Optional<MedecinEntity> med = userService.getUserByUUID(medecin, MedecinEntity.class);

        if(pat.isEmpty() || med.isEmpty()){
            getLogger().info("Patient or Medic shouldn't be empty"+ pat + med);
            return Optional.empty();
        }

        RemboursementEntity remboursement = new RemboursementEntity(pat.get(), med.get(), new HashSet<>(List.of(
                new ConsultationEntity(med.get())
        )));

        return Optional.ofNullable(remboursementDAO.add(remboursement));

    }

    public void processRemboursement(RemboursementEntity remboursement){
        remboursement.getRemboursables()
                .forEach(remboursable ->
                        patientService.rembourser(remboursement.getPatient(), remboursable)
                );
    }
}
