package clinicapp.domain;

import java.time.LocalDate;

public class Patient extends Person {
    private final String cedula;
    private LocalDate fechaNacimiento;
    private Sex sex;
    private EmergencyContact emergencyContact;
    private InsurancePolicy insurancePolicy;

    public Patient(String id, String nombres, String apellidos, String telefono, String email, String direccion,
                   String cedula, LocalDate fechaNacimiento, Sex sex,
                   EmergencyContact emergencyContact, InsurancePolicy insurancePolicy) {
        super(id, nombres, apellidos, telefono, email, direccion);
        this.cedula = DomainValidator.requireNonBlank(cedula, "cedula");
        this.fechaNacimiento = DomainValidator.requireNonNull(fechaNacimiento, "fechaNacimiento");
        this.sex = DomainValidator.requireNonNull(sex, "sex");
        this.emergencyContact = emergencyContact;
        this.insurancePolicy = insurancePolicy;
    }

    public String getCedula() { return cedula; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public Sex getSex() { return sex; }
    public EmergencyContact getEmergencyContact() { return emergencyContact; }
    public InsurancePolicy getInsurancePolicy() { return insurancePolicy; }

    public void updateProfile(LocalDate fechaNacimiento, Sex sex, EmergencyContact emergencyContact, InsurancePolicy insurancePolicy) {
        this.fechaNacimiento = DomainValidator.requireNonNull(fechaNacimiento, "fechaNacimiento");
        this.sex = DomainValidator.requireNonNull(sex, "sex");
        this.emergencyContact = emergencyContact;
        this.insurancePolicy = insurancePolicy;
    }
}
