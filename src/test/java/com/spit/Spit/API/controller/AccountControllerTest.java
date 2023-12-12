package com.spit.Spit.API.controller;

import com.spit.Spit.API.service.AccountService;
import com.spit.Spit.API.service.CommentService;
import com.spit.Spit.API.controller.AccountController;
import com.spit.Spit.API.dto.CreateAccountDTO;
import com.spit.Spit.API.dto.GetCommentDTO;
import com.spit.Spit.API.dto.UpdateAccountDTO;
import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.dto.GetPostDTO;
import com.spit.Spit.API.service.PostService;
import org.junit.jupiter.api.Disabled;
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
    @Mock
    CommentService commentService;

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
    void createAccount() {
        CreateAccountDTO account = new CreateAccountDTO("name", "test");
        when(accountService.isHandleAvailable(any(String.class))).thenReturn(true);

        ResponseEntity<String> actual = subject.createAccount(account);

        verify(accountService).createAccount(any(CreateAccountDTO.class));
        assertThat(actual.getBody()).isEqualTo("Account has been successfully saved.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void createAccount_unavailableHandle() {
        CreateAccountDTO account = new CreateAccountDTO(null, "handle");
        when(accountService.isHandleAvailable(any(String.class))).thenReturn(false);

        ResponseEntity<String> actual = subject.createAccount(account);

        assertThat(actual.getBody()).isEqualTo("Handle is unavailable.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void deleteAccountById() {
        Long id = 2L;
        Account account = new Account();
        account.setId(id);
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
    void patchAccountById_patchHandle() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO(null, "Homelander");
        Account account = new Account();
        when(accountService.getAccountById(1L)).thenReturn(account);

        ResponseEntity<String> actual = subject.patchAccountById(1L, updateAccountDTO);

        verify(accountService).patchAccountById(any(Account.class), any(UpdateAccountDTO.class));
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo("Account has been partially updated.");
    }

    @Test
    void patchAccountById_patchName() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO("Billy", null);
        Account account = new Account();
        when(accountService.getAccountById(1L)).thenReturn(account);

        ResponseEntity<String> actual = subject.patchAccountById(1L, updateAccountDTO);

        verify(accountService).patchAccountById(any(Account.class), any(UpdateAccountDTO.class));
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo("Account has been partially updated.");
    }

    @Test
    void patchAccountById_notFound() {
        ResponseEntity<String> actual = subject.patchAccountById(1L, new UpdateAccountDTO());

        assertThat(actual.getBody()).isEqualTo(null);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void patchAccountById_completeUpdate_failsToPatch() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO("Billy", "Butcher");
        Account account = new Account();
        when(accountService.getAccountById(1L)).thenReturn(account);

        ResponseEntity<String> actual = subject.patchAccountById(1L, updateAccountDTO);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(actual.getBody()).isEqualTo("Updating every field is a PUT request.");
    }

    @Test
    void putAccountById() {
        CreateAccountDTO account = new CreateAccountDTO("Reggie", "A-train");
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
    void getAllCommentsByAccountId_returnListOfComments() {
        List<GetCommentDTO> comments = new ArrayList<>();
        comments.add(new GetCommentDTO());
        comments.add(new GetCommentDTO());
        when(commentService.getAllCommentsByAccountId(any(Long.class))).thenReturn(comments);

        ResponseEntity<List<GetCommentDTO>> actual = subject.getAllCommentsByAccountId(2L);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo(comments);
    }

    @Disabled
    @Test //TODO
    void getLikedPostsForAccount() {

    }

    @Test
    void hasOnlyOneNullField_onlyNameHasValue_returnTrue() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO("name", null);

        boolean actual = AccountController.hasOnlyOneNullField(updateAccountDTO);

        assertThat(actual).isTrue();
    }

    @Test
    void hasOnlyOneNullField_onlyHandleHasValue_returnTrue() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO(null, "handle");

        boolean actual = AccountController.hasOnlyOneNullField(updateAccountDTO);

        assertThat(actual).isTrue();
    }

    @Test
    void hasOnlyOneNullField_returnFalse() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO("name", "handle");

        boolean actual = AccountController.hasOnlyOneNullField(updateAccountDTO);

        assertThat(actual).isFalse();
    }
}
