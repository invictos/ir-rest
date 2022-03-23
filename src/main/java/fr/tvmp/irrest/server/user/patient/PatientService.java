package fr.tvmp.irrest.server.user.patient;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import fr.tvmp.irrest.common.dto.utilisateur.PatientNewDTO;
import fr.tvmp.irrest.server.common.AbstractService;
import fr.tvmp.irrest.server.remboursement.ARemboursable;
import fr.tvmp.irrest.server.remboursement.RemboursementEntity;
import fr.tvmp.irrest.server.traitement.TraitementEntity;
import fr.tvmp.irrest.common.dto.utilisateur.UserRole;
import fr.tvmp.irrest.server.user.UserService;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PatientService extends AbstractService {
    @Inject
    UserService userService;

    public PatientEntity addPatient(@NonNull PatientNewDTO patient){
        return (PatientEntity) userService.addUser(new PatientEntity(patient));
    }

    public Optional<PatientEntity> getPatientByNSS(@NonNull String nss){
        return userService.getAllUsers().stream()
                .filter(u -> u.getRole() == UserRole.PATIENT)
                .map(p -> (PatientEntity) p)
                .filter(p -> p.getNss().equals(nss))
                .findFirst();
    }

    public Optional<PatientEntity> getPatientByUUID(@NonNull UUID uuid){
        return userService.getUserByUUID(uuid, PatientEntity.class);
    }

    public Banque setPatientBanque(@NonNull PatientEntity patient, @NonNull Banque banque){
        patient.setBanque(banque);
        return patient.getBanque();
    }

    public Adresse setPatientAdresse(@NonNull PatientEntity patient, @NonNull Adresse adresse){
        patient.setAdresse(adresse);
        return patient.getAdresse();
    }

    public void rembourser(PatientEntity patient, ARemboursable remboursable) {
        getLogger().info("Virement de " + remboursable.getRemboursement() + " euros sur " + patient.getBanque());
    }
}
