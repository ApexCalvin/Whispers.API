package com.spit.Spit.API.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Post.GetPostDTO;
import com.spit.Spit.API.Post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NamedNativeQuery(
        name = "getAllCommentsByPost-query",
        query = """
                SELECT c.id, c.date, c.message FROM comment c
                WHERE c.post_id = :postId
                """,
        resultSetMapping = "mapToGetCommentDTO"
)

@SqlResultSetMapping(
        name = "mapToGetCommentDTO",
        classes =   @ConstructorResult( targetClass = GetCommentDTO.class,
                                        columns = {
                                                @ColumnResult(name = "id", type = Long.class),
                                                @ColumnResult(name = "date", type = Date.class),
                                                @ColumnResult(name = "message", type = String.class)
//                                                @ColumnResult(name = "accountName", type = String.class),
//                                                @ColumnResult(name = "accountHandle", type = String.class)
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
//
//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private Account account;

    public Long getPostId() {
        if(post != null) {
            postId = post.getId();
        }
        return postId;
    }
}
