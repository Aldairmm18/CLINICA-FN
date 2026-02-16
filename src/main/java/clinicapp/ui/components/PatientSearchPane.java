package clinicapp.ui.components;

import clinicapp.AppContext;
import clinicapp.domain.Patient;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class PatientSearchPane {
    private final VBox root;
    private final Label resultLabel;
    private Patient foundPatient;

    public PatientSearchPane(AppContext appContext, Consumer<Patient> onSelected) {
        TextField cedulaField = new TextField();
        cedulaField.setPromptText("Cédula");

        Button searchButton = new Button("Buscar");
        Button selectButton = new Button("Seleccionar paciente");
        selectButton.setDisable(true);

        resultLabel = new Label("Sin búsqueda");

        searchButton.setOnAction(event -> {
            String cedula = cedulaField.getText();
            if (cedula == null || cedula.isBlank()) {
                UiAlerts.error("Validación", "La cédula es obligatoria.");
                return;
            }
            foundPatient = appContext.getPatientService().buscarPorCedula(cedula.trim()).orElse(null);
            if (foundPatient == null) {
                resultLabel.setText("Paciente no encontrado");
                selectButton.setDisable(true);
                return;
            }
            resultLabel.setText("Encontrado: " + foundPatient.getCedula() + " - " + foundPatient.getNombres() + " " + foundPatient.getApellidos());
            selectButton.setDisable(false);
        });

        selectButton.setOnAction(event -> {
            if (foundPatient != null) {
                appContext.getSelectedPatientContext().setSelected(foundPatient);
                onSelected.accept(foundPatient);
                UiAlerts.info("Paciente", "Paciente seleccionado correctamente.");
            }
        });

        HBox row = new HBox(8, cedulaField, searchButton, selectButton);
        root = new VBox(8, new Label("Buscar paciente por cédula"), row, resultLabel);
        root.setPadding(new Insets(8, 0, 8, 0));
    }

    public VBox getRoot() {
        return root;
    }
}
