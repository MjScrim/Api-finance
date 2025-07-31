package com.scrim.crm.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientInput {

  @NotBlank
  private String name;

  @NotBlank
  private String email;

  @NotBlank
  private String phone;

}
