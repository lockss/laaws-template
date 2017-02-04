package org.lockss.laaws.template.api.impl;

import org.lockss.laaws.template.api.EntitiesApiService;
import org.lockss.laaws.template.api.NotFoundException;
import org.lockss.laaws.template.model.Entity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntitiesApiServiceImpl extends EntitiesApiService {
  static List<Entity> entityList;

  static {
    entityList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Entity entity = new Entity();
      entity.setName("entity" + i);
      entity.setValue("entity" + i + "val");
      entity.setId(i);
      entityList.add(entity);
    }
  }

  @Override
  @GET
  @Path("/all")
  @Produces({"application/json"})
  public Response getAllEntities(SecurityContext securityContext)
      throws NotFoundException {
    return Response.ok().entity(entityList).build();
  }

  @Override
  @GET
  @Path("/{id}")
  @Produces({"application/json"})
  public Response getOneEntityById(Integer id, SecurityContext securityContext)
      throws NotFoundException {
    Entity entity = entityList.get(id);
    return Response.ok().entity(entity).build();
  }

  @Override
  @GET
  @Path("/one")
  @Produces({"application/json"})
  public Response getOneRandomEntity(SecurityContext securityContext)
      throws NotFoundException {
    Random rand = new Random();
    int el_num = rand.nextInt(10);
    Entity entity = entityList.get(el_num);
    return Response.ok().entity(entity).build();
  }
}
