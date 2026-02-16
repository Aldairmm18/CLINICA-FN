package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.Employee;
import clinicapp.domain.EmployeeStatus;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class UpdateEmployeeScreen implements FunctionalityScreen {
    private final Parent root;

    public UpdateEmployeeScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        ComboBox<Employee> employeeCombo = new ComboBox<>(FXCollections.observableArrayList(appContext.getEmployeeService().listar()));
        employeeCombo.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getEmployeeId() + " - " + item.getNombres());
            }
        });
        employeeCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getEmployeeId() + " - " + item.getNombres());
            }
        });

        TextField nombres = new TextField();
        TextField apellidos = new TextField();
        TextField telefono = new TextField();
        TextField email = new TextField();
        TextField direccion = new TextField();
        ComboBox<EmployeeStatus> status = new ComboBox<>(FXCollections.observableArrayList(EmployeeStatus.values()));

        employeeCombo.setOnAction(e -> {
            Employee emp = employeeCombo.getValue();
            if (emp != null) {
                nombres.setText(emp.getNombres());
                apellidos.setText(emp.getApellidos());
                telefono.setText(emp.getTelefono());
                email.setText(emp.getEmail());
                direccion.setText(emp.getDireccion());
                status.setValue(emp.getStatus());
            }
        });

        GridPane form = new GridPane();
        form.setHgap(8);form.setVgap(8);
        form.addRow(0, new Label("Empleado"), employeeCombo);
        form.addRow(1, new Label("Nombres"), nombres);
        form.addRow(2, new Label("Apellidos"), apellidos);
        form.addRow(3, new Label("Teléfono"), telefono);
        form.addRow(4, new Label("Email"), email);
        form.addRow(5, new Label("Dirección"), direccion);
        form.addRow(6, new Label("Status"), status);

        Button save = new Button("Guardar cambios");
        save.setOnAction(e -> {
            Employee emp = employeeCombo.getValue();
            if (emp == null || nombres.getText().isBlank() || apellidos.getText().isBlank() || telefono.getText().isBlank() || email.getText().isBlank() || direccion.getText().isBlank() || status.getValue() == null) {
                UiAlerts.error("Validación", "Complete todos los campos.");
                return;
            }
            emp.actualizarNombreCompleto(nombres.getText().trim(), apellidos.getText().trim());
            emp.actualizarContacto(telefono.getText().trim(), email.getText().trim(), direccion.getText().trim());
            emp.updateJobData(emp.getRole(), status.getValue());
            appContext.getEmployeeService().actualizar(emp);
            UiAlerts.info("Empleados", "Empleado actualizado.");
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, form, save);
    }

    @Override public Parent getRoot() { return root; }
}
