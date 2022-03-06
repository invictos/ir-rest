package fr.tvmp.irrest;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
