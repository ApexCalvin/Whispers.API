package com.Whispers.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.Whispers.dto.GetPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "post")
@NamedNativeQuery(
        name = "getAllPostsDesc",
        query = """
                SELECT a.handle, a.name, p.date, p.message, p.id FROM account a
                JOIN post p ON a.id = p.account_id
                ORDER BY p.date DESC
                """,
        resultSetMapping = "mapToGetPostDTO"
)
@NamedNativeQuery(
        name = "getPostsByHandleDesc",
        query = """
                SELECT a.handle, a.name, p.date, p.message, p.id FROM account a
                JOIN post p ON a.id = p.account_id
                WHERE a.handle = :handle
                ORDER BY p.date DESC;
                """,
        resultSetMapping = "mapToGetPostDTO"
)
@NamedNativeQuery(
        name = "getLikedPostsByAccountId",
        query = """
                SELECT p.DATE, p.message, p.id, a.handle, a.name FROM post p
                INNER JOIN like_ l ON p.id = l.post_id
                INNER JOIN account a ON l.account_id = a.id
                WHERE l.account_id = :accountId
                ORDER BY p.date DESC;
                """,
        resultSetMapping = "mapToGetPostDTO"
)
@SqlResultSetMapping(
        name = "mapToGetPostDTO",
        classes =   @ConstructorResult( targetClass = GetPostDTO.class,
                                        columns = {
                                            @ColumnResult(name = "id", type = Long.class),
                                            @ColumnResult(name = "name", type = String.class),
                                            @ColumnResult(name = "handle", type = String.class),
                                            @ColumnResult(name = "date", type = Date.class),
                                            @ColumnResult(name = "message", type = String.class)
                                         }))
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date = new Date();

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @Transient
    private String accountName; // java purposes only, not for db storage

    @Transient
    private String accountHandle; // java purposes only, not for db storage

    @OneToMany( mappedBy = "post",
                cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;

    @OneToMany( mappedBy = "post",
                cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Like_> likes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "xref_post_hashtag",
                joinColumns = @JoinColumn(name = "post_id"),
                inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private Set<Hashtag> hashtags = new HashSet<>();

    public Post(Date date, Account account, String message) {
        this.date = date;
        this.account = account;
        this.message = message;
    }
    public String getAccountHandle() { // for @Transient instance vars
        if (account != null) {
            accountHandle = account.getHandle();
        }
        return accountHandle;
    }

    public String getAccountName() { // for @Transient instance vars
        if (account != null) {
            accountName = account.getName();
        }
        return accountName;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", date=" + date +
                ", message='" + message + '\'' +
                ", accountHandle='" + (account != null ? account.getHandle() : null) + '\'' +
                '}';
    }
}
