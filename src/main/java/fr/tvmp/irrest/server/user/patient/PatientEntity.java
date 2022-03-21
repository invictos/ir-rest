package fr.tvmp.irrest.server.user.patient;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;
import fr.tvmp.irrest.server.common.ToDTO;
import fr.tvmp.irrest.server.remboursement.RemboursementEntity;
import fr.tvmp.irrest.server.traitement.TraitementEntity;
import fr.tvmp.irrest.common.dto.utilisateur.PatientNewDTO;
import fr.tvmp.irrest.server.user.UserEntity;
import fr.tvmp.irrest.server.user.UserRole;
import lombok.*;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "patient")
public class PatientEntity extends UserEntity implements ToDTO<PatientDTO> {

    public PatientEntity(@NonNull String prenom, @NonNull String nom, @NonNull String password, @NonNull Adresse adresse, @NonNull Integer nss, @NonNull Banque banque) {
        super(prenom, nom, password);
        this.adresse = adresse;
        this.nss = nss;
        this.banque = banque;
    }

    public PatientEntity(@NonNull PatientNewDTO stub) {
        this(stub.getPrenom(), stub.getNom(), stub.getPassword(), stub.getAdresse(), stub.getNss(), stub.getBanque());
    }

    @Embedded
    @NonNull private Adresse adresse;

    @NonNull private Integer nss;

    @Embedded
    @NonNull private Banque banque;

    @JsonbTransient
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    @NonNull private Set<TraitementEntity> traitements;

    @JsonbTransient
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    @NonNull private Set<RemboursementEntity> remboursements;

    @Override
    public UserRole getRole() {
        return UserRole.PATIENT;
    }

    @Override
    public PatientDTO toDTO() {
        return new PatientDTO(getAdresse(), getNss(), getBanque());
    }
}
