package clinicapp.domain;

import java.time.LocalDateTime;

public class NursingVisit {
    private final String id;
    private final Patient patient;
    private final Employee nurse;
    private final LocalDateTime fechaHora;
    private final VitalSigns vitalSigns;
    private final String notas;

    public NursingVisit(String id, Patient patient, Employee nurse, LocalDateTime fechaHora, VitalSigns vitalSigns, String notas) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.patient = DomainValidator.requireNonNull(patient, "patient");
        this.nurse = DomainValidator.requireNonNull(nurse, "nurse");
        this.fechaHora = DomainValidator.requireNonNull(fechaHora, "fechaHora");
        this.vitalSigns = DomainValidator.requireNonNull(vitalSigns, "vitalSigns");
        this.notas = notas;
    }

    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public Employee getNurse() { return nurse; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public VitalSigns getVitalSigns() { return vitalSigns; }
    public String getNotas() { return notas; }
}
