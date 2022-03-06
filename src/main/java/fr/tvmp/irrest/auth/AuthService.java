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

    static HashSet<String> tokens = new HashSet<>();

    public Optional<String> auth(Credentials credentials){
        return userService
                .getUserByUUID(credentials.getUuid())
                .flatMap(user -> { //We have a user matching uuid
                    if(!isUserPasswordOK(user, credentials)) {
                        return Optional.empty();
                    }
                    //User password is correct, generating token
                    String token = generateToken(user);
                    tokens.add(token);

                    logger.info("Logged user ("+user.getPrenom()+") with token: " + token);
                    return Optional.of(token);
                });
    }

    private boolean isUserPasswordOK(UserEntity user, Credentials credentials){
        return userService.validateUserPassword(user, credentials.getPassword());
    }

    public boolean isTokenValid(@NonNull String token){
        return isTokenValidUser(token).isPresent();
    }

    public Optional<UUID> isTokenValidUser(@NonNull String token){
        if(!tokens.contains(token)){
            return Optional.empty();
        }
        UUID id = UUID.fromString(token.split("_")[0]);
        return Optional.of(id);
    }

    private static String generateToken(@NonNull UserEntity user){
        return user.getId() + "_" + UUID.randomUUID().toString();
    }
}
