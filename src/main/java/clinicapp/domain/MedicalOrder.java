package clinicapp.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MedicalOrder {
    private final String orderNumber;
    private final Patient patient;
    private final Employee doctor;
    private final LocalDateTime fechaHora;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;

    protected MedicalOrder(String orderNumber, Patient patient, Employee doctor, LocalDateTime fechaHora,
                           List<OrderItem> items, OrderStatus status) {
        this.orderNumber = DomainValidator.requireNonBlank(orderNumber, "orderNumber");
        this.patient = DomainValidator.requireNonNull(patient, "patient");
        this.doctor = DomainValidator.requireNonNull(doctor, "doctor");
        this.fechaHora = DomainValidator.requireNonNull(fechaHora, "fechaHora");
        this.status = DomainValidator.requireNonNull(status, "status");
        if (items != null) {
            this.items.addAll(items);
        }
    }

    public String getOrderNumber() { return orderNumber; }
    public Patient getPatient() { return patient; }
    public Employee getDoctor() { return doctor; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
    public OrderStatus getStatus() { return status; }

    public void addItem(OrderItem item) {
        this.items.add(DomainValidator.requireNonNull(item, "item"));
    }

    public void setStatus(OrderStatus status) {
        this.status = DomainValidator.requireNonNull(status, "status");
    }
}
