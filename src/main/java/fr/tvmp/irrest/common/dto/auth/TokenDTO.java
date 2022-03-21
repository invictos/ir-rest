package fr.tvmp.irrest.common.dto.auth;

import lombok.Data;
import lombok.NonNull;

@Data
public class TokenDTO {
    @NonNull private String token;
}