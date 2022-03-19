package fr.tvmp.irrest.user.medecin;

import fr.tvmp.irrest.remboursement.RemboursableEntity;
import lombok.*;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "consultation")
public class ConsultationEntity extends RemboursableEntity {

    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @EqualsAndHashCode.Exclude
    MedecinEntity medecin;

    @Override
    public Float getPrix() {
        return 25.0F;
    }

    @Override
    public Float getTauxCPAM() {
        return 0.70F;
    }

    @JsonbProperty(value="medecin")
    public UUID getMedecinUUID(){
        return getMedecin().getId();
    }
}