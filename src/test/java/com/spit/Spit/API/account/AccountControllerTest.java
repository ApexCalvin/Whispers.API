package com.spit.Spit.API.account;

import com.spit.Spit.API.Account.UpdateAccountDTO;
import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountController;
import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Account.CreateAccountDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @InjectMocks
    AccountController subject;

    @Mock
    AccountService accountService;

    @Test
    void createAccount() {
        CreateAccountDTO account = new CreateAccountDTO();
        account.setHandle("Test");
        //when(accountService.isHandleAvailable(nullable(String.class))).thenReturn(true);
        when(accountService.isHandleAvailable(any(String.class))).thenReturn(true);
        when(accountService.createAccount(any(CreateAccountDTO.class))).thenReturn(new Account());

        ResponseEntity<Account> actual = subject.createAccount(account);

        assertThat(actual.getBody()).isInstanceOf(Account.class);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void createAccount_unavailableHandle() {
        CreateAccountDTO account = new CreateAccountDTO();
        account.setHandle("Test");
        when(accountService.isHandleAvailable(any(String.class))).thenReturn(false);

        ResponseEntity<Account> actual = subject.createAccount(account);

        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account());
        accountList.add(new Account());
        when(accountService.getAllAccounts()).thenReturn(accountList);

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
    @Test
    void patchAccountById_patchHandle() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO();
        updateAccountDTO.setHandle("Homelander");
        Account account = new Account();
        when(accountService.getAccountById(1L)).thenReturn(account);

        ResponseEntity<String> actual = subject.patchAccountById(1L, updateAccountDTO);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo("Account has been partially updated.");
    }
    @Test
    void patchAccountById_patchName() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO();
        updateAccountDTO.setName("Billy");
        Account account = new Account();
        when(accountService.getAccountById(1L)).thenReturn(account);

        ResponseEntity<String> actual = subject.patchAccountById(1L, updateAccountDTO);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo("Account has been partially updated.");
    }

    @Test
    void patchAccountById_notFound() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO();

        ResponseEntity<String> actual = subject.patchAccountById(1L, updateAccountDTO);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void patchAccountById_completeUpdate_failsToPatch() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO();
        updateAccountDTO.setName("Billy");
        updateAccountDTO.setHandle("Butcher");
        Account account = new Account();
        when(accountService.getAccountById(1L)).thenReturn(account);

        ResponseEntity<String> actual = subject.patchAccountById(1L, updateAccountDTO);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(actual.getBody()).isEqualTo("Updating every field is a PUT request.");
    }

    void handleValidationExceptions() {}
}
