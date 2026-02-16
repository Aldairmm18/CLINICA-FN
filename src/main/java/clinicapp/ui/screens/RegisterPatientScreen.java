package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.Patient;
import clinicapp.domain.Sex;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import clinicapp.ui.controllers.screens.RegisterPatientScreenController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.UUID;

public class RegisterPatientScreen implements FunctionalityScreen {
    private final VBox root;

    public RegisterPatientScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        RegisterPatientScreenController controller = new RegisterPatientScreenController(appContext);

        Label title = new Label(functionality.getDisplayName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField nombres = new TextField();
        TextField apellidos = new TextField();
        TextField cedula = new TextField();
        TextField telefono = new TextField();
        TextField email = new TextField();
        TextField direccion = new TextField();
        DatePicker nacimiento = new DatePicker();
        ComboBox<Sex> sex = new ComboBox<>();
        sex.getItems().addAll(Sex.values());

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.addRow(0, new Label("Nombres"), nombres);
        form.addRow(1, new Label("Apellidos"), apellidos);
        form.addRow(2, new Label("Cédula"), cedula);
        form.addRow(3, new Label("Teléfono"), telefono);
        form.addRow(4, new Label("Email"), email);
        form.addRow(5, new Label("Dirección"), direccion);
        form.addRow(6, new Label("Fecha nacimiento"), nacimiento);
        form.addRow(7, new Label("Sexo"), sex);

        Button save = new Button("Guardar paciente");
        save.setOnAction(e -> {
            if (nombres.getText().isBlank() || apellidos.getText().isBlank() || cedula.getText().isBlank() ||
                    telefono.getText().isBlank() || email.getText().isBlank() || direccion.getText().isBlank() ||
                    nacimiento.getValue() == null || sex.getValue() == null) {
                UiAlerts.error("Validación", "Todos los campos obligatorios deben diligenciarse.");
                return;
            }
            Patient patient = new Patient(
                    "PAT-" + UUID.randomUUID(),
                    nombres.getText(),
                    apellidos.getText(),
                    telefono.getText(),
                    email.getText(),
                    direccion.getText(),
                    cedula.getText(),
                    LocalDate.from(nacimiento.getValue()),
                    sex.getValue(),
                    null,
                    null
            );
            controller.save(patient);
            UiAlerts.info("Paciente", "Paciente registrado correctamente.");
            cedula.clear();
        });

        Button back = new Button("Volver al panel");
        back.setOnAction(e -> navigator.showRolePanel(role));

        root = new VBox(12, title, form, save, back);
        root.setPadding(new Insets(24));
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
