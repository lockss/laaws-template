package org.lockss.laaws.template.api.factories;

import org.lockss.laaws.template.api.EntitiesApiService;
import org.lockss.laaws.template.api.impl.EntitiesApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-11-02T10:08:28.161-07:00")
public class EntitiesApiServiceFactory {

  private final static EntitiesApiService service = new EntitiesApiServiceImpl();

  public static EntitiesApiService getEntitiesApi() {
    return service;
  }
}
