package clinicapp.domain;

public class PerformedDiagnosticTest {
    private final String id;
    private final NursingVisit visit;
    private final String orderNumber;
    private final String diagnosticAidItemId;
    private final String resultado;
    private final String observaciones;

    public PerformedDiagnosticTest(String id, NursingVisit visit, String orderNumber, String diagnosticAidItemId,
                                   String resultado, String observaciones) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.visit = DomainValidator.requireNonNull(visit, "visit");
        this.orderNumber = DomainValidator.requireNonBlank(orderNumber, "orderNumber");
        this.diagnosticAidItemId = DomainValidator.requireNonBlank(diagnosticAidItemId, "diagnosticAidItemId");
        this.resultado = DomainValidator.requireNonBlank(resultado, "resultado");
        this.observaciones = observaciones;
    }

    public String getId() { return id; }
    public NursingVisit getVisit() { return visit; }
    public String getOrderNumber() { return orderNumber; }
    public String getDiagnosticAidItemId() { return diagnosticAidItemId; }
    public String getResultado() { return resultado; }
    public String getObservaciones() { return observaciones; }
}
