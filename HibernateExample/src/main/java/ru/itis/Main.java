package ru.itis;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.model.Course;
import ru.itis.model.Lesson;
import ru.itis.model.UserEntity;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        /*Course course = Course.builder()
                .title("oris")
                .lessons(new ArrayList<>())
                .build();

        Lesson lesson1 = Lesson.builder()
                .name("spring context")
                .course(course)
                .build();

        Lesson lesson2 = Lesson.builder()
                .name("hibernate")
                .course(course)
                .build();

        session.persist(lesson1);
        session.persist(lesson2);
        session.persist(course);
*/
        Course course2 = session.load(Course.class, 1L);

        System.out.println(course2.getLessons());
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }
}