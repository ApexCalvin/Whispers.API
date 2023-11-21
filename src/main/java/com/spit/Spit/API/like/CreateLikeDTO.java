package com.spit.Spit.API.like;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateLikeDTO {

    @NotNull(message = "Required Field.")
    @NotBlank(message = "Cannot Be Blank.")
    private String accountHandle;

    @NotNull(message = "Required Field.")
    private Long postId;

}
