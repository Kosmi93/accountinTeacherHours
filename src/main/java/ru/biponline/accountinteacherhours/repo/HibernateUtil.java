package ru.biponline.accountinteacherhours.repo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.biponline.accountinteacherhours.entity.*;


public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

/*
//Хотелось бы организовать такую инициализацию
public class HibernateUtil {
    static final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder()
                    .build();
    private static final SessionFactory sessionFactory =new MetadataSources(registry)
            .addAnnotatedClass(DateTime.class)
            .addAnnotatedClass(Discipline.class)
            .addAnnotatedClass(DisciplineToPlan.class)
            .addAnnotatedClass(GroupDiscipline.class)
            .addAnnotatedClass(Group.class)
            .addAnnotatedClass(Teacher.class)
            .addAnnotatedClass(Speciality.class)
            .addAnnotatedClass(Report.class)
            .buildMetadata()
            .buildSessionFactory();;
    // A SessionFactory is set up once for an application!
    */
/*private static SessionFactory buildSessionFactory() {

		try {
            sessionFactory =
                new MetadataSources(registry)
                        .addAnnotatedClass(DateTime.class)
                        .addAnnotatedClass(Discipline.class)
                        .addAnnotatedClass(DisciplineToPlan.class)
                        .addAnnotatedClass(GroupDiscipline.class)
                        .addAnnotatedClass(Group.class)
                        .addAnnotatedClass(Teacher.class)
                        .addAnnotatedClass(Speciality.class)
                        .addAnnotatedClass(Report.class)
                        .buildMetadata()
                        .buildSessionFactory();
        }
		catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }*//*

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
*/
