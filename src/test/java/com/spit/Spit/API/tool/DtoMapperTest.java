package com.spit.Spit.API.tool;

import com.spit.Spit.API.dto.CreateCommentDTO;
import com.spit.Spit.API.dto.CreatePostDTO;
import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.dto.CreateAccountDTO;
import com.spit.Spit.API.model.Hashtag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DtoMapperTest {

    @Test
    void fromCreateAccountDTO_standardTranslation() {
        CreateAccountDTO expected = new CreateAccountDTO("FakeName", "FakeHandle");

        Account actual = DtoMapper.fromCreateAccountDTO(expected);

        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getHandle()).isEqualTo(expected.getHandle());
    }

    @Test
    void buildCreateCommentDTO() {
        CreateCommentDTO actual = DtoMapper.buildCreateCommentDTO("test", 1L, 2L);

        assertThat(actual.getMessage()).isEqualTo("test");
        assertThat(actual.getPostId()).isEqualTo(1L);
        assertThat(actual.getAccountId()).isEqualTo(2L);
    }

    @Test
    void buildCreatePostDTO() {
        ArrayList<String> al = new ArrayList<>();
        al.add("Hello");
        al.add("World");

        CreatePostDTO actual = DtoMapper.buildCreatePostDTO(1L, "test", al);

        assertThat(actual.getMessage()).isEqualTo("test");
        assertThat(actual.getAccountId()).isEqualTo(1L);
        assertThat(actual.getHashtags().size()).isEqualTo(2);
    }

    @Test
    void buildHashtagSet() {
        Set<Hashtag> actual = DtoMapper.buildHashtagSet("test1", "test2");

        assertThat(actual.size()).isEqualTo(2);
    }

}
