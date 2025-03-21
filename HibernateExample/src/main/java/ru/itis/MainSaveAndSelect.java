package ru.itis;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.model.UserEntity;

public class MainSaveAndSelect {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.getTransaction().begin();

        UserEntity user = UserEntity.builder()
                .name("Fedor")
                .build();

        session.persist(user);

        System.out.println(user);

        user.setName("Another");

        UserEntity user2 = session.load(UserEntity.class, 1L);

        user2.setName("Goood");

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

        System.out.println(user2);
    }
}