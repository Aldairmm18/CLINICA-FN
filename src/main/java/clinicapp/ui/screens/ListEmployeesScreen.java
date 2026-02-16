package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.Employee;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.controllers.screens.ListEmployeesScreenController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ListEmployeesScreen implements FunctionalityScreen {
    private final VBox root;

    public ListEmployeesScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        ListEmployeesScreenController controller = new ListEmployeesScreenController(appContext);

        Label title = new Label("Listado de empleados");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TableView<Employee> table = new TableView<>();
        TableColumn<Employee, String> idCol = new TableColumn<>("Employee ID");
        idCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmployeeId()));
        TableColumn<Employee, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombres() + " " + c.getValue().getApellidos()));
        TableColumn<Employee, String> roleCol = new TableColumn<>("Rol");
        roleCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRole().getDisplayName()));
        table.getColumns().addAll(idCol, nameCol, roleCol);
        table.setItems(FXCollections.observableArrayList(controller.list()));

        Button refresh = new Button("Refrescar");
        refresh.setOnAction(e -> table.setItems(FXCollections.observableArrayList(controller.list())));

        Button back = new Button("Volver al panel");
        back.setOnAction(e -> navigator.showRolePanel(role));

        root = new VBox(12, title, table, refresh, back);
        root.setPadding(new Insets(24));
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
