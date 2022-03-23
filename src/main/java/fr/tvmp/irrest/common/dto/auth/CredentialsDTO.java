package fr.tvmp.irrest.common.dto.auth;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CredentialsDTO implements CPAMDto {
    @NotNull private UUID uuid;
    @NotNull private String password;
}