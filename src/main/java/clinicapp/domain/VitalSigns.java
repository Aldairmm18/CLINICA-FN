package clinicapp.domain;

public class VitalSigns {
    private final String presionArterial;
    private final double temperatura;
    private final int pulso;
    private final int oxigeno;

    public VitalSigns(String presionArterial, double temperatura, int pulso, int oxigeno) {
        this.presionArterial = DomainValidator.requireNonBlank(presionArterial, "presionArterial");
        this.temperatura = temperatura;
        this.pulso = pulso;
        this.oxigeno = oxigeno;
    }

    public String getPresionArterial() { return presionArterial; }
    public double getTemperatura() { return temperatura; }
    public int getPulso() { return pulso; }
    public int getOxigeno() { return oxigeno; }
}
