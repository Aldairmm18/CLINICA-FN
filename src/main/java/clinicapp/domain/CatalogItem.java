package clinicapp.domain;

public abstract class CatalogItem {
    private final String id;
    private String nombre;
    private String descripcion;
    private boolean activo;

    protected CatalogItem(String id, String nombre, String descripcion, boolean activo) {
        this.id = DomainValidator.requireNonBlank(id, "id");
        this.nombre = DomainValidator.requireNonBlank(nombre, "nombre");
        this.descripcion = DomainValidator.requireNonBlank(descripcion, "descripcion");
        this.activo = activo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public boolean isActivo() { return activo; }

    public void actualizar(String nombre, String descripcion, boolean activo) {
        this.nombre = DomainValidator.requireNonBlank(nombre, "nombre");
        this.descripcion = DomainValidator.requireNonBlank(descripcion, "descripcion");
        this.activo = activo;
    }
}
