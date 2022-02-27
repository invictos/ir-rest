package fr.tvmp.irrest.user;

import lombok.NonNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.UUID;

public class UserService {
    @Inject
    UserDAO userDAO;

    public UserEntity getUserByUUID(@NonNull UUID id){
        return userDAO.getUserByUUID(id);
    }

    public void addUser(@NonNull UserEntity user){
        userDAO.addUser(user);
    }
}