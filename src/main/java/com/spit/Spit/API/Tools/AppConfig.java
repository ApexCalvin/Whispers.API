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

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = "01/01/2023 15:30:45";
            Date date = sdf.parse(data);
            Post post = new Post(date, "Homelander message 1", homelander);
            postRepository.save(post);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = "02/02/2023 02:00:23";
            Date date = sdf.parse(data);
            Post post = new Post(date, "Mother's Milk message 1", mothersmilk);
            postRepository.save(post);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = "03/03/2023 23:36:45";
            Date date = sdf.parse(data);
            Post post = new Post(date, "Homelander message 2", homelander);
            postRepository.save(post);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
