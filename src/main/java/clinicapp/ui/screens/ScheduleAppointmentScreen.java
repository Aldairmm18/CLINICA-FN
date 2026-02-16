package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.Appointment;
import clinicapp.domain.AppointmentStatus;
import clinicapp.domain.Employee;
import clinicapp.domain.Patient;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.PatientSearchPane;
import clinicapp.ui.components.UiAlerts;
import clinicapp.ui.controllers.screens.ScheduleAppointmentScreenController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class ScheduleAppointmentScreen implements FunctionalityScreen {
    private final VBox root;

    public ScheduleAppointmentScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        ScheduleAppointmentScreenController controller = new ScheduleAppointmentScreenController(appContext);

        Label title = new Label(functionality.getDisplayName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label selectedPatientLabel = new Label("Paciente seleccionado: ninguno");
        appContext.getSelectedPatientContext().getSelected().ifPresent(p ->
                selectedPatientLabel.setText("Paciente seleccionado: " + p.getCedula() + " - " + p.getNombres()));

        PatientSearchPane searchPane = new PatientSearchPane(appContext,
                p -> selectedPatientLabel.setText("Paciente seleccionado: " + p.getCedula() + " - " + p.getNombres()));

        ComboBox<Employee> doctorCombo = new ComboBox<>();
        doctorCombo.getItems().addAll(controller.getDoctors());
        doctorCombo.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombres() + " " + item.getApellidos());
            }
        });
        doctorCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombres() + " " + item.getApellidos());
            }
        });

        DatePicker date = new DatePicker();
        TextField hour = new TextField();
        hour.setPromptText("HH:mm");
        TextField motivo = new TextField();

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.addRow(0, new Label("Médico"), doctorCombo);
        form.addRow(1, new Label("Fecha"), date);
        form.addRow(2, new Label("Hora"), hour);
        form.addRow(3, new Label("Motivo"), motivo);

        Button save = new Button("Programar cita");
        save.setOnAction(e -> {
            Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
            if (patient == null) {
                UiAlerts.error("Validación", "Debe seleccionar un paciente.");
                return;
            }
            if (doctorCombo.getValue() == null || date.getValue() == null || hour.getText().isBlank() || motivo.getText().isBlank()) {
                UiAlerts.error("Validación", "Complete todos los campos obligatorios.");
                return;
            }
            LocalTime localTime;
            try {
                localTime = LocalTime.parse(hour.getText().trim());
            } catch (DateTimeParseException ex) {
                UiAlerts.error("Validación", "Hora inválida. Use formato HH:mm.");
                return;
            }
            Appointment appointment = new Appointment(
                    "APT-" + UUID.randomUUID(),
                    patient,
                    doctorCombo.getValue(),
                    LocalDateTime.of(date.getValue(), localTime),
                    motivo.getText().trim(),
                    AppointmentStatus.PROGRAMADA
            );
            controller.save(appointment);
            UiAlerts.info("Cita", "Cita programada correctamente.");
        });

        Button back = new Button("Volver al panel");
        back.setOnAction(e -> navigator.showRolePanel(role));

        root = new VBox(12, title, selectedPatientLabel, searchPane.getRoot(), form, save, back);
        root.setPadding(new Insets(24));
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
