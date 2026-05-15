package ua.com.project.repository;

import jakarta.persistence.EntityManager;
import ua.com.project.config.Factory;
import ua.com.project.dao.GeneralDao;
import ua.com.project.entity.Passenger;

public class PassengerRepository {
    private final GeneralDao generalDao = new GeneralDao();

    public void save(Passenger passenger) {
        generalDao.save(passenger);
    }

    public Passenger findById(Long id) {
        return generalDao.findById(Passenger.class, id);
    }

    // Специфічний пошук пасажира за номером паспорта
    public Passenger findByPassport(String passportNumber) {
        EntityManager em = Factory.getEntityManager();
        try {
            return em.createQuery("FROM Passenger WHERE passportNumber = :passport", Passenger.class)
                    .setParameter("passport", passportNumber)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // якщо пасажира не знайдено
        } finally {
            em.close();
        }
    }
}