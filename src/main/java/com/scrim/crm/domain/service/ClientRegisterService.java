package com.scrim.crm.domain.service;

import com.scrim.crm.api.assembler.ClientAssembler;
import com.scrim.crm.api.model.ClientModel;
import com.scrim.crm.api.model.input.ClientInput;
import com.scrim.crm.domain.exception.DomainException;
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
public class ClientRegisterService {

  private final ClientRepository clientRepository;
  private final ClientAssembler clientAssembler;

  public ClientModel save(ClientInput input) {
    boolean emailInUse = clientRepository.findByEmail(input.getEmail()).isPresent();

    if (emailInUse) {
      throw new DomainException();
    }

    Client client = Client.draft(input.getName(), input.getEmail(), input.getPhone());

    return clientAssembler.toModel(clientRepository.saveAndFlush(client));
  }

  public boolean remove(UUID clientId) {
    boolean exist = clientRepository.existsById(clientId);

    if (exist) {
      clientRepository.deleteById(clientId);

      return true;
    }

    return false;
  }

}
