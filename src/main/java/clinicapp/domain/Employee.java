package clinicapp.domain;

import clinicapp.Role;

import java.time.LocalDate;

public class Employee extends Person {
    private final String employeeId;
    private Role role;
    private LocalDate fechaIngreso;
    private EmployeeStatus status;

    public Employee(String id, String nombres, String apellidos, String telefono, String email, String direccion,
                    String employeeId, Role role, LocalDate fechaIngreso, EmployeeStatus status) {
        super(id, nombres, apellidos, telefono, email, direccion);
        this.employeeId = DomainValidator.requireNonBlank(employeeId, "employeeId");
        this.role = DomainValidator.requireNonNull(role, "role");
        this.fechaIngreso = DomainValidator.requireNonNull(fechaIngreso, "fechaIngreso");
        this.status = DomainValidator.requireNonNull(status, "status");
    }

    public String getEmployeeId() { return employeeId; }
    public Role getRole() { return role; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public EmployeeStatus getStatus() { return status; }

    public void updateJobData(Role role, EmployeeStatus status) {
        this.role = DomainValidator.requireNonNull(role, "role");
        this.status = DomainValidator.requireNonNull(status, "status");
    }
}
