package com.delivery.api.controller;

import com.delivery.db.account.entity.AccountEntity;
import com.delivery.db.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final AccountRepository accountRepository;

    @GetMapping("/")
    public void test() {
        accountRepository.save(AccountEntity.builder().build());
    }
}
