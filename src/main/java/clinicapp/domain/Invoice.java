package clinicapp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Invoice {
    private final String id;
    private final Patient patient;
    private final Appointment appointment;
    private final LocalDate fecha;
    private final List<InvoiceItem> items = new ArrayList<>();
    private double total;
    private InvoiceStatus status;

    public Invoice(String id, Patient patient, Appointment appointment, LocalDate fecha,
                   List<InvoiceItem> items, InvoiceStatus status) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.patient = DomainValidator.requireNonNull(patient, "patient");
        this.appointment = appointment;
        this.fecha = DomainValidator.requireNonNull(fecha, "fecha");
        this.status = DomainValidator.requireNonNull(status, "status");
        if (items != null) {
            this.items.addAll(items);
        }
        recalculateTotal();
    }

    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public Appointment getAppointment() { return appointment; }
    public LocalDate getFecha() { return fecha; }
    public List<InvoiceItem> getItems() { return Collections.unmodifiableList(items); }
    public double getTotal() { return total; }
    public InvoiceStatus getStatus() { return status; }

    public void addItem(InvoiceItem item) {
        this.items.add(DomainValidator.requireNonNull(item, "item"));
        recalculateTotal();
    }

    public void setStatus(InvoiceStatus status) {
        this.status = DomainValidator.requireNonNull(status, "status");
    }

    private void recalculateTotal() {
        this.total = items.stream().mapToDouble(InvoiceItem::getSubtotal).sum();
    }
}
