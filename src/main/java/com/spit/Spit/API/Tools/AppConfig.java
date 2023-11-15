package com.spit.Spit.API.Tools;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountRepository;
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

    @PostConstruct
    public void setup(){

        Account homelander = new Account("John", "Homelander");
        Account mothersmilk = new Account("Marvin", "Mother's Milk");
        accountRepository.saveAll(Arrays.asList(homelander, mothersmilk));

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
