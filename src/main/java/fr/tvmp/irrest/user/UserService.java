package fr.tvmp.irrest.user;

import fr.tvmp.irrest.stub.Credentials;
import lombok.NonNull;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class UserService {
    @Inject
    UserDAO userDAO;

    @Inject
    Logger logger;

    public Optional<UserEntity> getUserByUUID(@NonNull UUID id){
        return Optional.ofNullable(userDAO.getUserByUUID(id));
    }

    public UserEntity addUser(@NonNull UserEntity user){
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userDAO.addUser(user);
    }

    public boolean validateUserPassword(@NotNull UserEntity user, @NotNull String password) {
        String hashedPassword = hashPassword(password);
        return Objects.equals(user.getPassword(), hashedPassword);
    }

    public UtilisateurRole getUserRole(@NonNull UserEntity user){
        //TODO: Systeme de role: on utilise actuellement la 1e lettre de chaque prénom ...
        //Rendre User avec une méthode abstraite "getRole"
        switch(user.getPrenom().charAt(0)){
            case 'p': return UtilisateurRole.PATIENT;
            case 'm': return UtilisateurRole.MEDECIN;
            case 'a': return UtilisateurRole.ADMINISTRATIF;
        }
        return UtilisateurRole.PATIENT;
    }

    private static String hashPassword(@NotNull String password){
        return String.valueOf(password.hashCode());
    }
}