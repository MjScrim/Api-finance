package com.scrim.crm.domain.model;

import com.scrim.crm.api.model.input.ClientInput;
import com.scrim.crm.domain.exception.StatusException;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  private String name;
  private String email;
  private String phone;

  private Integer totalCards;

  private List<Card> cards = new ArrayList<>();

  public static Client draft(String name, String email, String phone) {
    Client client = new Client();

    client.setId(UUID.randomUUID());
    client.setName(name);
    client.setEmail(email);
    client.setPhone(phone);

    return client;
  }

  public List<Card> getCards() {
    return Collections.unmodifiableList(cards);
  }

  public UUID addCard(String bank, TypeCard typeCard) {
    Card card = Card.brandNew(bank, typeCard, this);

    this.cards.add(card);

    this.setTotalCards();

    return card.getId();
  }

  public void removeCard(UUID cardId) {
    this.cards.removeIf(card -> card.getId().equals(cardId));

    this.setTotalCards();
  }

  public StatusCard cancelCard(UUID cardId) {
    if (cardIsCanceled(cardId)) {
      throw new StatusException("Card already canceled.");
    }

    Card card = this.getCard(cardId);

    card.setStatus(StatusCard.CANCELED);
    card.setDateCanceled(OffsetDateTime.now());

    return card.getStatus();
  }

  public StatusCard removeCancelCard(UUID cardId) {
    if (!cardIsCanceled(cardId)) {
      throw new StatusException("Card is not canceled.");
    }

    Card card = this.getCard(cardId);

    card.setStatus(StatusCard.ACTIVE);
    card.setDateCanceled(null);

    return card.getStatus();
  }

  private boolean cardIsCanceled(UUID cardId) {
    Card card = this.getCard(cardId);

    return StatusCard.CANCELED.equals(card.getStatus());
  }

  private Card getCard(UUID cardId) {
    return this.getCards()
      .stream()
      .filter(c -> c.getId().equals(cardId))
      .findFirst()
      .orElseThrow();
  }

  private void setTotalCards() {
    this.totalCards = this.getCards().size();
  }

}