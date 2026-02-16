package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.*;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.CatalogPickerPane;
import clinicapp.ui.components.EmployeePickerPane;
import clinicapp.ui.components.UiAlerts;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.time.LocalDateTime;

public class CreateDiagnosticAidOrderScreen implements FunctionalityScreen {
    private final Parent root;

    public CreateDiagnosticAidOrderScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
        Label selected = new Label("Paciente seleccionado: " + (patient == null ? "ninguno" : patient.getCedula()));
        EmployeePickerPane doctorPicker = new EmployeePickerPane(appContext.getEmployeeService(), Role.MEDICO);
        CatalogPickerPane<DiagnosticAid> picker = new CatalogPickerPane<>(appContext.getInventoryService().listDiagnosticAids());
        CheckBox requires = new CheckBox("Requiere especialista");
        ComboBox<SpecialistType> specialist = new ComboBox<>(FXCollections.observableArrayList(SpecialistType.values()));

        javafx.scene.control.Button save = new javafx.scene.control.Button("Crear orden de ayuda diagnóstica");
        save.setOnAction(e -> {
            if (patient == null || doctorPicker.getSelected() == null || picker.getSelectedItems().isEmpty()) { UiAlerts.error("Validación", "Datos incompletos."); return; }
            if (requires.isSelected() && specialist.getValue() == null) { UiAlerts.error("Validación", "Seleccione especialista."); return; }
            String orderNumber = "DIA-" + System.currentTimeMillis();
            DiagnosticAidOrder order = new DiagnosticAidOrder(orderNumber, patient, doctorPicker.getSelected(), LocalDateTime.now(), picker.getSelectedItems(), OrderStatus.CREADA, requires.isSelected(), specialist.getValue());
            appContext.getOrderService().crear(order);
            UiAlerts.info("Órdenes", "Orden creada: " + orderNumber);
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, selected, doctorPicker.getRoot(), picker.getRoot(), requires, specialist, save);
    }

    @Override public Parent getRoot() { return root; }
}
