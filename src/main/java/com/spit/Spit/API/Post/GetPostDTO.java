package com.spit.Spit.API.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetPostDTO {

    private String name;

    private String handle;

    private String message;

    private Date date;
}