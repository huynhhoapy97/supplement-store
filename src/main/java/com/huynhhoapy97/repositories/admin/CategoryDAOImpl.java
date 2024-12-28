package com.huynhhoapy97.repositories.admin;

import com.huynhhoapy97.connections.HibernateConfiguration;
import com.huynhhoapy97.models.Category;
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
public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger log = LoggerFactory.getLogger(CategoryDAOImpl.class);
    private final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(HibernateConfiguration.class);
    private final LocalSessionFactoryBean localSessionFactoryBean =
            applicationContext.getBean(LocalSessionFactoryBean.class);

    private final SessionFactory sessionFactory = localSessionFactoryBean.getObject();

    @Override
    public boolean save(Category category) {
        Session session = null;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                session.save(category);

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
    public List<Category> getAll() {
        Session session = null;

        List<Category> categories = new ArrayList<>();
        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                String hql = "FROM Category WHERE isDeleted != 1";
                Query query = session.createQuery(hql);

                categories = query.getResultList();

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return categories;
    }

    @Override
    public List<Category> getAllWithoutCommodity() {
        Session session = null;

        List<Category> categories = new ArrayList<>();
        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                String hql = "FROM Category WHERE commodity.id = NULL AND isDeleted != 1";
                Query query = session.createQuery(hql);

                categories = query.getResultList();

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return categories;
    }

    @Override
    public Category getById(Integer categoryId) {
        Session session = null;
        Category category = null;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                category = session.get(Category.class, categoryId);

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return category;
    }

    @Override
    public boolean update(Category category) {
        Session session = null;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                session.update(category);

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
    public void fillCommodity(int commodityId, int categoryId) {
        Session session = null;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                String hql = "UPDATE Category SET commodity.id = :commodityId WHERE id = :categoryId";
                Query query = session.createQuery(hql);
                query.setParameter("commodityId", commodityId);
                query.setParameter("categoryId", categoryId);

                query.executeUpdate();

                transaction.commit();
            }
        } catch (Exception exception) {
            log.info("fillCommodity() exception", exception);
            throw exception;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Category> getByCommodityId(Integer commodityId) {
        Session session = null;
        List<Category> categories = new ArrayList<>();

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                String hql = "FROM Category WHERE commodity.id = :commodityId AND isDeleted != 1";
                Query query = session.createQuery(hql);
                query.setParameter("commodityId", commodityId);

                categories = query.getResultList();

                transaction.commit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return categories;
    }

    @Override
    public void refreshCommodity(int categoryId) {
        Session session = null;

        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();

                String hql = "UPDATE Category SET commodity.id = NULL WHERE id = :categoryId";
                Query query = session.createQuery(hql);
                query.setParameter("categoryId", categoryId);

                query.executeUpdate();

                transaction.commit();
            }
        } catch (Exception exception) {
            log.info("refreshCommodity() exception", exception);
            throw exception;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
