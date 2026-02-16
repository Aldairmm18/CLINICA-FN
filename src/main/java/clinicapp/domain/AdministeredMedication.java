package clinicapp.domain;

import java.time.LocalDateTime;

public class AdministeredMedication {
    private final String id;
    private final NursingVisit visit;
    private final String orderNumber;
    private final String medicationItemId;
    private final String dosis;
    private final LocalDateTime hora;
    private final String observaciones;

    public AdministeredMedication(String id, NursingVisit visit, String orderNumber, String medicationItemId,
                                  String dosis, LocalDateTime hora, String observaciones) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.visit = DomainValidator.requireNonNull(visit, "visit");
        this.orderNumber = DomainValidator.requireNonBlank(orderNumber, "orderNumber");
        this.medicationItemId = DomainValidator.requireNonBlank(medicationItemId, "medicationItemId");
        this.dosis = DomainValidator.requireNonBlank(dosis, "dosis");
        this.hora = DomainValidator.requireNonNull(hora, "hora");
        this.observaciones = observaciones;
    }

    public String getId() { return id; }
    public NursingVisit getVisit() { return visit; }
    public String getOrderNumber() { return orderNumber; }
    public String getMedicationItemId() { return medicationItemId; }
    public String getDosis() { return dosis; }
    public LocalDateTime getHora() { return hora; }
    public String getObservaciones() { return observaciones; }
}
