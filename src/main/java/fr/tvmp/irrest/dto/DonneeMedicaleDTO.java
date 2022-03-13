package fr.tvmp.irrest.dto;

import fr.tvmp.irrest.medical.donneemedicale.DonneeMedicaleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonneeMedicaleDTO {
    @NotNull DonneeMedicaleType type;
    @NotNull String valeur;
}