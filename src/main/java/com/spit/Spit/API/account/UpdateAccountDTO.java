package com.spit.Spit.API.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateAccountDTO {

    private String name;
    private String handle;
}
