package clinicapp.domain;

public class PerformedProcedure {
    private final String id;
    private final NursingVisit visit;
    private final String orderNumber;
    private final String procedureItemId;
    private final String observaciones;

    public PerformedProcedure(String id, NursingVisit visit, String orderNumber, String procedureItemId, String observaciones) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.visit = DomainValidator.requireNonNull(visit, "visit");
        this.orderNumber = DomainValidator.requireNonBlank(orderNumber, "orderNumber");
        this.procedureItemId = DomainValidator.requireNonBlank(procedureItemId, "procedureItemId");
        this.observaciones = observaciones;
    }

    public String getId() { return id; }
    public NursingVisit getVisit() { return visit; }
    public String getOrderNumber() { return orderNumber; }
    public String getProcedureItemId() { return procedureItemId; }
    public String getObservaciones() { return observaciones; }
}
