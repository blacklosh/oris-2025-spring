package ru.itis.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "users")
public class Course {
    private Long id;
    private String title;

    private List<Lesson> lessons;

    private List<UserEntity> users;
}
