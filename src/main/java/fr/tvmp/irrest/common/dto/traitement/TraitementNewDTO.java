package fr.tvmp.irrest.common.dto.traitement;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class TraitementNewDTO implements CPAMDto {
    @NonNull UUID medecin;
    @NonNull UUID patient;
    @NonNull List<DonneeMedicaleNewDTO> donnees;
}