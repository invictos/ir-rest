package fr.tvmp.irrest.user;

import lombok.NonNull;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class UserService {
    @Inject
    UserDAO userDAO;

    @Inject
    Logger logger;

    public List<UserEntity> getAllUsers(){
        return userDAO.getAll();
    }

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

    public UserRole getUserRole(@NonNull UserEntity user){
        return user.getRole();
    }

    private static String hashPassword(@NotNull String password){
        return String.valueOf(password.hashCode());
    }
}