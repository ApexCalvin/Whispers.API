package com.spit.Spit.API.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatePostDTO {

    @NotNull(message = "Required Field.")
    private Long accountId;

    @NotNull(message = "Required Field.")
    @NotBlank(message = "Cannot Be Blank.")
    private String message;

    @NotNull(message = "Required Field.")
    private List<String> hashtags;
}
