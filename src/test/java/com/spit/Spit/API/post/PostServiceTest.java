package com.spit.Spit.API.post;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Post.PostRepository;
import com.spit.Spit.API.Post.PostServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks //dependency injection for the mock beans
    PostServices postServices; //convention to the name the test object "subject"

    @Mock
    PostRepository postRepository;

    @Test
    void createPost_comparingMessages() throws ParseException {

        Post post = createDummyData_1post();

        String actual = postServices.createPost(post);

        String expected = "Post with id " +post.getId()+ " has been saved.";

        assertThat(actual).isEqualTo(expected);
    }

    //verify(postRepository).save(any(Post.class)); //verify method was called

    @Test
    void getPostById_comparingIds() throws ParseException {

        Post post = createDummyData_1post();
        postServices.createPost(post);


        Optional<Post> receivedPost = postServices.getPostById(post.getId());

        System.out.println("ReceivedPost id: " +post.toString());


    }

    @Test
    void deletePostById() {
        Post post = new Post();

        postServices.createPost(post);

        boolean is1 = postServices.getAllPost().size() == 1;

        postServices.deletePostById(post.getId());

        boolean is0 = postServices.getAllPost().isEmpty();

        assertThat(is1).isEqualTo(is0);
    }

    @Test
    void getAllPost() {
        List<Post> expected = new ArrayList<>();

        Post post1 = new Post();
        Post post2 = new Post();
        expected.add(post1);
        expected.add(post2);

        postServices.createPost(post1);
        postServices.createPost(post2);

        List <Post> actual = postServices.getAllPost();

        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    void getAllPostDesc() throws ParseException {
        createDummyData_3posts_andSaves();
        List<Post> all = postServices.getAllPost();
        Post firstPost = all.get(0);
        assertThat(firstPost.getMessage()).isEqualTo("Chapter 2");
    }

    @Test
    void getAllPostByHandleDesc() throws ParseException {
        createDummyData_3posts_andSaves();
        //List<Post> all = postServices.getPostByHandleDESC("Babayaga");

    }

    public Post createDummyData_1post() throws ParseException {

        Account account = new Account("Johnny", "Test");
        Date date = null;
        String message = "Hello World!";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = "01/01/2001 01:01:01";
            date = sdf.parse(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Post(1L, date, message, account);
    }

    void createDummyData_3posts_andSaves() throws ParseException {

        Post post = createDummyData_1post();
        postRepository.save(post);

        Account account = new Account("John Wick", "BabaYaga");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = "02/02/2002 02:20:00";
            Date date = sdf.parse(data);
            Post post1 = new Post(date, "Chapter 1", account);
            postRepository.save(post1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = "03/03/2023 03:30:00";
            Date date = sdf.parse(data);
            Post post2 = new Post(date, "Chapter 2", account);
            postRepository.save(post2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
