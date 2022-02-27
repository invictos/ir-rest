package fr.tvmp.irrest.user;

import fr.tvmp.irrest.common.Adresse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "utilisateurs")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull private String prenom;
    @NotNull private String nom;

    @JsonbTransient
    @NotNull private String password;

    @Embedded
    @NotNull private Adresse adresse;
}
