package com.scrim.crm.api.assembler;

import com.scrim.crm.api.model.CardModel;
import com.scrim.crm.api.model.input.CardInput;
import com.scrim.crm.domain.model.Card;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CardAssembler {

  private final ModelMapper modelMapper;

  public Card toEntity(CardInput cardInput) {
    return modelMapper.map(cardInput, Card.class);
  }

  public CardModel toModel(Card card) {
    return modelMapper.map(card, CardModel.class);
  }

  public List<CardModel> toCollectionModel(List<Card> cardList) {
    return cardList.stream()
      .map(this::toModel)
      .toList();
  }

}
