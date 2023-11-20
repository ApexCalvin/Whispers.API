package com.spit.Spit.API.Like;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "post_like")
public class PostLike { //like is a reserved word for HQL

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @Column(name = "date")
    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

}
