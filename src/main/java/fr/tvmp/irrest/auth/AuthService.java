package fr.tvmp.irrest.auth;

import fr.tvmp.irrest.stub.Credentials;
import fr.tvmp.irrest.user.UserEntity;
import fr.tvmp.irrest.user.UserService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class AuthService {
    @Inject
    Logger logger;

    @Inject
    UserService userService;

    static HashMap<UUID, String> tokens = new HashMap<UUID, String>();

    public Optional<String> auth(Credentials credentials){
        //Get user from credentials
        var userOption = userService.getUserByUUID(credentials.getUuid());

        if (!userOption.isPresent()) {
            return Optional.empty();
        }

        //We have a user, test his password
        UserEntity user = userOption.get();

        if (!user.getPassword().equals(credentials.getPassword())){
            return Optional.empty();
        }

        //User auth is ok, create a new token

        logger.info("Logged user "+ user.getId());

        String token = generateToken();

        tokens.put(user.getId(), token);

        return Optional.ofNullable(token);
    }


    private static String generateToken(){
        return UUID.randomUUID().toString();
    }
}
