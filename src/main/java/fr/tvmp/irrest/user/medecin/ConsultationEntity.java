package fr.tvmp.irrest.user.medecin;

import fr.tvmp.irrest.remboursement.RemboursableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Table(name = "consultation")
public class ConsultationEntity extends RemboursableEntity {
    MedecinType type;

    @Override
    public Float getPrix() {
        return 25.0F;
    }

    @Override
    public Float getTauxCPAM() {
        return 0.70F;
    }
}