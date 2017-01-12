package org.lockss.laaws.template.api;

import org.lockss.laaws.template.api.*;
import org.lockss.laaws.template.model.*;


import org.lockss.laaws.template.model.Entity;

import java.util.List;
import org.lockss.laaws.template.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-11-02T10:08:28.161-07:00")
public abstract class EntitiesApiService {
      public abstract Response getAllEntities(SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response getOneEntityById(Integer id,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response getOneRandomEntity(SecurityContext securityContext)
      throws NotFoundException;
}
