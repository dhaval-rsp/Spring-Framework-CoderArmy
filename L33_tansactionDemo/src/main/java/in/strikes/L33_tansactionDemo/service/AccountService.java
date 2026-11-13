package in.strikes.L33_tansactionDemo.service;

import in.strikes.L33_tansactionDemo.entity.Account;
import in.strikes.L33_tansactionDemo.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }
}
