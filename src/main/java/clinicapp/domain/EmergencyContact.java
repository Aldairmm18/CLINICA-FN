package clinicapp.domain;

public class EmergencyContact {
    private String nombre;
    private String telefono;
    private RelationshipType relationshipType;

    public EmergencyContact(String nombre, String telefono, RelationshipType relationshipType) {
        this.nombre = DomainValidator.requireNonBlank(nombre, "nombre");
        this.telefono = DomainValidator.requireNonBlank(telefono, "telefono");
        this.relationshipType = DomainValidator.requireNonNull(relationshipType, "relationshipType");
    }

    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public RelationshipType getRelationshipType() { return relationshipType; }
}
