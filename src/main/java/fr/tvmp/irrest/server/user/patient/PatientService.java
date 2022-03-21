package fr.tvmp.irrest.server.user.patient;

import fr.tvmp.irrest.server.common.AbstractService;
import fr.tvmp.irrest.server.remboursement.ARemboursable;
import fr.tvmp.irrest.server.remboursement.RemboursementEntity;
import fr.tvmp.irrest.server.traitement.TraitementEntity;
import fr.tvmp.irrest.server.user.UserRole;
import fr.tvmp.irrest.server.user.UserService;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PatientService extends AbstractService {
    @Inject
    UserService userService;

    public Optional<Set<TraitementEntity>> getTraitementsByPatientUUID(@NonNull UUID patient){
        return userService.getUserByUUID(patient, PatientEntity.class)
                .map(PatientEntity::getTraitements);
    }

    public Optional<Set<RemboursementEntity>> getRemboursementsByPatientUUID(@NonNull UUID patient){
        return userService.getUserByUUID(patient, PatientEntity.class)
                .map(PatientEntity::getRemboursements);
    }

    public Optional<PatientEntity> getPatientByNSS(@NonNull Integer nss){
        return userService.getAllUsers().stream()
                .filter(u -> u.getRole() == UserRole.PATIENT)
                .map(p -> (PatientEntity) p)
                .filter(p -> p.getNss().equals(nss))
                .findFirst();
    }

    public void rembourser(PatientEntity patient, ARemboursable remboursable) {
        getLogger().info("Virement de " + remboursable.getRemboursement() + " euros sur " + patient.getBanque());
    }
}
