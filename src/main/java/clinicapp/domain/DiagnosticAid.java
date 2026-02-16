package clinicapp.domain;

public class DiagnosticAid extends CatalogItem {
    private DiagnosticAidCategory categoria;

    public DiagnosticAid(String id, String nombre, String descripcion, boolean activo, DiagnosticAidCategory categoria) {
        super(id, nombre, descripcion, activo);
        this.categoria = DomainValidator.requireNonNull(categoria, "categoria");
    }

    public DiagnosticAidCategory getCategoria() { return categoria; }
}
