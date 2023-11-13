package com.spit.Spit.API.account;

import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Account.AccountRepository;
import com.spit.Spit.API.Account.CreateAccountDTO;
import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.UpdateAccountDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.spit.Spit.API.Account.AccountService.ACCOUNT_SAVED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService subject;

    @Mock
    AccountRepository accountRepository;

    @Test
    void createAccount_whenAccountIsSaved_returnSuccessMessage() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();

        String actual = subject.createAccount(createAccountDTO);

        verify(accountRepository).save(any(Account.class));
        assertThat(actual).isEqualTo(ACCOUNT_SAVED);
    }

    @Test
    void createAccount_whenAccountFailsToSave_returnFailureMessage() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        when(accountRepository.save(any(Account.class))).thenThrow(DataIntegrityViolationException.class);

        String actual = subject.createAccount(createAccountDTO);

        assertThat(actual).isEqualTo("Account failed to save.");
    }

    @Test
    void getAllAccounts_returnListOfAccounts() {
        List<Account> expected = new ArrayList<>(Arrays.asList(new Account(), new Account()));
        when(accountRepository.findAll()).thenReturn(expected);

        List<Account> actual = subject.getAllAccounts();

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void getAccountById() {
        Long id = 2L;
        Account account = new Account();
        account.setAccount_id(id);
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        Account actual = subject.getAccountById(id);

        //checking for nulls
        System.out.println(actual.getAccount_id() + " " + account.getAccount_id());
        assertThat(actual.getAccount_id()).isEqualTo(account.getAccount_id());
    }

    @Test
    void getAccountById_returnNull() {
        Long id = 2L;
        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        Account actual = subject.getAccountById(id);

        assertThat(actual).isEqualTo(null);
    }

    @Test
    void getAccountByHandle() {
        String handle = "test";
        Account account = new Account();
        account.setHandle(handle);
        when(accountRepository.findByHandle(handle)).thenReturn(account);

        Account actual = subject.getAccountByHandle(handle);

        //checking for nulls
        System.out.println(actual.getHandle() + " " + account.getHandle());
        assertThat(actual.getHandle()).isEqualTo(account.getHandle());
    }

    @Test
    void getAccountByHandle_returnNull() {
        String handle = "test";
        when(accountRepository.findByHandle(handle)).thenReturn(null);

        Account actual = subject.getAccountByHandle(handle);

        System.out.println("value: "+ actual);
        assertThat(actual).isEqualTo(null);
    }

    @Test
    void deleteAccountById() {
        subject.deleteAccountById(2L);
        verify(accountRepository).deleteById(any(Long.class));
    }

    //TODO: Fails to delete?
    void deleteAccountById_returnFailureMessage() {}

    @Test
    void putAccountById() {
        CreateAccountDTO expected = new CreateAccountDTO();
        expected.setName("Reggie");
        expected.setHandle("A-train");
        Account actual = new Account();

        subject.putAccountById(actual, expected);

        verify(accountRepository).save(actual);
        assertThat(actual.getHandle()).isEqualTo(expected.getHandle());
        assertThat(actual.getName()).isEqualTo(expected.getName());
    }

    @Test
    void patchAccountById_patchName() {
        String name = "test";
        UpdateAccountDTO updatedAccount = new UpdateAccountDTO();
        updatedAccount.setName(name);
        Account actual = new Account();

        subject.patchAccountById(actual, updatedAccount);

        verify(accountRepository).save(actual);
        //checking for nulls
        System.out.println(actual.getName() + " " + updatedAccount.getName());
        assertThat(actual.getName()).isEqualTo(updatedAccount.getName());
    }

    @Test
    void patchAccountById_patchHandle() {
        String handle = "test";
        UpdateAccountDTO updatedAccount = new UpdateAccountDTO();
        updatedAccount.setHandle(handle);
        Account actual = new Account();

        subject.patchAccountById(actual, updatedAccount);

        verify(accountRepository).save(actual);
        //checking for nulls
        System.out.println(actual.getHandle() + " " + updatedAccount.getHandle());
        assertThat(actual.getHandle()).isEqualTo(updatedAccount.getHandle());
    }

    @Test
    void isHandleAvailable_true() {
        String handle = "Test";
        Account account = new Account();
        when(subject.getAccountByHandle(handle)).thenReturn(account);

        boolean actual = subject.isHandleAvailable(handle);

        assertThat(actual).isTrue();
    }

    @Test
    void isHandleAvailable_false() {
        String handle = "Test";
        when(subject.getAccountByHandle(handle)).thenReturn(null);

        boolean actual = subject.isHandleAvailable(handle);

        assertThat(actual).isFalse();
    }
}
