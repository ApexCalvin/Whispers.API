package com.spit.Spit.API.Account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateAccountDTO {

    @NotNull(message = "Required Field.")
    @NotBlank(message = "Cannot Be Blank.")
    private String name;

    @NotNull(message = "Required Field.")
    @NotBlank(message = "Cannot Be Blank.")
    private String handle;

}
