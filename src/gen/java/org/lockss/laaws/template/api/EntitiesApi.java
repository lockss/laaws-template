package org.lockss.laaws.template.api;

import org.lockss.laaws.template.model.*;
import org.lockss.laaws.template.api.EntitiesApiService;
import org.lockss.laaws.template.api.factories.EntitiesApiServiceFactory;
import org.lockss.laaws.template.model.Entity;
import org.lockss.laaws.template.api.NotFoundException;

import java.util.List;
import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

import io.swagger.annotations.Api;


@Path("/entities")
@Api(value= "entities", description = "Endpoint for Entity operations")
public class EntitiesApi  {
   private final EntitiesApiService delegate = EntitiesApiServiceFactory.getEntitiesApi();

    @GET
    @Path("/all")
    @Produces({ "application/json" })
    public Response getAllEntities(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAllEntities(securityContext);
    }
    
    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    public Response getOneEntityById( @PathParam("id") Integer id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getOneEntityById(id,securityContext);
    }
    
    @GET
    @Path("/one")
    @Produces({ "application/json" })
    public Response getOneRandomEntity(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getOneRandomEntity(securityContext);
    }
}
