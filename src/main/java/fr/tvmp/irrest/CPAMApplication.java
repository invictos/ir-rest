package fr.tvmp.irrest;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.logging.Logger;

@ApplicationPath("/api")
public class CPAMApplication extends Application {

    @Getter
    static private final String PERSISTANCE_UNIT_NAME = "prodPU"; // prodPU si postgresql, devPU si h2
    static private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);

    /**
     * For logger injection. (for Controller & Service loggers, see AbstractController & ServiceController)
     * @param ip InjectionPoint
     * @return Logger
     */
    @Produces
    public Logger getLogger(@NotNull InjectionPoint ip) {
        String category = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(category);
    }

    /**
     * For entityManager injection
     * @return EntityManager
     */
    @Produces
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}