package org.lockss.laaws.template.api;

import io.swagger.annotations.Api;
import org.lockss.laaws.template.api.factories.EntitiesApiServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("/entities")
@Api(value = "entities", description = "Endpoint for Entity operations")
public class EntitiesApi {
  private final EntitiesApiService delegate = EntitiesApiServiceFactory.getEntitiesApi();

  @GET
  @Path("/all")
  @Produces({"application/json"})
  public Response getAllEntities(@Context SecurityContext securityContext)
      throws NotFoundException {
    return delegate.getAllEntities(securityContext);
  }

  @GET
  @Path("/{id}")
  @Produces({"application/json"})
  public Response getOneEntityById(@PathParam("id") Integer id, @Context SecurityContext securityContext)
      throws NotFoundException {
    return delegate.getOneEntityById(id, securityContext);
  }

  @GET
  @Path("/one")
  @Produces({"application/json"})
  public Response getOneRandomEntity(@Context SecurityContext securityContext)
      throws NotFoundException {
    return delegate.getOneRandomEntity(securityContext);
  }
}
