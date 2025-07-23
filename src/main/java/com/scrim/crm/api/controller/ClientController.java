package com.scrim.crm.api.controller;

import com.scrim.crm.domain.model.Client;
import com.scrim.crm.domain.repository.ClientRepository;
import com.scrim.crm.domain.service.RegisterClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

  private final ClientRepository clientRepository;
  private final RegisterClientService registerClientService;

  @GetMapping
  public ResponseEntity<List<Client>> list() {
    return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
  }

  @GetMapping("/{clientId}")
  public ResponseEntity<Client> search(@PathVariable UUID clientId) {
    return clientRepository.findById(clientId)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/create")
  public ResponseEntity<Client> create(@RequestBody Client client) {
    Client newClient = registerClientService.save(client);

    return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
  }

  @DeleteMapping("/{clientId}")
  public ResponseEntity<Void> remove(@PathVariable UUID clientId) {
    if (!clientRepository.existsById(clientId)) {
      return ResponseEntity.notFound().build();
    }

    registerClientService.remove(clientId);
    return ResponseEntity.noContent().build();
  }

}
