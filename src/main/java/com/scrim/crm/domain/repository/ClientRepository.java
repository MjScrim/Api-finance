package com.scrim.crm.domain.repository;

import com.scrim.crm.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

  List<Client> findByNameContaining(String name);
  Optional<Client> findByEmail(String email);

}
