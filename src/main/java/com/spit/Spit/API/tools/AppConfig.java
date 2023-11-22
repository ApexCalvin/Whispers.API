package com.spit.Spit.API.tools;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountRepository;
import com.spit.Spit.API.comment.Comment;
import com.spit.Spit.API.comment.CommentRepository;
import com.spit.Spit.API.like.LikeRepository;
import com.spit.Spit.API.like._Like;
import com.spit.Spit.API.post.Post;
import com.spit.Spit.API.post.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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

    @Autowired
    LikeRepository likeRepository;

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
        comment1.setAccount(theDeep);
        comment1.setPost(post1);

        Comment comment2 = new Comment();
        comment2.setMessage("Comment 2, post 1");
        comment2.setAccount(stormFront);
        comment2.setPost(post1);

        Comment comment3 = new Comment();
        comment3.setMessage("Comment 1, post 2");
        comment3.setAccount(aTrain);
        comment3.setPost(post2);

        commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3));

        _Like like1 = new _Like();
        like1.setPost(post1);
        like1.setAccount(stormFront);

        _Like like2 = new _Like();
        like2.setPost(post2);
        like2.setAccount(stormFront);

        _Like like3 = new _Like();
        like3.setPost(post1);
        like3.setAccount(homelander);

        _Like like4 = new _Like();
        like4.setPost(post3);
        like4.setAccount(homelander);

        likeRepository.saveAll(Arrays.asList(like1, like2, like3, like4));


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
