package fr.tvmp.irrest.server.traitement;

import fr.tvmp.irrest.common.dto.traitement.DonneeMedicaleNewDTO;
import fr.tvmp.irrest.server.common.AbstractService;
import fr.tvmp.irrest.common.dto.traitement.TraitementNewDTO;
import fr.tvmp.irrest.server.traitement.donneemedicale.DonneeMedicaleEntity;
import fr.tvmp.irrest.server.user.UserService;
import fr.tvmp.irrest.server.user.medecin.MedecinEntity;
import fr.tvmp.irrest.server.user.patient.PatientEntity;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class TraitementService extends AbstractService {
    @Inject
    TraitementDAO traitementDAO;

    @Inject
    UserService userService;

    public Optional<TraitementEntity> getTraitementByUUID(@NonNull UUID traitement) {
        return Optional.ofNullable(traitementDAO.getByUUID(traitement));
    }

    public Optional<Set<TraitementEntity>> getTraitementsByPatientUUID(@NonNull UUID patient){
        return userService.getUserByUUID(patient, PatientEntity.class)
                .map(PatientEntity::getTraitements);
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

    public Optional<DonneeMedicaleEntity> getDonneeOfTraitement(@NonNull TraitementEntity traitement, @NonNull UUID donnee_id){
        return traitement.getDonneesMedicales().stream()
                .filter(d -> d.getId() == donnee_id)
                .findFirst();
    }

    public Optional<DonneeMedicaleEntity> addDonneeToTraitement(@NonNull TraitementEntity traitement, @NonNull DonneeMedicaleNewDTO donnee){
        DonneeMedicaleEntity dme = new DonneeMedicaleEntity(donnee, traitement);
        traitement.getDonneesMedicales().add(dme); //TODO: check if persist !!
        return Optional.of(dme);
    }

    public void deleteDonneeOfTraitement(TraitementEntity traitement, UUID donnee_id) {
        traitement.getDonneesMedicales()
                .removeIf(dm -> dm.getId() == donnee_id); //TODO: check if persist !!
    }
}