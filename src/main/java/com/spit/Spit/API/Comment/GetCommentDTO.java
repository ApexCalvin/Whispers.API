package com.spit.Spit.API.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetCommentDTO {

    private Long id;

    private Date date;

    private String message;

    private String accountName;

    private String accountHandle;
}
