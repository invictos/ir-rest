package fr.tvmp.irrest.medical;

import fr.tvmp.irrest.common.AbstractService;
import fr.tvmp.irrest.dto.TraitementNewDTO;
import fr.tvmp.irrest.user.UserService;
import fr.tvmp.irrest.user.medecin.MedecinEntity;
import fr.tvmp.irrest.user.patient.PatientEntity;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class TraitementService extends AbstractService {
    @Inject
    TraitementDAO traitementDAO;

    @Inject
    UserService userService;

    public Optional<TraitementEntity> getTraitementByUUID(@NonNull UUID traitement) {
        return Optional.ofNullable(traitementDAO.getByUUID(traitement));
    }

    public Optional<TraitementEntity> createTraitement(@NonNull TraitementNewDTO traitementNewDTO,@NonNull UUID medecin,@NonNull UUID patient){
        Optional<PatientEntity> pat = userService.getUserByUUID(patient, PatientEntity.class);
        Optional<MedecinEntity> med = userService.getUserByUUID(medecin, MedecinEntity.class);

        if(pat.isEmpty() || med.isEmpty()){
            getLogger().info("Patient or Medic shouldn't be empty"+ pat + med);
            return Optional.empty();
        }

        TraitementEntity traitement = new TraitementEntity(traitementNewDTO, med.get(), pat.get());

        return addTraitement(traitement);
    }

    public Optional<TraitementEntity> addTraitement(@NonNull TraitementEntity traitement){
        return Optional.ofNullable(traitementDAO.add(traitement));
    }
}