package fr.tvmp.irrest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class CPAMEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}