package ua.com.project.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Factory {
    private static EntityManagerFactory entityManagerFactory;

    // Метод, який створює фабрику (якщо вона ще не створена)
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            // Назва "LowCostAirlinePU" має суворо збігатися з name у файлі persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("LowCostAirlinePU");
        }
        return entityManagerFactory;
    }

    // Зручний метод для отримання EntityManager (робочої сесії з БД)
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    // Метод для закриття фабрики при завершенні програми
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}