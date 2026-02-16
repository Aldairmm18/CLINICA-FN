package clinicapp.domain;

import java.time.LocalDateTime;

public class SupportTicket {
    private final String id;
    private final String usuario;
    private final String asunto;
    private final String descripcion;
    private final LocalDateTime fecha;

    public SupportTicket(String id, String usuario, String asunto, String descripcion, LocalDateTime fecha) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.usuario = DomainValidator.requireNonBlank(usuario, "usuario");
        this.asunto = DomainValidator.requireNonBlank(asunto, "asunto");
        this.descripcion = DomainValidator.requireNonBlank(descripcion, "descripcion");
        this.fecha = DomainValidator.requireNonNull(fecha, "fecha");
    }

    public String getId() { return id; }
    public String getUsuario() { return usuario; }
    public String getAsunto() { return asunto; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFecha() { return fecha; }
}
