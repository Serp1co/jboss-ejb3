package com.redhat.example;

import javax.persistence.*;

@Table(schema = "test_schema")
@Entity(name = "second_model")
public class SecondModel {
  @Id
  private String value;

  public SecondModel() {
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
