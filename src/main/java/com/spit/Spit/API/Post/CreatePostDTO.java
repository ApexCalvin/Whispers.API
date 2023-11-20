package com.spit.Spit.API.Post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatePostDTO {

    @NotNull(message = "Required Field.")
    @NotBlank(message = "Cannot Be Blank.")
    private Long accountId;

    @NotNull(message = "Required Field.")
    @NotBlank(message = "Cannot Be Blank.")
    private String message;
}
