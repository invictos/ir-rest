package fr.tvmp.irrest.stub;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserSmall {
    private UUID id;
    private String prenom;
    private String nom;
}
