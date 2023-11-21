package com.spit.Spit.API.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
/*
Subject to deletion. @Transient covers all data you'd want from this
 */
public class GetLikeDTO {
    private Long id;

    private Date date;

    private Long postId;

    private String accountName;

    private String accountHandle;
}
