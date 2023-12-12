package com.spit.Spit.API.tool;

import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.dto.CreateAccountDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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

    }

    @Disabled
    @Test //TODO
    void buildCreatePostDTO() {

    }

    @Disabled
    @Test //TODO
    void buildHashtagSet() {

    }

}
