package fr.tvmp.irrest.auth;

import fr.tvmp.irrest.stub.Credentials;
import fr.tvmp.irrest.user.UserEntity;
import fr.tvmp.irrest.user.UserService;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class AuthService {
    @Inject
    Logger logger;

    @Inject
    UserService userService;

    static HashSet<Token> tokens = new HashSet<>();

    public Optional<Token> auth(Credentials credentials){
        return userService
                .getUserByUUID(credentials.getUuid())
                .flatMap(user -> { //We have a user matching uuid
                    if(!isUserPasswordOK(user, credentials)) {
                        return Optional.empty();
                    }
                    //User password is correct, generating token
                    Token token = new Token(user);
                    tokens.add(token);

                    logger.info("Logged user ("+user.getPrenom()+") with token: " + token);
                    return Optional.of(token);
                });
    }

    private boolean isUserPasswordOK(UserEntity user, Credentials credentials){
        return userService.validateUserPassword(user, credentials.getPassword());
    }

    public boolean isTokenValid(@NonNull Token token){
        return tokens.contains(token);
    }
}
