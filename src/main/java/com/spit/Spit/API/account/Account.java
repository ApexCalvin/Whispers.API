package com.spit.Spit.API.account;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spit.Spit.API.comment.Comment;
import com.spit.Spit.API.like._Like;
import com.spit.Spit.API.post.Post;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long account_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "handle", nullable = false)
    private String handle;

    @OneToMany( mappedBy = "account",
                cascade = CascadeType.ALL) //deleting the account also deletes children (posts, comments)
    @JsonManagedReference
    private List<Post> posts;

    @OneToMany( mappedBy = "account",
                cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;

    @OneToMany( mappedBy = "account",
                cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<_Like> likes;

    public Account(String name, String handle) {
        this.name = name;
        this.handle = handle;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", name='" + name + '\'' +
                ", handle='" + handle + '\'' +
                '}';
    }
}
