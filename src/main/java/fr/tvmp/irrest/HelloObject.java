package fr.tvmp.irrest;


import lombok.Data;
import lombok.NonNull;

@Data
public class HelloObject {
    @NonNull private String nom;
    @NonNull private String prenom;
}
