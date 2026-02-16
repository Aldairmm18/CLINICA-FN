package clinicapp.domain;

public class Medication extends CatalogItem {
    private String concentracion;
    private MedicationForm forma;
    private int stock;

    public Medication(String id, String nombre, String descripcion, boolean activo,
                      String concentracion, MedicationForm forma, int stock) {
        super(id, nombre, descripcion, activo);
        this.concentracion = DomainValidator.requireNonBlank(concentracion, "concentracion");
        this.forma = DomainValidator.requireNonNull(forma, "forma");
        this.stock = DomainValidator.requireNonNegative(stock, "stock");
    }

    public String getConcentracion() { return concentracion; }
    public MedicationForm getForma() { return forma; }
    public int getStock() { return stock; }

    public void adjustStock(int delta) {
        int nuevoStock = this.stock + delta;
        this.stock = DomainValidator.requireNonNegative(nuevoStock, "stock");
    }
}
