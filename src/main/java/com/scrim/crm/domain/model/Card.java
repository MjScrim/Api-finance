package com.scrim.crm.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Card {

  @Id
  @EqualsAndHashCode.Include
  private UUID id;

  @ManyToOne(optional = false)
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private Client client;

  private String cardNumbers;

  @Enumerated(EnumType.STRING)
  private StatusCard status;

  @Enumerated(EnumType.STRING)
  @Setter(AccessLevel.PRIVATE)
  @Getter(AccessLevel.PRIVATE)
  private TypeCard type;

  private String bank;
  private String cvc;

  private OffsetDateTime dateCreated;
  private OffsetDateTime dateCanceled;

  @Setter(AccessLevel.PRIVATE)
  @Getter(AccessLevel.PRIVATE)
  private static SecureRandom random;

  static Card brandNew(String bank, TypeCard typeCard, Client client) {
    Card card = new Card();

    card.setId(UUID.randomUUID());
    card.setCardNumbers(card.setCardNumbers());
    card.setBank(bank);
    card.setType(typeCard);
    card.setCvc(String.format("%03d", random.nextInt(1000)));
    card.setClient(client);

    return card;
  }

  private String setCardNumbers() {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < 4; i++) {
      int block = random.nextInt(10000);
      stringBuilder.append(String.format("%04d", block));
    }

    return stringBuilder.toString();
  }

}
