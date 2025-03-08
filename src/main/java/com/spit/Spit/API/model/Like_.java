package com.spit.Spit.API.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "like_")
public class Like_ { //like is a reserved word for Hibernate Query Language (HQL)

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

    @Transient
    private String handle;
    @Transient
    private Long postIdLiked;
    @Transient
    private String accountName;

    public String getHandle() {
        if (account != null) {
            handle = account.getHandle();
        }
        return handle;
    }

    public String getAccountName() {
        if (account != null) {
            accountName = account.getName();
        }
        return accountName;
    }

    public Long getPostIdLiked() {
        if(post != null) {
            postIdLiked = post.getId();
        }
        return postIdLiked;
    }

    @Override
    public String toString() {
        return "PostLike{" +
                "id=" + id +
                ", date=" + date +
                ", handle='" + handle + '\'' +
                ", postIdLiked=" + postIdLiked +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
