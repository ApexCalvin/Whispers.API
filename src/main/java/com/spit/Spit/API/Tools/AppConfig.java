package com.spit.Spit.API.Tools;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountRepository;
import com.spit.Spit.API.Comment.Comment;
import com.spit.Spit.API.Comment.CommentRepository;
import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Post.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Configuration
public class AppConfig {

    @Autowired
    PostRepository postRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CommentRepository commentRepository;

    @PostConstruct
    public void setup(){

        Account homelander = new Account("John", "Homelander");
        Account theDeep = new Account("Kevin", "The Deep");
        Account aTrain = new Account("Reggie", "A-Train");
        Account stormFront = new Account("Klara", "Stormfront");
        Account mothersmilk = new Account("Marvin", "Mother's Milk");
        accountRepository.saveAll(Arrays.asList(    homelander,
                                                    theDeep,
                                                    aTrain,
                                                    stormFront,
                                                    mothersmilk));

        Date date1 = new Date();
        date1.setTime(1234030615354L); // 02/07/2009 12:16:55
        Post post1 = new Post(date1, "Homelander message 1", homelander);
        postRepository.save(post1);

        Date date2 = new Date();
        date2.setTime(1234837615354L); // 09/11/2009 16:13:35
        Post post2 = new Post(date2, "Mother's Milk message 1", mothersmilk);
        postRepository.save(post2);

        Date date3 = new Date();
        date3.setTime(1234859682534L); // 03/21/2009 16:08:02
        Post post3 = new Post(date3, "Homelander message 2", homelander);
        postRepository.save(post3);

        Comment comment1 = new Comment();
        comment1.setMessage("Comment 1, post 1");
        //comment1.setAccount(2L);
        //comment1.setPost(1L);

        Comment comment2 = new Comment();
        comment2.setMessage("Comment 2, post 1");
        //comment2.setAccount(3L);
        //comment2.setPost(1L);

        Comment comment3 = new Comment();
        comment3.setMessage("Comment 1, post 2");
        //comment3.setAccount(4L);
        //comment4.setPost(2L);

        commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3));

//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            String data = "01/01/2023 15:30:45";
//            Date date = sdf.parse(data);
//            Post post = new Post(date, "Homelander message 1", homelander);
//            postRepository.save(post);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
