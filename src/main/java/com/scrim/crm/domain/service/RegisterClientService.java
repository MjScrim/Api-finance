package com.scrim.crm.domain.service;

import com.scrim.crm.domain.exception.EntityNotFoundException;
import com.scrim.crm.domain.exception.SystemException;
import com.scrim.crm.domain.model.Client;
import com.scrim.crm.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class RegisterClientService {

  private final ClientRepository clientRepository;

  public void search(UUID clientId) {
    Client client = clientRepository.findById(clientId)
      .orElseThrow(() -> new EntityNotFoundException("Client not found."));
  }

  @Transactional
  public Client save(Client newClient) {
    boolean emailInUse = clientRepository.findByEmail(newClient.getEmail())
      .filter(c -> !c.equals(newClient))
      .isPresent();

    if (emailInUse) {
      throw new SystemException("Email is already in use");
    }

    return clientRepository.save(newClient);
  }

  @Transactional
  public void remove(UUID clientId) {
    clientRepository.deleteById(clientId);
  }

}
