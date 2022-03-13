package fr.tvmp.irrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserSmallDTO {
    private UUID id;
    private String prenom;
    private String nom;
}
