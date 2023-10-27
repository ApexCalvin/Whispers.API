package com.spit.Spit.API.account;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountRepository;
import com.spit.Spit.API.Account.AccountServices;
import com.spit.Spit.API.Account.CreateAccountDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//adding notes for calvin feel to remove
@ExtendWith(MockitoExtension.class) // allows mockito functionality
public class AccountServiceTest {

    @InjectMocks //dependency injection for the mock beans
    AccountServices subject; //convention to the name the test object "subject"

    @Mock
    AccountRepository accountRepository;

    @Test
    void createAccount_whenAccountIsSaved_returnSuccessMessage(){
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();

        String actual = subject.createAccount(createAccountDTO);

        verify(accountRepository).save(any(Account.class)); //verify method was called
        assertThat(actual).isEqualTo("Account successfully saved"); //good naming convention for asserts
    }

    @Test
    void createAccount_whenAccountFailsToSave_returnFailureMessage(){
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        //mock the behavior of the object
        when(accountRepository.save(any(Account.class))).thenThrow(DataIntegrityViolationException.class);

        String actual = subject.createAccount(createAccountDTO);

        assertThat(actual).isEqualTo("Account failed to save");
    }


}
