package com.spit.Spit.API.account;

import com.spit.Spit.API.comment.CommentService;
import com.spit.Spit.API.comment.GetCommentDTO;
import com.spit.Spit.API.post.GetPostDTO;
import com.spit.Spit.API.post.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final PostService postService;
    private final CommentService commentService;

    public AccountController(AccountService accountServices, PostService postService, CommentService commentService) {
        this.accountService = accountServices;
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody CreateAccountDTO newAccount) {
        boolean handleAvailability = accountService.isHandleAvailable(newAccount.getHandle());

        if(handleAvailability) {
            accountService.createAccount(newAccount);
            return new ResponseEntity<>("Account has been successfully saved.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Handle is unavailable.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);

        if(account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/handle/{handle}")
    public ResponseEntity<Account> getAccountByHandle(@PathVariable String handle) {
        Account account = accountService.getAccountByHandle(handle);

        if(account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{accountId}/liked-posts")
    public ResponseEntity<List<GetPostDTO>> getLikedPostsForAccount(@PathVariable Long accountId) {
        List<GetPostDTO> likedPosts = postService.getLikedPostsByAccountId(accountId);
        return ResponseEntity.ok(likedPosts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);

        if(account != null) {
            accountService.deleteAccountById(id);
            return new ResponseEntity<>("Account has been successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putAccountById(@PathVariable Long id, @Valid @RequestBody CreateAccountDTO updatedAccount) {
        Account account = accountService.getAccountById(id);

        if(account != null) {
            accountService.putAccountById(account, updatedAccount);
            return new ResponseEntity<>("Account has been fully updated.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchAccountById(@PathVariable Long id, @RequestBody UpdateAccountDTO updatedAccount) {
        Account account = accountService.getAccountById(id);

        if(account != null) {
            if(hasOnlyOneNullField(updatedAccount)) {
                accountService.patchAccountById(account, updatedAccount);
                return new ResponseEntity<>("Account has been partially updated.", HttpStatus.OK);
            }
            return new ResponseEntity<>("Updating every field is a PUT request.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{accountId}/comments")
    public ResponseEntity<List<GetCommentDTO>> getAllCommentsByAccountId(@PathVariable Long accountId) {
        List<GetCommentDTO> userComments = commentService.getAllCommentsByAccountId(accountId);
        return ResponseEntity.ok(userComments);
    }

    public static boolean hasOnlyOneNullField(UpdateAccountDTO updatedAccount) {
        return (updatedAccount.getName() != null && updatedAccount.getHandle() == null) ||
                (updatedAccount.getName() == null && updatedAccount.getHandle() != null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
