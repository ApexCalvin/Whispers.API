package com.spit.Spit.API.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetPostDTO {

    private Long id;

    private String name;

    private String handle;

    private String message;

    private Date date;
}