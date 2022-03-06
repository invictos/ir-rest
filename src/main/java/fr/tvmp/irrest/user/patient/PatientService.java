package fr.tvmp.irrest.user.patient;

import fr.tvmp.irrest.user.UserEntity;
import fr.tvmp.irrest.user.UserService;
import lombok.NonNull;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class PatientService {
    @Inject
    UserService userService;

    @Inject
    Logger logger;

    public Optional<PatientEntity> getPatientByUUID(@NonNull UUID uuid) {
        return userService.getUserByUUID(uuid)
                .flatMap(user -> {
                    PatientEntity patient = null;
                    try{
                        patient = (PatientEntity) user;
                    }catch(ClassCastException e){
                        logger.info("Can't cast to PatientEntity");
                    }
                    return Optional.ofNullable(patient);
                });
    }
}
