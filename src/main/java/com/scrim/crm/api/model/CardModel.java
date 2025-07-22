package com.scrim.crm.api.model;

import com.scrim.crm.domain.model.Client;
import com.scrim.crm.domain.model.StatusCard;
import com.scrim.crm.domain.model.TypeCard;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class CardModel {

  private UUID id;

  private Client client;
  private String cardNumbers;
  private StatusCard status;
  private TypeCard type;
  private String bank;
  private String cvc;
  private OffsetDateTime dateCreated;
  private OffsetDateTime dateCanceled;

}
