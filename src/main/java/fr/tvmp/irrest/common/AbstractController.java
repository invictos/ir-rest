package fr.tvmp.irrest.common;

import fr.tvmp.irrest.user.UserRole;
import lombok.Getter;

import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Logger;

@Getter
@Produces(MediaType.APPLICATION_JSON)
public abstract class AbstractController {
    @Context
    SecurityContext context;

    protected Logger getLogger(){
        return Logger.getLogger(getClass().getName());
    }

    /**
     * Allows user if it has the provided UUID
     * @param uuid
     * @throws ForbiddenException
     */
    protected void authIsUUID(UUID uuid) throws ForbiddenException{
        UUID contextUUID = UUID.fromString(getPrincipal().getName());

        if(!contextUUID.equals(uuid)){
            throw new ForbiddenException();
        }
    }

    /**
     * Allows user if it has the provided UUID, or is ANY of the provided role
     * @param uuid uuid to match
     * @param roles roles to match
     * @throws ForbiddenException if the user doesn't match
     */
    protected void authIsUUIDOrRole(UUID uuid, UserRole... roles) throws ForbiddenException{
        if (Arrays.stream(roles).anyMatch(r -> context.isUserInRole(r.name()))){
            return;
        }
        authIsUUID(uuid);
    }

    /**
     * Returns the authed used UUID
     * @return UUID authed user
     */
    protected UUID getContextUUID() throws ForbiddenException{
        return UUID.fromString(getPrincipal().getName());
    }


    private Principal getPrincipal() throws ForbiddenException{
        Principal principal = getContext().getUserPrincipal();
        if(principal == null){
            getLogger().warning("context not existent caught in controller, should be handled with @Secured");
            throw new ForbiddenException();
        }
        return principal;
    }
}
