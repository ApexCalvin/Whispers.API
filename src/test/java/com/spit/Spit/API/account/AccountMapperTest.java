package com.spit.Spit.API.account;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Tools.DtoMapper;
import com.spit.Spit.API.Account.CreateAccountDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountMapperTest {
    @Test
    void fromCreateAccountDTO_standardTranslation(){
        CreateAccountDTO expected = new CreateAccountDTO();
        expected.setName("FakeName");
        expected.setHandle("FakeHandle");

        Account actual = DtoMapper.fromCreateAccountDTO(expected);

        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getHandle()).isEqualTo(expected.getHandle());
    }
}
