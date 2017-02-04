package org.lockss.laaws.template.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public abstract class EntitiesApiService {
  public abstract Response getAllEntities(SecurityContext securityContext)
      throws NotFoundException;

  public abstract Response getOneEntityById(Integer id, SecurityContext securityContext)
      throws NotFoundException;

  public abstract Response getOneRandomEntity(SecurityContext securityContext)
      throws NotFoundException;
}
