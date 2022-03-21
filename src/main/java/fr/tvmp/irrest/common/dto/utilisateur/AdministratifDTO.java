package fr.tvmp.irrest.common.dto.utilisateur;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdministratifDTO extends UserDTO{
    @NonNull private Integer echelon;
}
