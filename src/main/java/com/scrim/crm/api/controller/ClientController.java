package com.scrim.crm.api.controller;

import com.scrim.crm.api.model.ClientModel;
import com.scrim.crm.api.model.input.ClientInput;
import com.scrim.crm.domain.service.ClientQueryService;
import com.scrim.crm.domain.service.ClientRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

  private final ClientRegisterService clientRegisterService;
  private final ClientQueryService clientQueryService;

  //@GetMapping
  //public ResponseEntity<List<Client>> list() {
  //  return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
  //}

  @GetMapping("/{clientId}")
  public ResponseEntity<ClientModel> search(@PathVariable UUID clientId) {
    return ResponseEntity.status(HttpStatus.FOUND).body(clientQueryService.search(clientId));
  }

  @PostMapping("/create")
  public ResponseEntity<ClientModel> create(@RequestBody ClientInput input) {
    return ResponseEntity.status(HttpStatus.CREATED).body(clientRegisterService.save(input));
  }

  @DeleteMapping("/{clientId}")
  public ResponseEntity<Void> remove(@PathVariable UUID clientId) {
    if (!clientRegisterService.remove((clientId))) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }

}
