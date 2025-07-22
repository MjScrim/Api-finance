package com.scrim.crm.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CardInput {

  @Valid
  @NotNull
  private ClientInput client;

  @NotBlank
  @Size(min = 16, max = 16, message = "Must contain 16 digits.")
  @Pattern(regexp = "\\d{16}", message = "Must contain only numbers.")
  private String card_numbers;

  @NotBlank
  @Size(min = 3, max = 3, message = "Must contain 3 digits.")
  @Pattern(regexp = "\\d{3}", message = "Must contain only numbers.")
  private String cvc;

  @NotBlank
  @Size(max = 20)
  private String bank;


}
