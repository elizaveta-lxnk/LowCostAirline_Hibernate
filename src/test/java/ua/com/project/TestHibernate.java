package ua.com.project;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.com.project.config.Factory;
import ua.com.project.entity.Flight;
import ua.com.project.entity.Passenger;
import ua.com.project.entity.Ticket;
import ua.com.project.entity.TicketOptions;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHibernate {

    @BeforeAll
    public static void setup() {
        // Ініціалізуємо фабрику перед початком тестів
        Factory.getEntityManagerFactory();
    }

    @Test
    public void testLowCostAirlineSystem() {
        EntityManager em = Factory.getEntityManager();
        em.getTransaction().begin();

        // 1. Створюємо новий рейс (Номер, Дата, Базова ціна: 50, Місткість: 180)
        Flight flight = new Flight("WZ-4044", LocalDateTime.now().plusDays(5), 50.0, 180);
        // Імітуємо, що літак уже заповнений (наприклад, 150 місць куплено), щоб перевірити підвищення ціни
        flight.setBookedSeats(150);
        em.persist(flight);

        // 2. Створюємо пасажира
        Passenger passenger = new Passenger("Оксана", "Левчук", "AB123456");
        em.persist(passenger);

        // 3. Створюємо опції лоукостера (додаємо багаж і пріоритетну посадку)
        TicketOptions options = new TicketOptions(true, true);

        // 4. Оформлюємо квиток
        Ticket ticket = new Ticket(flight, passenger, options);
        em.persist(ticket);

        em.getTransaction().commit();
        em.close();

        // Перевіряємо, чи успішно згенерувався ID для квитка в базі
        assertNotNull(ticket.getId(), "Квиток не зберігся в БД!");

        // Виводимо у консоль фінальну ціну, щоб переконатися, що логіка націнок спрацювала
        System.out.println("==================================================");
        System.out.println("Квиток успішно заброньовано!");
        System.out.println("Пасажир: " + ticket.getPassenger().getFirstName() + " " + ticket.getPassenger().getLastName());
        System.out.println("Рейс: " + ticket.getFlight().getFlightNumber());
        System.out.println("Фінальна вартість квитка з націнками: " + ticket.getFinalPrice() + " EUR");
        System.out.println("==================================================");

        assertTrue(ticket.getFinalPrice() > 50.0, "Ціна мала збільшитися за рахунок багажу та заповненості літака!");
    }

    @AfterAll
    public static void tearDown() {
        // Закриваємо фабрику після завершення тестів
        Factory.close();
    }
}