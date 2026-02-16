package clinicapp.domain;

import java.time.LocalDate;

public class InsurancePolicy {
    private String aseguradora;
    private String numeroPoliza;
    private InsuranceType insuranceType;
    private boolean activa;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double porcentajeCobertura;

    public InsurancePolicy(String aseguradora, String numeroPoliza, InsuranceType insuranceType, boolean activa,
                           LocalDate fechaInicio, LocalDate fechaFin, double porcentajeCobertura) {
        this.aseguradora = DomainValidator.requireNonBlank(aseguradora, "aseguradora");
        this.numeroPoliza = DomainValidator.requireNonBlank(numeroPoliza, "numeroPoliza");
        this.insuranceType = DomainValidator.requireNonNull(insuranceType, "insuranceType");
        this.activa = activa;
        this.fechaInicio = DomainValidator.requireNonNull(fechaInicio, "fechaInicio");
        this.fechaFin = fechaFin;
        this.porcentajeCobertura = DomainValidator.requireRange(porcentajeCobertura, 0, 100, "porcentajeCobertura");
    }

    public String getAseguradora() { return aseguradora; }
    public String getNumeroPoliza() { return numeroPoliza; }
    public InsuranceType getInsuranceType() { return insuranceType; }
    public boolean isActiva() { return activa; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public double getPorcentajeCobertura() { return porcentajeCobertura; }
}
