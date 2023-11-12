package com.spit.Spit.API.tool;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Account.CreateAccountDTO;
import com.spit.Spit.API.Post.CreatePostDTO;
import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Tools.DtoMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DtoMapperTest {
    @Mock
    private AccountService accountServices;

    @Test
    void fromCreateAccountDTO_standardTranslation(){
        CreateAccountDTO expected = new CreateAccountDTO();
        expected.setName("FakeName");
        expected.setHandle("FakeHandle");

        Account actual = DtoMapper.fromCreateAccountDTO(expected);

        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getHandle()).isEqualTo(expected.getHandle());
    }

    @Disabled
    @Test
    void fromCreatePostDTO_standardTranslation(){
        CreatePostDTO expected = new CreatePostDTO();
        expected.setAccountId(1L);
        expected.setMessage("FakeMessage");
//        AccountServices accountServices = mock(AccountServices.class);
        Account account = new Account();

        when(accountServices.getExistingAccount(expected.getAccountId())).thenReturn(account);
        Post actual = DtoMapper.fromCreatePostDTO(expected);

        assertThat(actual.getAccount()).isEqualTo(account);
        assertThat(actual.getMessage()).isEqualTo(expected.getMessage());
    }
}
