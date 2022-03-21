package fr.tvmp.irrest.common.dto.remboursement;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class RemboursementDTO {
    @NonNull UUID id;
    @NonNull UUID patient;
    @NonNull UUID medecin;
    @NonNull List<ARemboursableDTO> remboursables;
}
