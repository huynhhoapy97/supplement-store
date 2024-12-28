package com.huynhhoapy97.repositories.admin;

import com.huynhhoapy97.connections.HibernateConfiguration;
import com.huynhhoapy97.models.Commodity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommodityDAOImpl implements CommodityDAO {
    private static final Logger log = LoggerFactory.getLogger(CommodityDAOImpl.class);
    private final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(HibernateConfiguration.class);
    private final LocalSessionFactoryBean localSessionFactoryBean =
            applicationContext.getBean(LocalSessionFactoryBean.class);

    private final SessionFactory sessionFactory = localSessionFactoryBean.getObject();

    @Override
    public List<Commodity> getAll() {
        Session session = null;

        List<Commodity> commodities = new ArrayList<>();
        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                String hql = "FROM Commodity WHERE isDeleted != 1";
                Query query = session.createQuery(hql);

                commodities = query.getResultList();

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return commodities;
    }

    @Override
    public int save(Commodity commodity) {
        Session session = null;
        int id = -1;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                id = (Integer) session.save(commodity);

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return id;
    }

    @Override
    public Commodity getById(Integer commodityId) {
        Session session = null;
        Commodity commodity = null;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                commodity = session.get(Commodity.class, commodityId);

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return commodity;
    }

    @Override
    public boolean update(Commodity commodity) {
        Session session = null;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                session.update(commodity);

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
}
