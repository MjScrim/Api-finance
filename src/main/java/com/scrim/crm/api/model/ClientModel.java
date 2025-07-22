package com.scrim.crm.api.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ClientModel {

  private UUID id;

  private String name;
  private String email;

}
