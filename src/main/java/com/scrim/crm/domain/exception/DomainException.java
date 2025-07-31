package com.scrim.crm.domain.exception;

public class DomainException extends RuntimeException {
  public DomainException() {
  }

  public DomainException(String message) {
    super(message);
  }
}
