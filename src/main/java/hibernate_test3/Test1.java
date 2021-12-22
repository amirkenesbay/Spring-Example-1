package hibernate_test3;

import hibernate_test3.entity.Passport;
import hibernate_test3.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class Test1 {
    static Session session = null;
    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure(new File("hibernate.cfg.xml"))
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class)
                .buildSessionFactory()) {

//            addPersonAndPassportDetails(session, factory);
            getPersonDetails(factory);
            getPassportDetails(factory);
        } finally {
            session.close();
        }
    }

    private static void addPersonAndPassportDetails(SessionFactory factory){
        session = factory.getCurrentSession();
        Person person = new Person("Michael", "Jordan", 45);
        Passport passport = new Passport(2020, 2030);
        person.setPassportDetail(passport);
        passport.setPerson(person);
        session.beginTransaction();
        session.save(passport);
        session.getTransaction().commit();
        System.out.println("Done");
        session.close();
    }

    private static void getPersonDetails(SessionFactory factory){
        session = factory.getCurrentSession();
        session.beginTransaction();
        Person person = session.get(Person.class, 1);
        System.out.println(person.getPassportDetail());
        session.getTransaction().commit();
        System.out.println("Done");
        session.close();
    }

    private static void getPassportDetails(SessionFactory factory){
        session = factory.getCurrentSession();
        session.beginTransaction();
        Passport passport = session.get(Passport.class, 1);
        System.out.println(passport.getPerson());
        session.getTransaction().commit();
        System.out.println("Done");
        session.close();
    }
}
