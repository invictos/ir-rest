package fr.tvmp.irrest.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Adresse {
    @NotNull private String rue;
    @NotNull private String ville;
}
