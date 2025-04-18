package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.api.MessageApi;
import ru.itis.dto.MessageResponse;
import ru.itis.model.MessageEntity;
import ru.itis.service.MessageServiceStub;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MessageController implements MessageApi {

    private final MessageServiceStub messageService;

    @Override
    public List<MessageEntity> getAllMessages() {
        return messageService.getAll();
    }

    @Override
    public MessageResponse getById(UUID id) {
        return messageService.getById(id);
    }

    @Override
    public MessageEntity save(MessageEntity entity) {
        return messageService.save(entity);
    }

    @Override
    public boolean deleteById(UUID id) {
        return messageService.deleteById(id);
    }
}
