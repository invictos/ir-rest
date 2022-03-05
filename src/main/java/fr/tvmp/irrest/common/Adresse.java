package fr.tvmp.irrest.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Embeddable
public class Adresse {
    @NotNull private String rue;
    @NotNull private String ville;
}
