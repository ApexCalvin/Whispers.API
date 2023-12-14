package com.spit.Spit.API.tool;

import com.spit.Spit.API.dto.CreateCommentDTO;
import com.spit.Spit.API.dto.CreatePostDTO;
import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.dto.CreateAccountDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

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

    @Disabled
    @Test //TODO
    void buildCreateCommentDTO() {

        CreateCommentDTO actual = DtoMapper.buildCreateCommentDTO("test", 1L, 2L);
    }

    @Disabled
    @Test //TODO
    void buildCreatePostDTO() {

        CreatePostDTO actual = DtoMapper.buildCreatePostDTO(1L, "test", new ArrayList<String>());
    }

    @Disabled
    @Test //TODO
    void buildHashtagSet() {

    }

}
