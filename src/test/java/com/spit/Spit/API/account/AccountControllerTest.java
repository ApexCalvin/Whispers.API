package com.spit.Spit.API.account;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountController;
import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Account.CreateAccountDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @InjectMocks
    AccountController subject;

    @Mock
    AccountService accountService;

    //change static on hasValue
    @Test //TODO
    void createAccount() {
        CreateAccountDTO account = new CreateAccountDTO();
        when(accountService.isHandleAvailable(any(String.class))).thenReturn(true);
        String s = "Account has been successfully saved.";
        when(accountService.createAccount(any(CreateAccountDTO.class))).thenReturn(s);

        ResponseEntity<String> actual = subject.createAccount(account);

        assertThat(actual.getBody()).isEqualTo(s);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void createAccount_saveError() {
        CreateAccountDTO account = new CreateAccountDTO();
        when(accountService.isHandleAvailable(any(String.class))).thenReturn(true);
        when(accountService.createAccount(account)).thenReturn("else");

        ResponseEntity<String> actual = subject.createAccount(account);

        assertThat(actual.getBody()).isEqualTo("Account has been successfully saved.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void createAccount_unavailableHandle() {
        CreateAccountDTO account = new CreateAccountDTO();
        when(accountService.isHandleAvailable(any(String.class))).thenReturn(false);

        ResponseEntity<String> actual = subject.createAccount(account);

        assertThat(actual.getBody()).isEqualTo("Handle is not available.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account());
        accountList.add(new Account());

        ResponseEntity<List<Account>> actual = subject.getAllAccounts();

        assertThat(actual.getBody().size()).isEqualTo(accountList.size());
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAccountById() {
        Long id = 2L;
        Account account = new Account();
        when(accountService.getAccountById(any(Long.class))).thenReturn(account);

        ResponseEntity<Account> actual = subject.getAccountById(id);

        assertThat(actual.getBody()).isEqualTo(account);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAccountById_notFound() {
        when(accountService.getAccountById(any(Long.class))).thenReturn(null);

        ResponseEntity<Account> actual = subject.getAccountById(2L);

        assertThat(actual.getBody()).isEqualTo(null);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getAccountByHandle() {
        String handle = "Test";
        Account account = new Account();
        when(accountService.getAccountByHandle(any(String.class))).thenReturn(account);

        ResponseEntity<Account> actual = subject.getAccountByHandle(handle);

        assertThat(actual.getBody()).isEqualTo(account);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAccountByHandle_notFound() {
        when(accountService.getAccountByHandle(any(String.class))).thenReturn(null);

        ResponseEntity<Account> actual = subject.getAccountByHandle("Test");

        assertThat(actual.getBody()).isEqualTo(null);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    void deleteAccountById() {}

    void deleteAccountById_notFound() {}

    void deleteAccountById_deleteError() {} //TODO

    void putAccountById() {}

    void putAccountById_notFound() {}

    void patchAccountById() {}

    void patchAccountById_Error_putRequest() {}

    void patchAccountById_notFound() {}

    void hasValue_true() {}

    void hasValue_false() {}

    void handleValidationExceptions() {}
}
