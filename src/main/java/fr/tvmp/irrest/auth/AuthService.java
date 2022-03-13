package fr.tvmp.irrest.auth;

import fr.tvmp.irrest.common.AbstractService;
import fr.tvmp.irrest.dto.CredentialsDTO;
import fr.tvmp.irrest.user.UserEntity;
import fr.tvmp.irrest.user.UserService;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;

public class AuthService extends AbstractService {
    @Inject
    UserService userService;

    static HashSet<Token> tokens = new HashSet<>();

    public Optional<Token> createToken(CredentialsDTO credentialsDTO){
        return userService
                .getUserByUUID(credentialsDTO.getUuid())
                .flatMap(user -> { //We have a user matching uuid
                    if(!isUserPasswordOK(user, credentialsDTO)) {
                        return Optional.empty();
                    }
                    //User password is correct, generating token
                    Token token = new Token(user);
                    tokens.add(token);

                    getLogger().info("Logged user ("+user.getPrenom()+") with token: " + token);
                    return Optional.of(token);
                });
    }

    public boolean validateToken(@NonNull Token token){
        return tokens.contains(token);
    }

    private boolean isUserPasswordOK(UserEntity user, CredentialsDTO credentialsDTO){
        return userService.validateUserPassword(user, credentialsDTO.getPassword());
    }

}
