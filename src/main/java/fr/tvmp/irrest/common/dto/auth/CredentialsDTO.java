package fr.tvmp.irrest.common.dto.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CredentialsDTO {
    @NotNull private UUID uuid;
    @NotNull private String password;
}