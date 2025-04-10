package com.filosofiadelsoftware.pruebadb.spring;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //PESSIMISTIC_WRITE: Cuando vas a modificar datos, Riesgo alto de deadlock
    //PESSIMISTIC_READ: Cuando solo quieres leer datos consistentes, riesgo bajo pero existe

    //@Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Account> findByAccountNumber(String accountNumber);
}

