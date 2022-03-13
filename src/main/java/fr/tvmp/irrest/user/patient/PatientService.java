package fr.tvmp.irrest.user.patient;

import fr.tvmp.irrest.common.AbstractService;
import fr.tvmp.irrest.medical.TraitementEntity;
import fr.tvmp.irrest.user.UserRole;
import fr.tvmp.irrest.user.UserService;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PatientService extends AbstractService {
    @Inject
    UserService userService;

    public Optional<Set<TraitementEntity>> getTraitementsByPatientUUID(@NonNull UUID patient){
        return userService.getUserByUUID(patient, PatientEntity.class)
                .map(u -> {
                    getLogger().info(u.toString());
                    return u;
                })
                .map(PatientEntity::getTraitements);
    }

    public Optional<PatientEntity> getPatientByNSS(@NonNull Integer nss){
        return userService.getAllUsers().stream()
                .filter(u -> u.getRole() == UserRole.PATIENT)
                .map(p -> (PatientEntity) p)
                .filter(p -> p.getNss().equals(nss))
                .findFirst();
    }
}
