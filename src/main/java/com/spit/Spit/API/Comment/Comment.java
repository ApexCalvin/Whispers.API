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

//@NamedNativeQuery(
//        name = "getAllPostsDesc-query",
//        query = """
//                SELECT a.handle, a.name, p.date, p.message FROM account a
//                JOIN post p ON a.account_id = p.account_id
//                ORDER BY p.date DESC
//                """,
//        resultSetMapping = "mapToGetPostDTO"
//)

//@SqlResultSetMapping(
//        name = "mapToGetPostDTO",
//        classes =   @ConstructorResult( targetClass = GetPostDTO.class,
//                columns = {
//                        @ColumnResult(name = "name", type = String.class),
//                        @ColumnResult(name = "handle", type = String.class),
//                        @ColumnResult(name = "date", type = Date.class),
//                        @ColumnResult(name = "message", type = String.class)
//                }))

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date = new Date();
    private String message;

//    @ManyToOne
//    @JoinColumn(name = "id")
//    @JsonBackReference
//    private Post post;
//
//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private Account account;
}
