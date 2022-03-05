package fr.tvmp.irrest.stub;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.ToEntity;
import fr.tvmp.irrest.user.UserEntity;
import lombok.Data;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserForm implements ToEntity<UserEntity> {
    @NotNull private String prenom;
    @NotNull private String nom;

    @NotNull private String password;

    @Embedded
    @NotNull private Adresse adresse;

    @Override
    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setPrenom(prenom);
        user.setNom(nom);
        user.setPassword(password);
        user.setAdresse(adresse);

        return user;
    }
}
