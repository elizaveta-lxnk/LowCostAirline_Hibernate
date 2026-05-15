package ua.com.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Зв'язок: Багато квитків на один рейс
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    // Зв'язок: Багато квитків на одного пасажира
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    // Зв'язок Один до одного: один квиток — один набір опцій
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "options_id")
    private TicketOptions options;

    @Column(name = "final_price")
    private double finalPrice;

    public Ticket() {}

    public Ticket(Flight flight, Passenger passenger, TicketOptions options) {
        this.flight = flight;
        this.passenger = passenger;
        this.options = options;
        calculateFinalPrice(); // Рахуємо ціну при створенні квитка
    }

    // Бізнес-логіка лоукостера: динамічна ціна + опції
    public void calculateFinalPrice() {
        if (flight == null) return;

        double price = flight.getBasePrice();

        // 1. Динамічне підвищення ціни залежно від наповненості літака
        double fullness = (double) flight.getBookedSeats() / flight.getCapacity();
        if (fullness > 0.8) {
            price *= 1.5; // якщо літак заповнений на 80%+, ціна зростає на 50%
        } else if (fullness > 0.5) {
            price *= 1.2; // якщо заповнений на 50%+, ціна зростає на 20%
        }

        // 2. Додаємо вартість багажу та пріоритетної посадки
        if (options != null) {
            if (options.isHasBaggage()) {
                price += options.getBaggagePrice();
            }
            if (options.isHasPriorityBoarding()) {
                price += options.getPriorityPrice();
            }
        }

        this.finalPrice = price;
    }

    // Геттери та сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Passenger getPassenger() { return passenger; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public TicketOptions getOptions() { return options; }
    public void setOptions(TicketOptions options) { this.options = options; }

    public double getFinalPrice() { return finalPrice; }
    public void setFinalPrice(double finalPrice) { this.finalPrice = finalPrice; }
}