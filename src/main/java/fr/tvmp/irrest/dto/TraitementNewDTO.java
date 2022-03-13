package fr.tvmp.irrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitementNewDTO {
    @NotNull List<DonneeMedicaleDTO> donneesMedicales;
}