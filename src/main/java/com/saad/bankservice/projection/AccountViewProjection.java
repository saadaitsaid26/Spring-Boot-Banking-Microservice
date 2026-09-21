package com.saad.bankservice.projection;

import com.saad.bankservice.entity.BankAccount;
import com.saad.bankservice.enums.AccountType;
import lombok.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullAccount", types = { BankAccount.class })
public interface AccountViewProjection {
    String getId();
    double getBalance();
    String getCurrency();
    AccountType getType();

}
