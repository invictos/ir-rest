package fr.tvmp.irrest.server.user.medecin;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.dto.utilisateur.MedecinDTO;
import fr.tvmp.irrest.common.dto.utilisateur.MedecinType;
import fr.tvmp.irrest.server.user.UserEntity;
import fr.tvmp.irrest.common.dto.utilisateur.UserRole;
import lombok.*;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;


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

    @Embedded
    Adresse adresse;

    String siret; // https://www.economie.gouv.fr/entreprises/numeros-siren-siret

    public MedecinEntity(@NonNull String prenom, @NonNull String nom, @NonNull String password, @NonNull MedecinType type, @NonNull Adresse adresse, @NonNull String siret) {
        super(prenom, nom, password);
        this.type = type;
        this.adresse = adresse;
        this.siret = siret;
    }

    @Override
    public UserRole getRole() {
        return UserRole.MEDECIN;
    }

    @Override
    public MedecinDTO toDTO() {
        return new MedecinDTO(getId(), getPrenom(), getPrenom(), getAdresse(), getType(), getSiret(), getRole());
    }
}
