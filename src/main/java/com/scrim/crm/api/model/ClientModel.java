package com.scrim.crm.api.model;

import com.scrim.crm.domain.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ClientModel {

  private UUID id;

  private String name;
  private String email;
  private String phone;

  private Integer totalCards;

  private List<Card> cards = new ArrayList<>();

}
