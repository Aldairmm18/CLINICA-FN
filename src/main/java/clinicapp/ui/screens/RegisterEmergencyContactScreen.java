package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.EmergencyContact;
import clinicapp.domain.Patient;
import clinicapp.domain.RelationshipType;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.PatientSearchPane;
import clinicapp.ui.components.UiAlerts;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class RegisterEmergencyContactScreen implements FunctionalityScreen {
    private final Parent root;

    public RegisterEmergencyContactScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Label patientLabel = new Label("Paciente seleccionado: " + appContext.getSelectedPatientContext().getSelected().map(Patient::getCedula).orElse("ninguno"));
        PatientSearchPane searchPane = new PatientSearchPane(appContext, p -> patientLabel.setText("Paciente seleccionado: " + p.getCedula()));

        TextField nombre = new TextField();
        TextField telefono = new TextField();
        ComboBox<RelationshipType> relation = new ComboBox<>(FXCollections.observableArrayList(RelationshipType.values()));

        GridPane form = new GridPane();
        form.setHgap(8); form.setVgap(8);
        form.addRow(0, new Label("Nombre"), nombre);
        form.addRow(1, new Label("Teléfono"), telefono);
        form.addRow(2, new Label("Relación"), relation);

        Button save = new Button("Guardar contacto");
        save.setOnAction(e -> {
            Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
            if (patient == null) { UiAlerts.error("Validación", "Seleccione paciente."); return; }
            if (nombre.getText().isBlank() || telefono.getText().isBlank() || relation.getValue()==null) { UiAlerts.error("Validación", "Complete campos."); return; }
            EmergencyContact contact = new EmergencyContact(nombre.getText().trim(), telefono.getText().trim(), relation.getValue());
            patient.updateProfile(patient.getFechaNacimiento(), patient.getSex(), contact, patient.getInsurancePolicy());
            appContext.getPatientService().actualizar(patient);
            UiAlerts.info("Paciente", "Contacto de emergencia guardado.");
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, patientLabel, searchPane.getRoot(), form, save);
    }

    @Override public Parent getRoot() { return root; }
}
