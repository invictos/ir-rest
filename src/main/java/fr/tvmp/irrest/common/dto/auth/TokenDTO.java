package fr.tvmp.irrest.common.dto.auth;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.Data;
import lombok.NonNull;

@Data
public class TokenDTO implements CPAMDto {
    @NonNull private String token;
}