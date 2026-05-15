package ua.com.project.repository;

import ua.com.project.dao.GeneralDao;
import ua.com.project.entity.Flight;

public class FlightRepository {
    private final GeneralDao generalDao = new GeneralDao();

    public void save(Flight flight) {
        generalDao.save(flight);
    }

    public Flight findById(Long id) {
        return generalDao.findById(Flight.class, id);
    }
}