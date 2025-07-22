package com.scrim.crm.domain.service;

import com.scrim.crm.domain.exception.EntityNotFoundException;
import com.scrim.crm.domain.exception.SystemException;
import com.scrim.crm.domain.model.Card;
import com.scrim.crm.domain.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class RegisterCardService {

  private final CardRepository cardRepository;

  public Card search(UUID cardId) {
    return cardRepository.findById(cardId)
      .orElseThrow(() -> new EntityNotFoundException("Card not found."));
  }

  @Transactional
  public Card save(Card newCard) {
    if (newCard.getId() != null) {
      throw new SystemException("Card to be registered must not have an id.");
    }

    boolean numberInUse = cardRepository.findByNumbers(newCard.getCard_numbers())
      .filter(c -> !c.equals(newCard))
      .isPresent();

    if (numberInUse) {
      throw new SystemException("There is already a card registered with this number.");
    }

    return cardRepository.save(newCard);
  }

  @Transactional
  public void remove(UUID cardId) {
    cardRepository.deleteById(cardId);
  }

}
