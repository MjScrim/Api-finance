package com.scrim.crm.domain.service;

import com.scrim.crm.domain.model.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CancelCardService {

  private final RegisterCardService registerCardService;

  @Transactional
  public void cancel(UUID cardId) {
    Card card = registerCardService.search(cardId);
    card.cancel();
  }


  @Transactional
  public void removeCancel(UUID cardId) {
    Card card = registerCardService.search(cardId);
    card.removeCancel();
  }

}
