package com.scrim.crm.infrastructure.config;

import com.scrim.crm.api.model.ClientModel;
import com.scrim.crm.domain.model.Client;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();

    modelMapper.createTypeMap(Client.class, ClientModel.class)
      .addMappings(mapper -> mapper.map(Client::getCards, ClientModel::setCards));

    return modelMapper;
  }

}
