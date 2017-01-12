package org.lockss.laaws.template.api;

import org.lockss.laaws.template.model.*;
import org.lockss.laaws.template.api.EntitiesApiService;
import org.lockss.laaws.template.api.factories.EntitiesApiServiceFactory;

import org.lockss.laaws.template.model.Entity;

import java.util.List;
import org.lockss.laaws.template.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/entities")


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-11-02T10:08:28.161-07:00")
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
