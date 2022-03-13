package fr.tvmp.irrest.user.administratif;

import fr.tvmp.irrest.user.UserEntity;
import fr.tvmp.irrest.user.UserRole;
import lombok.*;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "administratif")
public class AdministratifEntity extends UserEntity {
    Integer echelon;

    public AdministratifEntity(@NonNull String prenom, @NonNull String nom, @NonNull String password, @NonNull Integer echelon) {
        super(prenom, nom, password);
        this.echelon = echelon;
    }

    @Override
    public UserRole getRole() {
        return UserRole.ADMINISTRATIF;
    }
}
