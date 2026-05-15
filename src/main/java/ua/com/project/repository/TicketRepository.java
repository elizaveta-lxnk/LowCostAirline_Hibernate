package ua.com.project.repository;

import ua.com.project.dao.GeneralDao;
import ua.com.project.entity.Ticket;

public class TicketRepository {
    private final GeneralDao generalDao = new GeneralDao();

    public void save(Ticket ticket) {
        generalDao.save(ticket);
    }

    public Ticket findById(Long id) {
        return generalDao.findById(Ticket.class, id);
    }
}