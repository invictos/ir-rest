package fr.tvmp.irrest.server.auth;

import fr.tvmp.irrest.server.common.AbstractService;
import fr.tvmp.irrest.common.dto.auth.CredentialsDTO;
import fr.tvmp.irrest.server.user.UserEntity;
import fr.tvmp.irrest.server.user.UserService;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;

public class AuthService extends AbstractService {
    @Inject
    UserService userService;

    static HashSet<Token> tokens = new HashSet<>();

    public Optional<Token> createToken(CredentialsDTO credentials){
        return userService
                .getUserByUUID(credentials.getUuid())
                .flatMap(user -> { //We have a user matching uuid
                    if(!isUserPasswordOK(user, credentials)) {
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

    private boolean isUserPasswordOK(UserEntity user, CredentialsDTO credentials){
        return userService.validateUserPassword(user, credentials.getPassword());
    }

}
