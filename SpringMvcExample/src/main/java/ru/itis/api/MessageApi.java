package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.MessageResponse;
import ru.itis.model.MessageEntity;

import java.util.List;
import java.util.UUID;

@RequestMapping("/messages")
public interface MessageApi {

    @GetMapping
    List<MessageEntity> getAllMessages();

    @GetMapping("/{id}")
    MessageResponse getById(@PathVariable UUID id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MessageEntity save(@RequestBody MessageEntity entity);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    boolean deleteById(@PathVariable UUID id);

}
