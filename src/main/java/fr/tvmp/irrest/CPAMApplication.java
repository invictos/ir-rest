package fr.tvmp.irrest;

import org.jetbrains.annotations.NotNull;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.logging.Logger;

@ApplicationPath("/api")
public class CPAMApplication extends Application {

    static private final String PERSISTANCE_UNIT_NAME = "prodPU"; // prodPU si postgresql, devPU si h2
    static private EntityManager em;

    @Produces
    public Logger getLogger(@NotNull InjectionPoint ip) {
        String category = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(category);
    }

    @Produces
    public EntityManager getEntityManager(){
        if (em == null) {
            em = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME).createEntityManager();
        }

        return em;
    }
}