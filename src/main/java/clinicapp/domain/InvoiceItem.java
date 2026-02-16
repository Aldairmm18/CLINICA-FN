package clinicapp.domain;

public class InvoiceItem {
    private final String descripcion;
    private final int cantidad;
    private final double valorUnitario;
    private final double subtotal;

    public InvoiceItem(String descripcion, int cantidad, double valorUnitario) {
        this.descripcion = DomainValidator.requireNonBlank(descripcion, "descripcion");
        this.cantidad = DomainValidator.requireNonNegative(cantidad, "cantidad");
        this.valorUnitario = DomainValidator.requireRange(valorUnitario, 0, Double.MAX_VALUE, "valorUnitario");
        this.subtotal = this.cantidad * this.valorUnitario;
    }

    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getValorUnitario() { return valorUnitario; }
    public double getSubtotal() { return subtotal; }
}
