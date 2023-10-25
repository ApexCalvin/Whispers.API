package com.spit.Spit.API.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spit.Spit.API.Post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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
                targetEntity = Post.class,
                fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Post> posts;

    public Account(String name, String handle) { // AppConfig
        this.name = name;
        this.handle = handle;
    }
}
