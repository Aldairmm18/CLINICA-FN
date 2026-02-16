package clinicapp.domain;

public abstract class Person {
    private final String id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String direccion;

    protected Person(String id, String nombres, String apellidos, String telefono, String email, String direccion) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.nombres = DomainValidator.requireNonBlank(nombres, "nombres");
        this.apellidos = DomainValidator.requireNonBlank(apellidos, "apellidos");
        this.telefono = DomainValidator.requireNonBlank(telefono, "telefono");
        this.email = DomainValidator.requireNonBlank(email, "email");
        this.direccion = DomainValidator.requireNonBlank(direccion, "direccion");
    }

    public String getId() { return id; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }

    public void actualizarContacto(String telefono, String email, String direccion) {
        this.telefono = DomainValidator.requireNonBlank(telefono, "telefono");
        this.email = DomainValidator.requireNonBlank(email, "email");
        this.direccion = DomainValidator.requireNonBlank(direccion, "direccion");
    }
}
