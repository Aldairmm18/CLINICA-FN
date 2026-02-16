package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.Employee;
import clinicapp.domain.EmployeeStatus;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import clinicapp.ui.controllers.screens.CreateEmployeeScreenController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class CreateEmployeeScreen implements FunctionalityScreen {
    private final VBox root;

    public CreateEmployeeScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        CreateEmployeeScreenController controller = new CreateEmployeeScreenController(appContext);

        Label title = new Label("Crear empleado");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField id = new TextField();
        TextField employeeId = new TextField();
        TextField nombres = new TextField();
        TextField apellidos = new TextField();
        TextField telefono = new TextField();
        TextField email = new TextField();
        TextField direccion = new TextField();
        ComboBox<Role> roleCombo = new ComboBox<>();
        roleCombo.getItems().addAll(Role.values());
        DatePicker ingreso = new DatePicker(LocalDate.now());

        GridPane form = new GridPane();
        form.setHgap(8); form.setVgap(8);
        form.addRow(0, new Label("ID"), id);
        form.addRow(1, new Label("Employee ID"), employeeId);
        form.addRow(2, new Label("Nombres"), nombres);
        form.addRow(3, new Label("Apellidos"), apellidos);
        form.addRow(4, new Label("Teléfono"), telefono);
        form.addRow(5, new Label("Email"), email);
        form.addRow(6, new Label("Dirección"), direccion);
        form.addRow(7, new Label("Rol"), roleCombo);
        form.addRow(8, new Label("Fecha ingreso"), ingreso);

        Button save = new Button("Crear empleado");
        save.setOnAction(e -> {
            if (id.getText().isBlank() || employeeId.getText().isBlank() || nombres.getText().isBlank() || apellidos.getText().isBlank() ||
                    telefono.getText().isBlank() || email.getText().isBlank() || direccion.getText().isBlank() || roleCombo.getValue() == null || ingreso.getValue() == null) {
                UiAlerts.error("Validación", "Todos los campos son obligatorios.");
                return;
            }
            Employee employee = new Employee(id.getText().trim(), nombres.getText().trim(), apellidos.getText().trim(),
                    telefono.getText().trim(), email.getText().trim(), direccion.getText().trim(),
                    employeeId.getText().trim(), roleCombo.getValue(), ingreso.getValue(), EmployeeStatus.ACTIVO);
            controller.save(employee);
            UiAlerts.info("Empleados", "Empleado creado correctamente.");
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
