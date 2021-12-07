package com.redhat.example;

import javax.persistence.*;

@Table(schema = "test_schema")
@Entity(name = "model")
public class Model {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String random;
  private String updated;

  public Model() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRandom() {
    return random;
  }

  public void setRandom(String random) {
    this.random = random;
  }

  public String getUpdated() {
    return updated;
  }

  public void setUpdated(String updated) {
    this.updated = updated;
  }

  @Override
  public String toString() {
    return "Model{" +
        "id=" + id +
        ", random='" + random + '\'' +
        ", updated='" + updated + '\'' +
        '}';
  }
}
