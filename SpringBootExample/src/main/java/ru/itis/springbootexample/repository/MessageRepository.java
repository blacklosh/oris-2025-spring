package ru.itis.springbootexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootexample.model.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
