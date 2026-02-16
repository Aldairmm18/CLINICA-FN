package clinicapp.domain;

public class Procedure extends CatalogItem {
    private ProcedureCategory categoria;

    public Procedure(String id, String nombre, String descripcion, boolean activo, ProcedureCategory categoria) {
        super(id, nombre, descripcion, activo);
        this.categoria = DomainValidator.requireNonNull(categoria, "categoria");
    }

    public ProcedureCategory getCategoria() { return categoria; }
}
