package fr.tvmp.irrest.user.patient;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.stub.PatientForm;
import fr.tvmp.irrest.stub.UserSmall;
import fr.tvmp.irrest.user.UserEntity;
import fr.tvmp.irrest.user.UserRole;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "patient")
public class PatientEntity extends UserEntity {

    public PatientEntity(@NonNull String prenom, @NonNull String nom, @NonNull String password, @NotNull Adresse adresse, @NonNull Integer nss) {
        super(prenom, nom, password);
        this.adresse = adresse;
        this.nss = nss;
    }

    public PatientEntity(@NonNull PatientForm stub) {
        this(stub.getPrenom(), stub.getNom(), stub.getPassword(), stub.getAdresse(), stub.getNss());
    }

    @Embedded
    @NonNull private Adresse adresse;

    @NonNull private Integer nss;

    @Override
    public UserRole getRole() {
        return UserRole.PATIENT;
    }
}
