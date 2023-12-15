package com.spit.Spit.API.service;

import com.spit.Spit.API.model.Hashtag;
import com.spit.Spit.API.repository.HashtagRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HashtagServiceTest {

    @InjectMocks
    HashtagService subject;
    @Mock
    HashtagRepository hashtagRepository;

    @Disabled
    @Test
    void createHashtags_singular() {

    }

    @Disabled
    @Test
    void createHashtags_plural() {

    }

    @Test
    void validateAndSaveHashtag_newHashtag() {
        when(subject.getHashtagByName(any(String.class))).thenReturn(null);

        Hashtag actual = subject.validateAndSaveHashtag("Test");

        verify(hashtagRepository).save(any(Hashtag.class));
        assertThat(actual.getName()).isEqualTo("Test");
    }

    @Test
    void validateAndSaveHashtag_existingHashtag() {
        Hashtag expected = new Hashtag();
        expected.setName("Test");
        when(subject.getHashtagByName(any(String.class))).thenReturn(expected);

        Hashtag actual = subject.validateAndSaveHashtag("Test");

        assertThat(actual).isEqualTo(expected);
        assertThat(actual.getName()).isEqualTo(expected.getName());
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
        when(subject.getHashtagByName(name)).thenReturn(null);

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
