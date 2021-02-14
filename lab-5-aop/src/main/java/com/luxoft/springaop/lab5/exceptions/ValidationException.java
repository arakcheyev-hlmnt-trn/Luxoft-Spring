package com.luxoft.springaop.lab5.exceptions;

import java.util.logging.Logger;

public class ValidationException extends RuntimeException {
  private final static Logger LOG = Logger.getLogger(ValidationException.class.getName());

  public ValidationException(String message) {
    super(message);
    LOG.info(message);
  }
}
