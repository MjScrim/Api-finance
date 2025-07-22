package com.scrim.crm.domain.service;

import com.scrim.crm.domain.exception.EntityNotFoundException;
import com.scrim.crm.domain.model.Card;
import com.scrim.crm.domain.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class RegisterCardService {

  private final CardRepository cardRepository;

  public Card search(UUID cardId) {
    return cardRepository.findById(cardId)
      .orElseThrow(() -> new EntityNotFoundException("Card not found."));
  }

}
