package fr.tvmp.irrest.server.auth;

import fr.tvmp.irrest.common.dto.auth.TokenDTO;
import fr.tvmp.irrest.server.common.ToDTO;
import fr.tvmp.irrest.server.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Token implements ToDTO<TokenDTO> {
    @NonNull private String token;

    public Token(@NonNull UserEntity user){
        this.token = user.getId() + "_" + UUID.randomUUID();
    }

    public UUID getUserUUID(){
        return UUID.fromString(token.split("_")[0]);
    }

    @Override
    public TokenDTO toDTO() {
        return new TokenDTO(token);
    }
}
