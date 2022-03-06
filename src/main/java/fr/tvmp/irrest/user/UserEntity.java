package fr.tvmp.irrest.user;

import fr.tvmp.irrest.stub.UserSmall;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public abstract class UserEntity extends fr.tvmp.irrest.Entity implements Serializable {

    @NotNull private String prenom;
    @NotNull private String nom;

    @JsonbTransient
    @NotNull private String password;

    public abstract UserRole getRole();

    public UserSmall toUserSmall(){
        return new UserSmall(this.getId(), this.getPrenom(), this.getNom());
    }
}
