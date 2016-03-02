package org.lockss.snafl.tmpl.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lockss.snafl.tmpl.model.Entity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/entity")
@Api (value = "/entity", description = "Simple Service to browse entities")
public class EntityBrowser {
  static List<Entity> entityList;
  static {
    entityList = new ArrayList<>();
    for (int i = 0; i <10; i++) {
      Entity entity = new Entity();
      entity.setName("entity" + i);
      entity.setValue("entity"+i+"val");
      entity.setId(i);
      entityList.add(entity);
    }
  }
  @GET
  @Path("/one")
  @ApiOperation(value = "Return one entity",
    notes = "Returns one entity at random",
    response = Entity.class)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEntity() {
    Random rand = new Random();
    int el_num = rand.nextInt(10);
    Entity entity = entityList.get(el_num);
    return Response.ok().entity(entity).build();
  }

  @GET
  @Path("/all")
  @ApiOperation (value = "Return all entities",
    notes = "Returns all entities in the collection",
    response = Entity.class, responseContainer = "List")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResult() {
    return Response.ok().entity(entityList).build();
  }
}