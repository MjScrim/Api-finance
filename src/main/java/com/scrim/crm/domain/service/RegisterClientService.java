package com.scrim.crm.domain.service;

import com.scrim.crm.domain.exception.EntityNotFoundException;
import com.scrim.crm.domain.model.Client;
import com.scrim.crm.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class RegisterClientService {

  private final ClientRepository clientRepository;

  public void search(UUID clientId) {
    Client client = clientRepository.findById(clientId)
      .orElseThrow(() -> new EntityNotFoundException("Client not found."));
  }

}
