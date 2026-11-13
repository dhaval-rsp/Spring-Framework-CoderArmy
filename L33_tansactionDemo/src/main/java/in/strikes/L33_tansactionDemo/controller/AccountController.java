package in.strikes.L33_tansactionDemo.controller;

import in.strikes.L33_tansactionDemo.entity.Account;
import in.strikes.L33_tansactionDemo.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(
            @RequestBody Account account) {

        accountService.createAccount(account);
        return ResponseEntity.ok("account created");
    }
}
