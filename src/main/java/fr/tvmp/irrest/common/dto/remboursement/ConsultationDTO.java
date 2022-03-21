package fr.tvmp.irrest.common.dto.remboursement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConsultationDTO extends ARemboursableDTO{
    @NonNull private UUID medecin;
}
