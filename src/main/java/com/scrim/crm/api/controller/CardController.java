package com.scrim.crm.api.controller;

import com.scrim.crm.api.assembler.CardAssembler;
import com.scrim.crm.api.model.CardModel;
import com.scrim.crm.api.model.input.CardInput;
import com.scrim.crm.domain.model.Card;
import com.scrim.crm.domain.repository.CardRepository;
import com.scrim.crm.domain.service.CancelCardService;
import com.scrim.crm.domain.service.RegisterCardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardController {

  private final RegisterCardService registerCardService;
  private final CancelCardService cancelCardService;
  private final CardRepository cardRepository;
  private final CardAssembler cardAssembler;

  @GetMapping
  public ResponseEntity<List<CardModel>> list() {
    return ResponseEntity.status(HttpStatus.OK)
      .body(cardAssembler.toCollectionModel(cardRepository.findAll()));
  }

  @GetMapping("/{cardId}")
  public ResponseEntity<CardModel> search(@PathVariable UUID cardId) {
    return cardRepository.findById(cardId)
      .map(cardAssembler::toModel)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/create")
  public ResponseEntity<CardModel> create(@Valid @RequestBody CardInput cardInput) {
    Card newCard = cardAssembler.toEntity(cardInput);
    Card cardCreated = registerCardService.save(newCard);

    return ResponseEntity.status(HttpStatus.CREATED).body(cardAssembler.toModel(cardCreated));
  }

  @PutMapping("/{cardId}/cancel")
  public ResponseEntity<Void> cancel(@PathVariable UUID cardId) {
    if (!cardRepository.existsById(cardId)) {
      return ResponseEntity.notFound().build();
    }

    cancelCardService.cancel(cardId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{cardId}")
  public ResponseEntity<Void> remove(@PathVariable UUID cardId) {
    if (!cardRepository.existsById(cardId)) {
      return ResponseEntity.noContent().build();
    }

    registerCardService.remove(cardId);
    return ResponseEntity.noContent().build();
  }

}
