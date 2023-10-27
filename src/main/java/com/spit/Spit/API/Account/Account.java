package com.spit.Spit.API.Account;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spit.Spit.API.Post.Post;
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
                cascade = CascadeType.ALL) //deleting the account also deletes children (posts)
    @JsonManagedReference
    private List<Post> posts;

    public Account(String name, String handle) {
        this.name = name;
        this.handle = handle;
    }
}
