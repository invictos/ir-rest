package fr.tvmp.irrest.server.user;

import fr.tvmp.irrest.server.common.AbstractService;
import lombok.NonNull;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserService extends AbstractService {
    @Inject
    UserDAO userDAO;

    public List<UserEntity> getAllUsers(){
        return userDAO.getAll();
    }

    public Optional<UserEntity> getUserByUUID(@NonNull UUID id){
        return Optional.ofNullable(userDAO.getByUUID(id));
    }

    public <T extends UserEntity> Optional<T> getUserByUUID(@NonNull UUID id, Class<T> userClass){
        return getUserByUUID(id).flatMap(u -> {
            T user = null;
            try{
                user = (userClass.cast(u));
            }catch(ClassCastException e){
                getLogger().info("Can't cast user "+ id + " to class " + userClass.getName());
            }
            return Optional.ofNullable(user);
        });
    }

    public UserEntity addUser(@NonNull UserEntity user){
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userDAO.add(user);
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