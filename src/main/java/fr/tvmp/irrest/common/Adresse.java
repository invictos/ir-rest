package fr.tvmp.irrest.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Embeddable
public class Adresse {
    private String rue;
    private String ville;
}
