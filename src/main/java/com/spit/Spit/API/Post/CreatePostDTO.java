package com.spit.Spit.API.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreatePostDTO {

    private Long accountId;

    private String message;
}
