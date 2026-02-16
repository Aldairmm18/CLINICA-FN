package clinicapp.ui.components;

import clinicapp.Role;
import clinicapp.domain.Employee;
import clinicapp.services.EmployeeService;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class EmployeePickerPane {
    private final ComboBox<Employee> comboBox;

    public EmployeePickerPane(EmployeeService employeeService, Role role) {
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(employeeService.buscarPorRole(role));
        comboBox.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombres() + " " + item.getApellidos());
            }
        });
        comboBox.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombres() + " " + item.getApellidos());
            }
        });
    }

    public VBox getRoot() {
        return new VBox(comboBox);
    }

    public ComboBox<Employee> getComboBox() {
        return comboBox;
    }

    public Employee getSelected() {
        return comboBox.getValue();
    }
}
