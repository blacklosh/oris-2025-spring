package ru.itis;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.model.Course;
import ru.itis.model.Lesson;
import ru.itis.model.UserEntity;

import java.util.ArrayList;

public class MainManyToMany {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Course course1 = Course.builder()
                .title("oris")
                .lessons(new ArrayList<>())
                .users(new ArrayList<>())
                .build();

        Course course2 = Course.builder()
                .title("iip")
                .lessons(new ArrayList<>())
                .users(new ArrayList<>())
                .build();

        session.persist(course1);
        session.persist(course2);

        UserEntity user1 = UserEntity.builder()
                .name("Fedor")
                .courses(new ArrayList<>())
                .build();

        session.persist(user1);

        UserEntity user2 = UserEntity.builder()
                .name("Marsel")
                .courses(new ArrayList<>())
                .build();

        session.persist(user2);

        user1.getCourses().add(course1);
        user1.getCourses().add(course2);

        user2.getCourses().add(course1);
        user2.getCourses().add(course2);

        course1.getUsers().add(user1);
        course1.getUsers().add(user2);

        course2.getUsers().add(user1);
        course2.getUsers().add(user2);

        System.out.println(user1);
        session.getTransaction().commit();

        session.getTransaction().begin();
        UserEntity user = session.load(UserEntity.class, 1L);
        System.out.println(user.getCourses());
        session.getTransaction().commit();

        session.getTransaction().begin();
        Course course = session.load(Course.class, 1L);
        System.out.println(course.getUsers());
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();



    }
}