package com.spit.Spit.API.Account;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spit.Spit.API.Account.AccountService.ACCOUNT_SAVED;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountServices) {
        this.accountService = accountServices;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createAccount(@Valid @RequestBody CreateAccountDTO newAccount) {
        boolean handleAvailability = accountService.isHandleAvailable(newAccount.getHandle());

        if(handleAvailability) {
            String message = accountService.createAccount(newAccount);
            if(message.equals(ACCOUNT_SAVED)){
                return new ResponseEntity<>(message, HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Handle is not available.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);

        if(hasValue(account)) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/handle/{handle}")
    public ResponseEntity<Account> getAccountByHandle(@PathVariable String handle) {
        Account account = accountService.getAccountByHandle(handle);

        if(hasValue(account)) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //return exist.map(account -> new ResponseEntity<>(account, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);

        if(hasValue(account)) {
            accountService.deleteAccountById(id);
            return new ResponseEntity<>("Account has been successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putAccountById(@PathVariable Long id, @Valid @RequestBody CreateAccountDTO updatedAccount) {
        Account account = accountService.getAccountById(id);

        if(hasValue(account)) {
            accountService.putAccountById(account, updatedAccount);
            return new ResponseEntity<>("Account has been fully updated.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchAccountById(@PathVariable Long id, @RequestBody UpdateAccountDTO updatedAccount) {
        Account account = accountService.getAccountById(id);

        if(hasValue(account)) {
            //if only one field has a value
            if(!(updatedAccount.getName() != null && updatedAccount.getHandle() != null)) {
                accountService.patchAccountById(account, updatedAccount);
                return new ResponseEntity<>("Account has been partially updated.", HttpStatus.OK);
            }
            return new ResponseEntity<>("Updating every field is a PUT request.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public static boolean hasValue(Account account) {
        return account != null;
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
