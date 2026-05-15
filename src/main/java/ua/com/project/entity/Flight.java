package ua.com.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber; // наприклад, "FR-1234"

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "base_price", nullable = false)
    private double basePrice; // базова ціна квитка

    @Column(name = "capacity", nullable = false)
    private int capacity; // загальна кількість місць у літаку

    @Column(name = "booked_seats")
    private int bookedSeats = 0; // скільки місць уже викуплено

    // Порожній конструктор обов'язковий для Hibernate
    public Flight() {}

    public Flight(String flightNumber, LocalDateTime departureTime, double basePrice, int capacity) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.basePrice = basePrice;
        this.capacity = capacity;
    }

    // Геттери та сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getBookedSeats() { return bookedSeats; }
    public void setBookedSeats(int bookedSeats) { this.bookedSeats = bookedSeats; }
}