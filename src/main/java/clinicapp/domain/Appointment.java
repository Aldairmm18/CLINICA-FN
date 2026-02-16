package clinicapp.domain;

import java.time.LocalDateTime;

public class Appointment {
    private final String id;
    private final Patient patient;
    private final Employee doctor;
    private LocalDateTime fechaHora;
    private String motivo;
    private AppointmentStatus status;

    public Appointment(String id, Patient patient, Employee doctor, LocalDateTime fechaHora, String motivo, AppointmentStatus status) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.patient = DomainValidator.requireNonNull(patient, "patient");
        this.doctor = DomainValidator.requireNonNull(doctor, "doctor");
        this.fechaHora = DomainValidator.requireNonNull(fechaHora, "fechaHora");
        this.motivo = DomainValidator.requireNonBlank(motivo, "motivo");
        this.status = DomainValidator.requireNonNull(status, "status");
    }

    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public Employee getDoctor() { return doctor; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getMotivo() { return motivo; }
    public AppointmentStatus getStatus() { return status; }

    public void cancel() { this.status = AppointmentStatus.CANCELADA; }
    public void complete() { this.status = AppointmentStatus.COMPLETADA; }
}
