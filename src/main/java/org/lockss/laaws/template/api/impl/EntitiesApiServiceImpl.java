package org.lockss.laaws.template.api.impl;

import org.lockss.laaws.template.api.*;
import org.lockss.laaws.template.model.*;


import org.lockss.laaws.template.model.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lockss.laaws.template.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-11-02T10:08:28.161-07:00")
public class EntitiesApiServiceImpl extends EntitiesApiService {
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
      @Override
      public Response getAllEntities(SecurityContext securityContext)
      throws NotFoundException {
    	return Response.ok().entity(entityList).build();
  	}
      @Override
      public Response getOneEntityById(Integer id,SecurityContext securityContext)
      throws NotFoundException {
    		Entity entity = entityList.get(id);
    		return Response.ok().entity(entity).build();
  		}
      @Override
      public Response getOneRandomEntity(SecurityContext securityContext)
      throws NotFoundException {
    		Random rand = new Random();
    		int el_num = rand.nextInt(10);
    		Entity entity = entityList.get(el_num);
    		return Response.ok().entity(entity).build();
  	}
}
