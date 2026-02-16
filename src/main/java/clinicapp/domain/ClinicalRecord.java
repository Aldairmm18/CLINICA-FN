package clinicapp.domain;

import java.time.LocalDateTime;

public class ClinicalRecord {
    private final String id;
    private final Patient patient;
    private final Employee doctor;
    private final LocalDateTime fechaHora;
    private final String motivo;
    private final String sintomatologia;
    private final String diagnostico;
    private final String observaciones;

    public ClinicalRecord(String id, Patient patient, Employee doctor, LocalDateTime fechaHora,
                          String motivo, String sintomatologia, String diagnostico, String observaciones) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.patient = DomainValidator.requireNonNull(patient, "patient");
        this.doctor = DomainValidator.requireNonNull(doctor, "doctor");
        this.fechaHora = DomainValidator.requireNonNull(fechaHora, "fechaHora");
        this.motivo = DomainValidator.requireNonBlank(motivo, "motivo");
        this.sintomatologia = DomainValidator.requireNonBlank(sintomatologia, "sintomatologia");
        this.diagnostico = DomainValidator.requireNonBlank(diagnostico, "diagnostico");
        this.observaciones = observaciones;
    }

    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public Employee getDoctor() { return doctor; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getMotivo() { return motivo; }
    public String getSintomatologia() { return sintomatologia; }
    public String getDiagnostico() { return diagnostico; }
    public String getObservaciones() { return observaciones; }
}
