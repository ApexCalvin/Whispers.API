package com.spit.Spit.API.hashtag;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HashtagServiceTest {

    @InjectMocks
    HashtagService subject;

    @Mock
    HashtagRepository hashtagRepository;

    @Test
    void createHashtags_singular() {}

    @Test
    void createHashtags_plural() {}

    @Test
    void validateAndSaveHashtag_newHashtag() {}

    @Test
    void validateAndSaveHashtag_existingHashtag() {}

    @Test
    void getHashtagById() {}

    @Test
    void getHashtagById_returnNull() {}

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
    void getAllHashtags() {
        List<Hashtag> hashtags = new ArrayList<>(Arrays.asList(new Hashtag(), new Hashtag()));
        when(hashtagRepository.findAll()).thenReturn(hashtags);

        List<Hashtag> actual = subject.getAllHashtags();

        assertThat(actual).isEqualTo(hashtags);
    }

    @Test
    void deleteHashtagById() {}

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
