package fr.tvmp.irrest.common.dto.remboursement;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class RemboursementDTO implements CPAMDto {
    @NonNull UUID id;
    @NonNull UUID patient;
    @NonNull UUID medecin;
    @NonNull List<? extends ARemboursableDTO> remboursables;
}
