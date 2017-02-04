package org.lockss.laaws.template.api.impl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lockss.laaws.template.model.Entity;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Arquillian Test code for Entities Api.
 */
@RunWith(Arquillian.class)
public class TestEntitiesApiServiceImpl {
  @ArquillianResource
  private URL url;

  @Deployment(testable = false)
  public static WebArchive createDeployment() {
    return ShrinkWrap.create(WebArchive.class)
        .addPackages(true, "org.lockss.laaws.template.api",
            "org.lockss.laaws.template.api.impl", "org.lockss.laaws.template.model")
        .addClass(TestEntitiesApiServiceImpl.class)
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  public void getAllEntities() throws Exception {
    RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
    reqSpecBuilder.setBaseUri(url.toURI());
    Response response =
        given(reqSpecBuilder.build())
            .when()
            .get("/entities/all")
            .then()
            .contentType(ContentType.JSON)
            .statusCode(200)
            .extract()
            .response();
    List<Entity> actual = Arrays.asList(response.getBody().as(Entity[].class));
    assertThat(actual, is(EntitiesApiServiceImpl.entityList));
  }

  @Test
  public void getOneEntityById() throws Exception {
    RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
    reqSpecBuilder.setBaseUri(url.toURI());
    given(reqSpecBuilder.build())
        .when()
        .get("/entities/3")
        .then()
        .contentType(ContentType.JSON)
        .body("id", equalTo(3),
            "name", equalTo("entity3"),
            "value", equalTo("entity3val"))
        .statusCode(200);

  }

  @Test
  public void getOneRandomEntity() throws Exception {
    RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
    reqSpecBuilder.setBaseUri(url.toURI());
    Response response = given(reqSpecBuilder.build())
        .when()
        .get("/entities/one")
        .then()
        .contentType(ContentType.JSON)
        .statusCode(200)
        .extract()
        .response();
    Entity found = response.getBody().as(Entity.class);
    assertThat(found, isIn(EntitiesApiServiceImpl.entityList));
  }

}
