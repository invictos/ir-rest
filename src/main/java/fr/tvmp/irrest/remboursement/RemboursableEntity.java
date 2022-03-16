package fr.tvmp.irrest.remboursement;

import fr.tvmp.irrest.CPAMEntity;
import lombok.*;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "remboursable")
public abstract class RemboursableEntity extends CPAMEntity {

    @JsonbTransient
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @NonNull RemboursementEntity remboursement;

    public abstract Float getPrix();

    public abstract Float getTauxCPAM();

    public Float getRemboursement(){
        return getPrix()*getTauxCPAM();
    }
}
