package com.spit.Spit.API.hashtag;

import com.spit.Spit.API.post.Post;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "hashtag")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy ="hashtags",
                fetch = FetchType.LAZY) ////load associated entities only when explicitly requested
    private Set<Post> posts = new HashSet<>();
}
