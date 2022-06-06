package com.revature.bibimbop.util;

import com.revature.bibimbop.credit_card.CreditCardModel;
import com.revature.bibimbop.customer.CustomerModel;
import com.revature.bibimbop.menu.MenuModel;
import com.revature.bibimbop.order.OrderModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
//            Properties props = new Properties();
//            ClassLoader loader = Thread.currentThread().getContextClassLoader();
//            props.load(loader.getResourceAsStream("hibernate.properties"));

//            configuration.setProperties(props);
            // TODO setup config with proper classes
            String url = System.getenv("SQLAZURECONNSTR_bibimbop");
            String username = System.getenv("DBUSER");
            String password = System.getenv("DBPASS");

            configuration.setProperty("hibernate.connection.url", url);
            configuration.setProperty("hibernate.connection.username", username);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            configuration.addAnnotatedClass(MenuModel.class);
            configuration.addAnnotatedClass(OrderModel.class);
            configuration.addAnnotatedClass(CustomerModel.class);
            configuration.addAnnotatedClass(CreditCardModel.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        if(session == null) {
            session = sessionFactory.openSession();
        }

        return session;
    }

    public static void closeSession() {
        session.close();
        session = null;

    }
}








