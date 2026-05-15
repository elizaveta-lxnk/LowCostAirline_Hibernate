package ua.com.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_options")
public class TicketOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "has_baggage")
    private boolean hasBaggage; // чи є багаж

    @Column(name = "baggage_price")
    private double baggagePrice = 45.0; // фіксована ціна за багаж у лоукостера

    @Column(name = "has_priority")
    private boolean hasPriorityBoarding; // чи є пріоритетна посадка

    @Column(name = "priority_price")
    private double priorityPrice = 20.0; // ціна за пріоритет

    public TicketOptions() {}

    public TicketOptions(boolean hasBaggage, boolean hasPriorityBoarding) {
        this.hasBaggage = hasBaggage;
        this.hasPriorityBoarding = hasPriorityBoarding;
    }

    // Геттери та сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isHasBaggage() { return hasBaggage; }
    public void setHasBaggage(boolean hasBaggage) { this.hasBaggage = hasBaggage; }

    public double getBaggagePrice() { return baggagePrice; }
    public void setBaggagePrice(double baggagePrice) { this.baggagePrice = baggagePrice; }

    public boolean isHasPriorityBoarding() { return hasPriorityBoarding; }
    public void setHasPriorityBoarding(boolean hasPriorityBoarding) { this.hasPriorityBoarding = hasPriorityBoarding; }

    public double getPriorityPrice() { return priorityPrice; }
    public void setPriorityPrice(double priorityPrice) { this.priorityPrice = priorityPrice; }
}