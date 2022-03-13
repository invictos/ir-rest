package fr.tvmp.irrest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CredentialsDTO {
    @NotNull UUID uuid;
    @NotNull String password;
}
