package com.spit.Spit.API.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

//    @ManyToMany(mappedBy ="hashtags",
//                fetch = FetchType.LAZY) ////load associated entities only when explicitly requested
//    @JsonIgnoreProperties
//    private Set<Post> posts = new HashSet<>();

    @Override
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", posts=" + posts.size() +
                '}';
    }
}
