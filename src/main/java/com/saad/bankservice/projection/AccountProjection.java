package com.saad.bankservice.projection;

import com.saad.bankservice.entity.BankAccount;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "summary", types = { BankAccount.class })
public interface AccountProjection {
    String getId();
    double getBalance();
    String getCurrency();
}
