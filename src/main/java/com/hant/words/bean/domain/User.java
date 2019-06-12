package com.hant.words.bean.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
  private long id;
  @JsonIgnore
  private String password;
  private String username;
  private List<Role> roles;
  @JsonIgnore
  public void getPassword(String password) {
    this.password = password;
  }
}
