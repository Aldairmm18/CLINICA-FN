package clinicapp;

public enum Role {
    RECURSOS_HUMANOS("Recursos Humanos"),
    PERSONAL_ADMINISTRATIVO("Personal Administrativo"),
    SOPORTE_DE_INFORMACION("Soporte de Información"),
    ENFERMERIA("Enfermería"),
    MEDICO("Médico");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
