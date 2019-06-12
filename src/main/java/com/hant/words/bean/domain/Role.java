package com.hant.words.bean.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
  private long id;
  private String role;
}
