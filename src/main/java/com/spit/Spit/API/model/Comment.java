package com.spit.Spit.API.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spit.Spit.API.dto.GetCommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NamedNativeQuery(
        name = "getAllCommentsByPostId-query",
        query = """
                SELECT c.id, c.date, c.message, a.name, a.handle FROM comment c
                JOIN account a ON c.account_id = a.id
                WHERE c.post_id = :postId
                """,
        resultSetMapping = "mapToGetCommentDTO"
)

@NamedNativeQuery(
        name = "getAllCommentsByAccountId-query",
        query = """
                SELECT c.id, c.date, c.message, a.name, a.handle FROM comment c
                JOIN account a ON c.account_id = a.id
                WHERE a.id = :accountId
                """,
        resultSetMapping = "mapToGetCommentDTO"
)

//order of ColumnResult matters when mapping
@SqlResultSetMapping(
        name = "mapToGetCommentDTO",
        classes =   @ConstructorResult( targetClass = GetCommentDTO.class,
                                        columns = {
                                                @ColumnResult(name = "id", type = Long.class),
                                                @ColumnResult(name = "date", type = Date.class),
                                                @ColumnResult(name = "message", type = String.class),
                                                @ColumnResult(name = "name", type = String.class),
                                                @ColumnResult(name = "handle", type = String.class),
                                        }))

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date = new Date();
    private String message;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    @Transient
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    public Long getPostId() {
        if(post != null) {
            postId = post.getId();
        }
        return postId;
    }

    public Comment(Post post, Account account, String message) {
        this.post = post;
        this.account = account;
        this.message = message;
    }
}
