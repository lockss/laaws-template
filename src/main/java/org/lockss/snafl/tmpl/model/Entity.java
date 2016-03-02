package org.lockss.snafl.tmpl.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Entity {
  private String name;
  private String value;
  private Integer id;


  @JsonProperty("name")
  public String getName() {return name;}
  public void setName(String name) {this.name = name;}

  @JsonProperty("value")
  public String getValue() {return value;}
  public void setValue(String value) {this.value = value;}

  @JsonProperty("id")
  public int getId() {return id;}
  public void setId(int id) {this.id = id;}
}