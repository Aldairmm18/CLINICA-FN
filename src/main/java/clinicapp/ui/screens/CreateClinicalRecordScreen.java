package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.ClinicalRecord;
import clinicapp.domain.Employee;
import clinicapp.domain.Patient;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import clinicapp.ui.controllers.screens.CreateClinicalRecordScreenController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateClinicalRecordScreen implements FunctionalityScreen {
    private final VBox root;

    public CreateClinicalRecordScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        CreateClinicalRecordScreenController controller = new CreateClinicalRecordScreenController(appContext);

        Label title = new Label(functionality.getDisplayName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label patientLabel = new Label();
        Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
        if (patient == null) {
            patientLabel.setText("Paciente seleccionado: ninguno");
        } else {
            patientLabel.setText("Paciente seleccionado: " + patient.getCedula() + " - " + patient.getNombres());
        }

        TextArea motivo = new TextArea();
        TextArea sintoma = new TextArea();
        TextArea diagnostico = new TextArea();
        TextArea observaciones = new TextArea();

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.addRow(0, new Label("Motivo"), motivo);
        form.addRow(1, new Label("Sintomatología"), sintoma);
        form.addRow(2, new Label("Diagnóstico"), diagnostico);
        form.addRow(3, new Label("Observaciones"), observaciones);

        Button save = new Button("Guardar registro");
        save.setOnAction(e -> {
            Patient selected = appContext.getSelectedPatientContext().getSelected().orElse(null);
            if (selected == null) {
                UiAlerts.error("Validación", "Debe seleccionar un paciente.");
                return;
            }
            if (motivo.getText().isBlank() || sintoma.getText().isBlank() || diagnostico.getText().isBlank()) {
                UiAlerts.error("Validación", "Motivo, sintomatología y diagnóstico son obligatorios.");
                return;
            }
            Employee doctor = appContext.getEmployeeService().buscarPorRole(Role.MEDICO).stream().findFirst().orElse(null);
            if (doctor == null) {
                UiAlerts.error("Datos", "No hay médicos registrados.");
                return;
            }
            ClinicalRecord record = new ClinicalRecord("CR-" + UUID.randomUUID(), selected, doctor, LocalDateTime.now(),
                    motivo.getText().trim(), sintoma.getText().trim(), diagnostico.getText().trim(), observaciones.getText().trim());
            controller.save(record);
            UiAlerts.info("Historia clínica", "Registro clínico guardado.");
        });

        Button back = new Button("Volver al panel");
        back.setOnAction(e -> navigator.showRolePanel(role));

        root = new VBox(12, title, patientLabel, form, save, back);
        root.setPadding(new Insets(24));
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
