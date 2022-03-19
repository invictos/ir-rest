package fr.tvmp.irrest.common;

import fr.tvmp.irrest.user.UserService;
import lombok.Getter;

import javax.inject.Inject;
import java.util.logging.Logger;

@Getter
public abstract class AbstractService{
    @Inject
    Logger logger;
}