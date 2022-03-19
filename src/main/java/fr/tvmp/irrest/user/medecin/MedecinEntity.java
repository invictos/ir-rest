package fr.tvmp.irrest.user.medecin;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.MedecinType;
import fr.tvmp.irrest.user.UserEntity;
import fr.tvmp.irrest.user.UserRole;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "medecin")
public class MedecinEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    MedecinType type;

    Adresse adresse;

    String siret; // https://www.economie.gouv.fr/entreprises/numeros-siren-siret

    public MedecinEntity(@NonNull String prenom, @NonNull String nom, @NonNull String password, @NonNull MedecinType type, @NotNull Adresse adresse, @NonNull String siret) {
        super(prenom, nom, password);
        this.type = type;
        this.adresse = adresse;
        this.siret = siret;
    }

    @Override
    public UserRole getRole() {
        return UserRole.MEDECIN;
    }
}
