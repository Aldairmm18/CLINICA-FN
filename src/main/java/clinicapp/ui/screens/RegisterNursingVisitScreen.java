package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.Employee;
import clinicapp.domain.NursingVisit;
import clinicapp.domain.Patient;
import clinicapp.domain.VitalSigns;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import clinicapp.ui.controllers.screens.RegisterNursingVisitScreenController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterNursingVisitScreen implements FunctionalityScreen {
    private final VBox root;

    public RegisterNursingVisitScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        RegisterNursingVisitScreenController controller = new RegisterNursingVisitScreenController(appContext);

        Label title = new Label(functionality.getDisplayName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label patientLabel = new Label("Paciente seleccionado: " + appContext.getSelectedPatientContext()
                .getSelected().map(p -> p.getCedula() + " - " + p.getNombres()).orElse("ninguno"));

        ComboBox<Employee> nurseCombo = new ComboBox<>();
        nurseCombo.getItems().addAll(controller.getNurses());
        nurseCombo.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombres() + " " + item.getApellidos());
            }
        });
        nurseCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombres() + " " + item.getApellidos());
            }
        });

        TextField presion = new TextField();
        TextField temperatura = new TextField();
        TextField pulso = new TextField();
        TextField oxigeno = new TextField();
        TextArea notas = new TextArea();

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.addRow(0, new Label("Enfermera"), nurseCombo);
        form.addRow(1, new Label("Presión arterial"), presion);
        form.addRow(2, new Label("Temperatura"), temperatura);
        form.addRow(3, new Label("Pulso"), pulso);
        form.addRow(4, new Label("Oxígeno"), oxigeno);
        form.addRow(5, new Label("Notas"), notas);

        Button save = new Button("Registrar visita");
        save.setOnAction(e -> {
            Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
            if (patient == null) {
                UiAlerts.error("Validación", "Debe seleccionar un paciente.");
                return;
            }
            if (nurseCombo.getValue() == null || presion.getText().isBlank() || temperatura.getText().isBlank() || pulso.getText().isBlank() || oxigeno.getText().isBlank()) {
                UiAlerts.error("Validación", "Complete los campos obligatorios.");
                return;
            }
            try {
                VitalSigns signs = new VitalSigns(presion.getText().trim(),
                        Double.parseDouble(temperatura.getText().trim()),
                        Integer.parseInt(pulso.getText().trim()),
                        Integer.parseInt(oxigeno.getText().trim()));
                NursingVisit visit = new NursingVisit("NV-" + UUID.randomUUID(), patient, nurseCombo.getValue(), LocalDateTime.now(), signs, notas.getText().trim());
                controller.saveVisit(visit);
                UiAlerts.info("Enfermería", "Visita registrada correctamente.");
            } catch (NumberFormatException ex) {
                UiAlerts.error("Validación", "Temperatura, pulso y oxígeno deben ser numéricos.");
            }
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
