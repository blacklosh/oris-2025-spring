package ru.itis;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.model.UserEntity;
import ru.itis.repository.UserRepository;
import ru.itis.repository.impl.UserJpaRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        UserEntity user = UserEntity.builder()
                .name("Fedor")
                .build();

        UserRepository userRepository = new UserJpaRepositoryImpl(entityManager);
        UserEntity userFromDatabase = userRepository.save(user);

        System.out.println(userFromDatabase);
    }
}