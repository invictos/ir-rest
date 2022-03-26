package fr.tvmp.irrest.common.dto.auth;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class CredentialsDTO implements CPAMDto {
    @NonNull private UUID uuid;
    @NonNull private String password;
}