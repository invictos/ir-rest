package fr.tvmp.irrest.server.common;

import fr.tvmp.irrest.server.user.UserRole;
import lombok.Getter;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
     * Returns the authed user UUID
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

    /**
     * Creates a response with custom StatusType
     * @param statusType status
     * @return Response
     */
    protected Response createResponse(Response.StatusType statusType){
        return Response.status(statusType).build();
    }

    /**
     * Creates a response with custom StatusType & serialize given object to DTO
     * @param status status
     * @param obj Object to turn to DTO. Implements ToDTO
     * @return Response
     */
    protected <T> Response createResponse(Response.StatusType status, ToDTO<T> obj){
        return Response.status(status)
                .entity(obj.toDTO())
                .build();
    }

    /**
     * Creates a response with custom StatusType & serialize given objects to DTO
     * @param status status
     * @param objs Collection of object to turn into DTO. each Implements ToDTO
     * @return Response
     */
    private <A, T extends ToDTO<A>> Response createResponse(Response.StatusType status, Collection<T> objs){
        return Response.status(status)
                .entity(objs.stream()
                        .map(ToDTO::toDTO)
                        .collect(Collectors.toList())
                ).build();
    }

    /**
     * Creates an OK response & serialize object
     * @param obj Object to serialize to DTO
     * @return Response
     */
    protected <T> Response ok(ToDTO<? extends T> obj){
        return createResponse(Response.Status.OK, obj);
    }

    /**
     * Creates an OK response & serialize objects
     * @param objs Collection of Objects to serialize to DTO
     * @return Response
     */
    protected <A, T extends ToDTO<A>> Response ok(Collection<T> objs){
        return createResponse(Response.Status.OK, objs);
    }

    /**
     * Create a response & serialize optional object to dto, adds status
     * @param obj Optional object to turn into DTO
     * @param status Response.Status
     * @return Response
     */
    protected <A, T extends ToDTO<A>> Response ifFound(Optional<T> obj, Response.Status status){
        if(obj.isEmpty()){
            return createResponse(Response.Status.NOT_FOUND);
        }
        return createResponse(status, obj.get());
    }

    /**
     * Create an OK response & serialize optional object to dto
     * @param obj Optional object to turn into DTO
     * @return Response
     */
    protected <A, T extends ToDTO<A>> Response ifFound(Optional<T> obj){
        return ifFound(obj, Response.Status.OK);
    }

    /**
     * Create a response & serialize optional collection of objects to dtos & adds status
     * @param objs Optional objects to turn into DTOs
     * @param status Response.Status
     * @return Response
     */
    protected <T, B extends ToDTO<T>, C extends Collection<B>> Response ifFoundCollection(Optional<C> objs, Response.Status status){
        if(objs.isEmpty()){
            return createResponse(Response.Status.NOT_FOUND);
        }
        return createResponse(status, objs.get());
    }

    /**
     * Create an OK response & serialize optional collection of objects to dtos
     * @param objs Optional objects to turn into DTOs
     * @return Response
     */
    protected <T, B extends ToDTO<T>, C extends Collection<B>> Response ifFoundCollection(Optional<C> objs){
        return ifFoundCollection(objs, Response.Status.OK);
    }

}
