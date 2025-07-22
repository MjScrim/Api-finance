package com.scrim.crm.domain.model;

import com.scrim.crm.domain.exception.StatusException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private Long id;

  @ManyToOne
  private Client client;

  @Column(nullable = false)
  private String card_numbers;

  @Enumerated(EnumType.STRING)
  private StatusCard status;

  @Column(nullable = false)
  private String bank;

  @Column(nullable = false)
  private String cvc;

  private OffsetDateTime dateCreated;
  private OffsetDateTime dateCanceled;

  public void cancel() {
    if (isCanceled()) {
      throw new StatusException("Card already canceled");
    }

    setStatus(StatusCard.CANCELED);
    setDateCanceled(OffsetDateTime.now());
  }

  public void removeCancel() {
    if (!isCanceled()) {
      throw new StatusException("Card is not canceled");
    }

    setStatus(StatusCard.ACTIVE);
    setDateCanceled(null);
  }

  public boolean isCanceled() {
    return StatusCard.CANCELED.equals(getStatus());
  }

}
