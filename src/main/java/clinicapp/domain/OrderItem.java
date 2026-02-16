package clinicapp.domain;

public class OrderItem {
    private final String catalogItemId;
    private final String nombre;
    private final int cantidad;
    private final String notas;

    public OrderItem(String catalogItemId, String nombre, int cantidad, String notas) {
        this.catalogItemId = DomainValidator.requireNonBlank(catalogItemId, "catalogItemId");
        this.nombre = DomainValidator.requireNonBlank(nombre, "nombre");
        this.cantidad = DomainValidator.requireNonNegative(cantidad, "cantidad");
        this.notas = notas;
    }

    public String getCatalogItemId() { return catalogItemId; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public String getNotas() { return notas; }
}
