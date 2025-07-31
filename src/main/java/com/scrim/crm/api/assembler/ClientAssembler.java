package com.scrim.crm.api.assembler;

import com.scrim.crm.api.model.CardModel;
import com.scrim.crm.api.model.ClientModel;
import com.scrim.crm.api.model.input.CardInput;
import com.scrim.crm.api.model.input.ClientInput;
import com.scrim.crm.domain.model.Card;
import com.scrim.crm.domain.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ClientAssembler {

  private final ModelMapper modelMapper;

  public Client toEntity(ClientInput input) {
    return modelMapper.map(input, Client.class);
  }

  public ClientModel toModel(Client client) {
    return modelMapper.map(client, ClientModel.class);
  }

}
