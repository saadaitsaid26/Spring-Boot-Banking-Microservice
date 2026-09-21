package com.saad.bankservice.repository;

import com.saad.bankservice.entity.BankAccount;
import com.saad.bankservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    List<BankAccount> findBankAccountByType(@Param("t") AccountType accountType);

}
