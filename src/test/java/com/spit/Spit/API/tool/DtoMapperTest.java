package com.spit.Spit.API.tool;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
import com.spit.Spit.API.account.CreateAccountDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DtoMapperTest {
    @Mock
    private AccountService accountServices;

    @Test
    void fromCreateAccountDTO_standardTranslation(){
        CreateAccountDTO expected = new CreateAccountDTO("FakeName", "FakeHandle");

        Account actual = DtoMapper.fromCreateAccountDTO(expected);

        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getHandle()).isEqualTo(expected.getHandle());
    }

    @Disabled
    @Test
    void fromCreatePostDTO_standardTranslation(){
//        CreatePostDTO expected = new CreatePostDTO(1L, "FakeMessage");
//        Account account = new Account();
//        when(accountServices.getAccountById(expected.getAccountId())).thenReturn(account);
//
//        Post actual = DtoMapper.fromCreatePostDTO(expected);
//
//        assertThat(actual.getAccount()).isEqualTo(account);
//        assertThat(actual.getMessage()).isEqualTo(expected.getMessage());
    }
}
