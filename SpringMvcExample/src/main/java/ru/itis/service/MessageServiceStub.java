package ru.itis.service;

import org.springframework.stereotype.Service;
import ru.itis.dto.MessageResponse;
import ru.itis.model.MessageEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageServiceStub {

    private final Map<UUID, MessageEntity> messages = new ConcurrentHashMap<>();

    public MessageEntity save(MessageEntity entity) {
        entity.setId(UUID.randomUUID());
        messages.put(entity.getId(), entity);
        return entity;
    }

    public boolean deleteById(UUID id) {
        return messages.remove(id) != null;
    }

    public MessageResponse getById(UUID id) {
        MessageEntity response = messages.get(id);
        if(response == null) {
            return MessageResponse.builder()
                    .found(false)
                    .build();
        }
        return MessageResponse.builder()
                .message(response)
                .found(true)
                .build();
    }

    public List<MessageEntity> getAll() {
        return messages.values().stream().toList();
    }

}
