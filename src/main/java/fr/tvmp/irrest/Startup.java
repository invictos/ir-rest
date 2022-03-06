package fr.tvmp.irrest;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.stub.UserForm;
import fr.tvmp.irrest.user.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.logging.Logger;


@ApplicationScoped
public class Startup {

    @Inject
    private UserService userService;

    @Inject
    private Logger logger;

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object pointless) {
        addFakeUsers();
    }

    public void addFakeUsers(){
        //TODO: Systeme de role: on utilise actuellement la 1e lettre de chaque prénom ...
        userService.addUser(new UserForm("paul", "mcCain", "1234", new Adresse("rue de Lille", "Lille")).toEntity());
        userService.addUser(new UserForm("adrien", "lapierre", "1234", new Adresse("rue de Dijon", "Dijon")).toEntity());
        userService.addUser(new UserForm("manon", "dumont", "1234", new Adresse("rue de Marseille", "Marseille")).toEntity());

        logger.info("Added fake users");
    }
}

