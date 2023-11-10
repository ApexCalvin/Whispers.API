package com.spit.Spit.API.account;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountRepository;
import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Account.CreateAccountDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//adding notes for calvin feel to remove
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService subject;

    @Mock
    AccountRepository accountRepository;

    @Test
    void createAccount_whenAccountIsSaved_returnSuccessMessage(){
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();

        String actual = subject.createAccount(createAccountDTO);

        verify(accountRepository).save(any(Account.class));
        assertThat(actual).isEqualTo("Account successfully saved");
    }

    @Test
    void createAccount_whenAccountFailsToSave_returnFailureMessage(){
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        //mock the behavior of the object
        when(accountRepository.save(any(Account.class))).thenThrow(DataIntegrityViolationException.class);

        String actual = subject.createAccount(createAccountDTO);

        assertThat(actual).isEqualTo("Account failed to save");
    }

    @Test
    void getAllAccounts_returnListOfAccounts(){
        List<Account> expected = new ArrayList<>(Arrays.asList(new Account(), new Account()));
        when(accountRepository.findAll()).thenReturn(expected);

        List<Account> actual = subject.getAllAccounts();

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void fullyUpdateAccountById(){
        CreateAccountDTO expected = new CreateAccountDTO();
        expected.setName("Reggie");
        expected.setHandle("A-train");
        Account actual = new Account();

        subject.fullyUpdateAccountById(actual, expected);

        verify(accountRepository).save(actual);
        assertThat(actual.getHandle()).isEqualTo(expected.getHandle());
        assertThat(actual.getName()).isEqualTo(expected.getName());
    }
}
