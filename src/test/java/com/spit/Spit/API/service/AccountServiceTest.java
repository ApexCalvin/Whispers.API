package com.spit.Spit.API.service;

import com.spit.Spit.API.dto.CreateAccountDTO;
import com.spit.Spit.API.dto.UpdateAccountDTO;
import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void createAccount() {
        CreateAccountDTO expected = new CreateAccountDTO("Joe", "SuperHero");
        ArgumentCaptor<Account> accAC = ArgumentCaptor.forClass(Account.class);
        subject.createAccount(expected);

        verify(accountRepository).save(accAC.capture());
        assertThat(accAC.getValue().getName()).isEqualTo(expected.getName());
        assertThat(accAC.getValue().getHandle()).isEqualTo(expected.getHandle());
    }

    @Test
    void getAllAccounts_returnAccountList() {
        List<Account> expected = new ArrayList<>(Arrays.asList(new Account(), new Account()));
        when(accountRepository.findAll()).thenReturn(expected);

        List<Account> actual = subject.getAllAccounts();

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void getAccountById_returnAccount() {
        Long id = 2L;
        Account account = new Account();
        account.setId(id);
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        Account actual = subject.getAccountById(id);

        assertThat(actual.getId()).isEqualTo(account.getId());
    }

    @Test
    void getAccountById_returnNull() {
        Long id = 2L;
        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        Account actual = subject.getAccountById(id);

        assertThat(actual).isEqualTo(null);
    }

    @Test
    void getAccountByHandle_returnAccount() {
        String handle = "test";
        Account account = new Account();
        account.setHandle(handle);
        when(accountRepository.findByHandle(handle)).thenReturn(account);

        Account actual = subject.getAccountByHandle(handle);

        assertThat(actual.getHandle()).isEqualTo(account.getHandle());
    }

    @Test
    void getAccountByHandle_returnNull() {
        String handle = "test";
        when(accountRepository.findByHandle(handle)).thenReturn(null);

        Account actual = subject.getAccountByHandle(handle);

        assertThat(actual).isEqualTo(null);
    }

    @Test
    void deleteAccountById() {
        subject.deleteAccountById(2L);
        verify(accountRepository).deleteById(any(Long.class));
    }

    @Test
    void putAccountById() {
        CreateAccountDTO expected = new CreateAccountDTO("Reggie", "A-train");
        Account actual = new Account();

        subject.putAccountById(actual, expected);

        verify(accountRepository).save(actual);
        assertThat(actual.getHandle()).isEqualTo(expected.getHandle());
        assertThat(actual.getName()).isEqualTo(expected.getName());
    }

    @Test
    void patchAccountById_patchName() {
        UpdateAccountDTO updatedAccount = new UpdateAccountDTO("name", null);
        Account actual = new Account();

        subject.patchAccountById(actual, updatedAccount);

        verify(accountRepository).save(actual);
        assertThat(actual.getName()).isEqualTo(updatedAccount.getName());
    }

    @Test
    void patchAccountById_patchHandle() {
        UpdateAccountDTO updatedAccount = new UpdateAccountDTO(null, "handle");
        Account actual = new Account();

        subject.patchAccountById(actual, updatedAccount);

        verify(accountRepository).save(actual);
        assertThat(actual.getHandle()).isEqualTo(updatedAccount.getHandle());
    }

    @Test
    void isHandleAvailable_true() {
        String handle = "Test";
        when(subject.getAccountByHandle(handle)).thenReturn(null);

        boolean actual = subject.isHandleAvailable(handle);

        assertThat(actual).isTrue();
    }

    @Test
    void isHandleAvailable_false() {
        String handle = "Test";
        Account account = new Account();
        when(subject.getAccountByHandle(handle)).thenReturn(account);

        boolean actual = subject.isHandleAvailable(handle);

        assertThat(actual).isFalse();
    }
}
