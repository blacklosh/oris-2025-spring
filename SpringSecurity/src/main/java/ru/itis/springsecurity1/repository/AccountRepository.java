package ru.itis.springsecurity1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springsecurity1.model.AccountEntity;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByEmail(String email);

}
