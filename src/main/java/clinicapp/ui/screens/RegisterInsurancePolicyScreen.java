package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.*;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class RegisterInsurancePolicyScreen implements FunctionalityScreen {
    private final Parent root;

    public RegisterInsurancePolicyScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Label selected = new Label("Paciente seleccionado: " + appContext.getSelectedPatientContext().getSelected().map(Patient::getCedula).orElse("ninguno"));

        TextField aseguradora = new TextField();
        TextField numero = new TextField();
        ComboBox<InsuranceType> type = new ComboBox<>(FXCollections.observableArrayList(InsuranceType.values()));
        CheckBox activa = new CheckBox("Activa");
        activa.setSelected(true);
        DatePicker inicio = new DatePicker();
        DatePicker fin = new DatePicker();
        TextField cobertura = new TextField();

        GridPane form = new GridPane(); form.setHgap(8); form.setVgap(8);
        form.addRow(0, new Label("Aseguradora"), aseguradora);
        form.addRow(1, new Label("Número póliza"), numero);
        form.addRow(2, new Label("Tipo"), type);
        form.addRow(3, new Label("Fecha inicio"), inicio);
        form.addRow(4, new Label("Fecha fin"), fin);
        form.addRow(5, new Label("Cobertura 0..100"), cobertura);
        form.addRow(6, new Label("Estado"), activa);

        Button save = new Button("Guardar póliza");
        save.setOnAction(e -> {
            Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
            if (patient == null) { UiAlerts.error("Validación", "Debe seleccionar paciente."); return; }
            if (aseguradora.getText().isBlank() || numero.getText().isBlank() || type.getValue()==null || inicio.getValue()==null || cobertura.getText().isBlank()) {
                UiAlerts.error("Validación", "Campos obligatorios incompletos."); return;
            }
            try {
                double cov = Double.parseDouble(cobertura.getText().trim());
                InsurancePolicy policy = new InsurancePolicy(aseguradora.getText().trim(), numero.getText().trim(), type.getValue(), activa.isSelected(), inicio.getValue(), fin.getValue(), cov);
                patient.updateProfile(patient.getFechaNacimiento(), patient.getSex(), patient.getEmergencyContact(), policy);
                appContext.getPatientService().actualizar(patient);
                UiAlerts.info("Paciente", "Póliza registrada/actualizada.");
            } catch (NumberFormatException ex) {
                UiAlerts.error("Validación", "Cobertura inválida.");
            }
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, selected, form, save);
    }

    @Override public Parent getRoot() { return root; }
}
