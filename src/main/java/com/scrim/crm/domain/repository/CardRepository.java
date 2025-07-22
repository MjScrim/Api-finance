package com.scrim.crm.domain.repository;

import com.scrim.crm.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

  Optional<Card> findByCardNumbers(String cardNumbers);

}
