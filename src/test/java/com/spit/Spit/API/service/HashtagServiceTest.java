package com.spit.Spit.API.service;

import com.spit.Spit.API.model.Hashtag;
import com.spit.Spit.API.repository.HashtagRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HashtagServiceTest {

    @InjectMocks
    HashtagService subject;
    @Mock
    HashtagRepository hashtagRepository;

    @Test
    void createHashtags_singular_newHashtag() {
        List<String> hashtags = new ArrayList<>();
        hashtags.add("first");
        when(hashtagRepository.findByName("first")).thenReturn(null);
        ArgumentCaptor<Hashtag> hashtagArgumentCaptor = ArgumentCaptor.forClass(Hashtag.class);

        Set<Hashtag> actual = subject.createHashtags(hashtags);

        verify(hashtagRepository).save(hashtagArgumentCaptor.capture());
        assertThat(hashtagArgumentCaptor.getValue().getName()).isEqualTo("first");
        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    void createHashtags_singular_existingHashtag() {
        List<String> hashtags = new ArrayList<>();
        hashtags.add("first");
        Hashtag h = new Hashtag();
        h.setName("first");
        when(hashtagRepository.findByName("first")).thenReturn(h);

        Set<Hashtag> actual = subject.createHashtags(hashtags);

        verify(hashtagRepository, never()).save(any());
        assertThat(actual.stream().findFirst().get().getName()).isEqualTo(h.getName());
        assertThat(actual.size()).isEqualTo(1);
    }

    @Disabled
    @Test
    void createHashtags_plural() {
        List<String> hashtags = new ArrayList<>();
        hashtags.add("first");
        hashtags.add("second");
        hashtags.add("third");
        //when().thenReturn();

        Set<Hashtag> actual = subject.createHashtags(hashtags);

        //verify(hashtagRepository, times(1)).save()
        assertThat(actual.size()).isEqualTo(3);
    }

    @Disabled
    @Test
    void createHashtags_sendEmptyList() {
        List<String> hashtags = new ArrayList<>();
        //when().thenReturn(null);

        Set<Hashtag> actual = subject.createHashtags(hashtags);

        assertThat(actual.size()).isEqualTo(null);
    }

    @Test
    @DisplayName("When hashtag does not exist returns there already existing hashtag from database")
    void createHashTags_hashtagDoesNotExistAlready() {
        when(hashtagRepository.findByName(any(String.class))).thenReturn(null); //don't mock subjects behavior only mock external dependencies when necessary

        Set<Hashtag> actual = subject.createHashtags(Arrays.asList("1", "2"));

        verify(hashtagRepository, times(2)).save(any(Hashtag.class));
        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("When hashtag already exists return hashtag from database")
    void createHashTags_hashtagAlreadyExists_doesNotSaveNewHashtagReturnExisting() {
        Hashtag expected = new Hashtag();
        expected.setName("Test");
        when(hashtagRepository.findByName("Test")).thenReturn(expected);

        Set<Hashtag> actual = subject.createHashtags(Arrays.asList("Test"));

        verify(hashtagRepository, never()).save(any(Hashtag.class));
        assertThat(actual).contains(expected);
    }

    @Test
    @DisplayName("Filter out duplicate incoming hashtags save 1 time only")
    void createHashTags_duplicateHashtags() {
        when(hashtagRepository.findByName(any(String.class))).thenReturn(null);

        Set<Hashtag> actual = subject.createHashtags(Arrays.asList("1", "1"));

        verify(hashtagRepository, times(1)).save(any(Hashtag.class));
        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    void getHashtagById() {
        Long id = 1L;
        Hashtag expected = new Hashtag();
        when(hashtagRepository.findById(id)).thenReturn(Optional.of(expected));

        Hashtag actual = subject.getHashtagById(id);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getHashtagById_returnNull() {
        Long id = 1L;
        when(hashtagRepository.findById(id)).thenReturn(Optional.empty());

        Hashtag actual = subject.getHashtagById(id);

        assertThat(actual).isEqualTo(null);
    }

    @Test
    void getHashtagByName() {
        String name = "test";
        Hashtag expected = new Hashtag();
        expected.setName(name);
        when(hashtagRepository.findByName(name)).thenReturn(expected);

        Hashtag actual = subject.getHashtagByName(name);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getHashtagByName_returnNull() {
        String name = "test";
        when(hashtagRepository.findByName(name)).thenReturn(null);

        Hashtag actual = subject.getHashtagByName(name);

        assertThat(actual).isEqualTo(null);
    }

    @Test
    void getAllHashtags() {
        List<Hashtag> hashtags = new ArrayList<>(Arrays.asList(new Hashtag(), new Hashtag()));
        when(hashtagRepository.findAll()).thenReturn(hashtags);

        List<Hashtag> actual = subject.getAllHashtags();

        assertThat(actual).isEqualTo(hashtags);
    }

    @Test
    void isNewHashtag_true() {
        String name = "Test";
        when(hashtagRepository.findByName(name)).thenReturn(null);

        boolean actual = subject.isNewHashtag(name);

        assertThat(actual).isTrue();
    }

    @Test
    void isNewHashtag_false() {
        String name = "Test";
        Hashtag hashtag = new Hashtag();
        when(subject.getHashtagByName(name)).thenReturn(hashtag);

        boolean actual = subject.isNewHashtag(name);

        assertThat(actual).isFalse();
    }
}
