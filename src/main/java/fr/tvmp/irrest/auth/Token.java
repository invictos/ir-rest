package fr.tvmp.irrest.auth;

import fr.tvmp.irrest.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Token {
    @NonNull private String token;

    public Token(@NonNull UserEntity user){
        this.token = user.getId() + "_" + UUID.randomUUID().toString();
    }

    public UUID getUserUUID(){
        return UUID.fromString(token.split("_")[0]);
    }

}
