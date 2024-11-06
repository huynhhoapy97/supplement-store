package com.huynhhoapy97.repositories.admin;

import com.huynhhoapy97.connections.HibernateConfiguration;
import com.huynhhoapy97.models.Account;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private static final Log log = LogFactory.getLog(AccountDAOImpl.class);
    private final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(HibernateConfiguration.class);
    private final LocalSessionFactoryBean localSessionFactoryBean =
            applicationContext.getBean(LocalSessionFactoryBean.class);

    private final SessionFactory sessionFactory = localSessionFactoryBean.getObject();

    @Override
    public Account verifyUsername(String username) {
        Session session = null;

        List<Account> accounts = new ArrayList<>();
        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                String hql = "FROM Account WHERE userName = :username";
                Query query = session.createQuery(hql);
                query.setParameter("username", username);

                accounts = query.getResultList();

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        if (accounts.isEmpty()) {
            return null;
        } else {
            return accounts.get(0);
        }
    }

    @Override
    public boolean updatePassword(Account account) {
        Session session = null;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                session.update(account);

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return true;
    }

    @Override
    public Account getInformation(Account account) {
        Session session = null;
        Account accountDatabase = null;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                accountDatabase = session.get(Account.class, account.getId());

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return accountDatabase;
    }
}
