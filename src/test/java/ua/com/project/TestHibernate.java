package ua.com.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.com.project.config.Factory;
import ua.com.project.entity.Flight;
import ua.com.project.entity.Passenger;
import ua.com.project.entity.Ticket;
import ua.com.project.entity.TicketOptions;
import ua.com.project.repository.FlightRepository;
import ua.com.project.repository.PassengerRepository;
import ua.com.project.repository.TicketRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHibernate {

    @BeforeAll
    public static void setup() {
        Factory.getEntityManagerFactory();
    }

    @Test
    public void testLowCostAirlineSystem() {
        // Ініціалізуємо наші нові репозиторії
        FlightRepository flightRepository = new FlightRepository();
        PassengerRepository passengerRepository = new PassengerRepository();
        TicketRepository ticketRepository = new TicketRepository();

        // 1. Створюємо та зберігаємо рейс через репозиторій
        Flight flight = new Flight("WZ-4044", LocalDateTime.now().plusDays(5), 50.0, 180);
        flight.setBookedSeats(150);
        flightRepository.save(flight);

        // 2. Створюємо та зберігаємо пасажира через репозиторій
        Passenger passenger = new Passenger("Оксана", "Самойлова", "AB123456");
        passengerRepository.save(passenger);

        // 3. Оформлюємо квиток з опціями лоукостера
        TicketOptions options = new TicketOptions(true, true);
        Ticket ticket = new Ticket(flight, passenger, options);

        // Зберігаємо квиток через репозиторій
        ticketRepository.save(ticket);

        // Перевіряємо результат
        assertNotNull(ticket.getId(), "Квиток не зберігся через репозиторій!");

        System.out.println("==================================================");
        System.out.println("Квиток успішно збережено через ШАР РЕПОЗИТОРІЇВ!");
        System.out.println("Пасажир: " + ticket.getPassenger().getFirstName() + " " + ticket.getPassenger().getLastName());
        System.out.println("Рейс: " + ticket.getFlight().getFlightNumber());
        System.out.println("Фінальна вартість квитка: " + ticket.getFinalPrice() + " EUR");
        System.out.println("==================================================");

        assertTrue(ticket.getFinalPrice() > 50.0);
    }

    @AfterAll
    public static void tearDown() {
        Factory.close();
    }
}