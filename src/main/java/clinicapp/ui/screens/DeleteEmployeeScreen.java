package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.Employee;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DeleteEmployeeScreen implements FunctionalityScreen {
    private final Parent root;

    public DeleteEmployeeScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        TableView<Employee> table = new TableView<>();
        TableColumn<Employee, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        TableColumn<Employee, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombres() + " " + c.getValue().getApellidos()));
        table.getColumns().addAll(idCol, nameCol);
        table.setItems(FXCollections.observableArrayList(appContext.getEmployeeService().listar()));

        Button delete = new Button("Eliminar seleccionado");
        delete.setOnAction(e -> {
            Employee employee = table.getSelectionModel().getSelectedItem();
            if (employee == null) {
                UiAlerts.error("Validación", "Seleccione un empleado.");
                return;
            }
            if (UiAlerts.confirm("Confirmar", "¿Eliminar empleado?")) {
                appContext.getEmployeeService().eliminar(employee.getId());
                table.setItems(FXCollections.observableArrayList(appContext.getEmployeeService().listar()));
                UiAlerts.info("Empleados", "Empleado eliminado.");
            }
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, table, delete);
    }

    @Override public Parent getRoot() { return root; }
}
