package com.spit.Spit.API.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCommentDTO {

    private Long accountId;

    private Long postId;

    private String message;
}
