package fr.tvmp.irrest.common.dto.traitement;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.Data;
import lombok.NonNull;

@Data
public class DonneeMedicaleNewDTO implements CPAMDto {
    @NonNull DonneeMedicaleType type;
    @NonNull String value;
}
