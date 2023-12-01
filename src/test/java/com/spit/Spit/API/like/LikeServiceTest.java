package com.spit.Spit.API.like;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
import com.spit.Spit.API.post.Post;
import com.spit.Spit.API.post.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {

    @InjectMocks
    private LikeService subject;

    @Mock
    private LikeRepository likeRepository;
    @Mock
    private PostService postService;
    @Mock
    private AccountService accountService;

    @Test
    void createLike_SaveLike(){
        CreateLikeDTO createLikeDTO = new CreateLikeDTO();
        createLikeDTO.setPostId(2L);
        createLikeDTO.setAccountHandle("Stormfront");
        Post expectedPost = new Post();
        when(postService.getPostById(createLikeDTO.getPostId())).thenReturn(expectedPost);
        Account expectedAccount = new Account();
        when(accountService.getAccountByHandle(createLikeDTO.getAccountHandle())).thenReturn(expectedAccount);
        ArgumentCaptor<Like_> likeCaptor = ArgumentCaptor.forClass(Like_.class);

        subject.createLike(createLikeDTO);

        verify(likeRepository).save(likeCaptor.capture());
        assertThat(likeCaptor.getValue().getAccount()).isEqualTo(expectedAccount);
        assertThat(likeCaptor.getValue().getPost()).isEqualTo(expectedPost);
    }

    @Test
    void getLikeById_likeExists_returnLike(){
        long id = 1L;
        Like_ expected = new Like_();
        when(likeRepository.findById(id)).thenReturn(Optional.of(expected));

        Like_ actual = subject.getLikeById(id);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getLikeById_likeNotFound_returnNull(){
        long id = 1L;
        when(likeRepository.findById(id)).thenReturn(Optional.empty());

        Like_ actual = subject.getLikeById(id);

        assertThat(actual).isNull();
    }

    @Test
    void getAllLikes_returnAllLikes(){
        List<Like_> expected = Arrays.asList(new Like_(), new Like_());
        when(likeRepository.findAll()).thenReturn(expected);

        List<Like_> actual = subject.getAllLikes();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void deleteLike_likeExists_callsDeleteByIdReturnTrue(){
        long id = 1L;
        Like_ expected = new Like_();
        when(likeRepository.findById(id)).thenReturn(Optional.of(expected));

        boolean actual = subject.deleteLike(id);

        verify(likeRepository).deleteById(id);
        assertThat(actual).isTrue();
    }

    @Test
    void deleteLike_likeNotFound_callsDeleteByIdReturnTrue(){
        long id = 1L;
        when(likeRepository.findById(id)).thenReturn(Optional.empty());

        boolean actual = subject.deleteLike(id);

        verify(likeRepository, never()).deleteById(id);
        assertThat(actual).isFalse();
    }

    @Test
    void getLikeByHandleAndPost_callsFindByAccountHandleAndPostId(){
        long id = 1L;
        String handle = "Homelander";
        Like_ expected = new Like_();
        when(likeRepository.findByAccountHandleAndPostId(handle,id)).thenReturn(expected);

        Like_ actual = subject.getLikeByHandleAndPost(handle,id);

        verify(likeRepository).findByAccountHandleAndPostId(handle,id);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void doesUserAlreadyLikePost_true(){
        long id = 1L;
        String handle = "Homelander";
        Like_ expected = new Like_();
        CreateLikeDTO likeDTO = new CreateLikeDTO();
        likeDTO.setPostId(id);
        likeDTO.setAccountHandle(handle);
        when(likeRepository.findByAccountHandleAndPostId(handle,id)).thenReturn(expected);

        boolean actual = subject.doesUserAlreadyLikePost(likeDTO);

        verify(likeRepository).findByAccountHandleAndPostId(handle,id);
        assertThat(actual).isTrue();
    }

    @Test
    void doesUserAlreadyLikePost_false(){
        long id = 1L;
        String handle = "Homelander";
        CreateLikeDTO likeDTO = new CreateLikeDTO();
        likeDTO.setPostId(id);
        likeDTO.setAccountHandle(handle);
        when(likeRepository.findByAccountHandleAndPostId(handle,id)).thenReturn(null);

        boolean actual = subject.doesUserAlreadyLikePost(likeDTO);

        verify(likeRepository).findByAccountHandleAndPostId(handle,id);
        assertThat(actual).isFalse();
    }
}
