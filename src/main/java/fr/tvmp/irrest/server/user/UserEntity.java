package fr.tvmp.irrest.server.user;

import fr.tvmp.irrest.server.CPAMEntity;
import fr.tvmp.irrest.common.dto.utilisateur.UserPublicDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utilisateurs")
public abstract class UserEntity extends CPAMEntity implements Serializable {

    @NotNull private String prenom;
    @NotNull private String nom;

    @JsonbTransient
    @NotNull private String password;

    @JsonbProperty("role")
    public abstract UserRole getRole();

    public UserPublicDTO toUserSmall(){
        return new UserPublicDTO(this.getId(), this.getPrenom(), this.getNom());
    }
}
