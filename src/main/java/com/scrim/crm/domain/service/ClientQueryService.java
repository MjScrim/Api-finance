package com.scrim.crm.domain.service;

import com.scrim.crm.api.assembler.ClientAssembler;
import com.scrim.crm.api.model.ClientModel;
import com.scrim.crm.domain.exception.EntityNotFoundException;
import com.scrim.crm.domain.model.Client;
import com.scrim.crm.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class ClientQueryService {

  private final ClientRepository clientRepository;
  private final ClientAssembler clientAssembler;

  public ClientModel search(UUID clientId) {
    Client client = clientRepository.findById(clientId)
      .orElseThrow(() -> new EntityNotFoundException("Client not found."));

    return clientAssembler.toModel(client);
  }

}