package com.spit.Spit.API.account;

import com.spit.Spit.API.Account.AccountController;
import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Account.CreateAccountDTO;
import com.spit.Spit.API.Account.Account;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

    @Test
    void deleteAccountById() {
        Long id = 2L;
        Account account = new Account();
        account.setAccount_id(id);
        when(accountService.getAccountById(any(Long.class))).thenReturn(account);

        ResponseEntity<String> actual = subject.deleteAccountById(id);

        verify(accountService).deleteAccountById(any(Long.class));
        assertThat(actual.getBody()).isEqualTo("Account has been successfully deleted.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteAccountById_notFound() {
        when(accountService.getAccountById(any(Long.class))).thenReturn(null);

        ResponseEntity<String> actual = subject.deleteAccountById(2L);

        assertThat(actual.getBody()).isEqualTo(null);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    void deleteAccountById_deleteError() {} //TODO

    @Test
    void putAccountById() {
        CreateAccountDTO account = new CreateAccountDTO();
        account.setHandle("A-train");
        account.setName("Reggie");
        when(accountService.getAccountById(any(Long.class))).thenReturn(new Account());

        ResponseEntity<String> actual = subject.putAccountById(2L, account);

        verify(accountService).putAccountById(any(Account.class), any(CreateAccountDTO.class));
        assertThat(actual.getBody()).isEqualTo("Account has been fully updated.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void putAccountById_notFound() {
        when(accountService.getAccountById(any(Long.class))).thenReturn(null);

        ResponseEntity<String> actual = subject.putAccountById(2L, new CreateAccountDTO());

        assertThat(actual.getBody()).isEqualTo(null);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    void patchAccountById_patchHandle() {
    }

    void patchAccountById_patchName() {}

    void patchAccountById_Error_putRequest() {}

    void patchAccountById_notFound() {}

    @Test
    void hasValue_true() {
        Account account = new Account();
        boolean actual = subject.hasValue(account);

        assertThat(actual).isTrue();
    }

    @Test
    void hasValue_false() {
        boolean actual = subject.hasValue(null);
        assertThat(actual).isFalse();
    }

    void handleValidationExceptions() {}
}
