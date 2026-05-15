package ua.com.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ua.com.project.config.Factory;

public class GeneralDao {

    // Універсальний метод для збереження будь-якої сутності
    public <T> void save(T entity) {
        EntityManager em = Factory.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Універсальний метод для пошуку за ID
    public <T> T findById(Class<T> clazz, Long id) {
        EntityManager em = Factory.getEntityManager();
        try {
            return em.find(clazz, id);
        } finally {
            em.close();
        }
    }
}