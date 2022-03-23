package fr.tvmp.irrest.common.dto.traitement;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class TraitementNewDTO {
    @NonNull UUID medecin;
    @NonNull UUID patient;
    @NonNull List<DonneeMedicaleNewDTO> donnees;
}