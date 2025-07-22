package com.scrim.crm.common;

import com.scrim.crm.api.model.CardModel;
import com.scrim.crm.domain.model.Card;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();

    modelMapper.createTypeMap(Card.class, CardModel.class)
      .addMappings(mapper -> mapper.map(Card::getCard_numbers, CardModel::setCard_numbers));

    return modelMapper;
  }

}
