package com.saad.bankservice.mapper;

import com.saad.bankservice.dto.AccountRequest;
import com.saad.bankservice.dto.AccountResponse;
import com.saad.bankservice.entity.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    // Entity -> Response DTO
    public AccountResponse toResponse(BankAccount account) {
        return new AccountResponse(
                account.getId(),
                account.getBalance(),
                account.getCurrency(),
                account.getType(),
                account.getCreatedAt()
        );
    }

    // Request -> new Entity (server-controlled fields set in the service, not here)
    public BankAccount toEntity(AccountRequest req) {
        return BankAccount.builder()
                .balance(req.balance())
                .currency(req.currency())
                .type(req.type())
                .build();
    }
}
