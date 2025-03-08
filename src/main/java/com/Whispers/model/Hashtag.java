package com.Whispers.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "hashtag", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Hashtag(String name){
        this.name = name;
    }

    /*
    For when you want to see Posts on the Hashtag side

    @ManyToMany(mappedBy ="hashtags",
                fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Set<Post> posts = new HashSet<>();

     */

    @Override
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", posts=" + posts.size() +
                '}';
    }
}
