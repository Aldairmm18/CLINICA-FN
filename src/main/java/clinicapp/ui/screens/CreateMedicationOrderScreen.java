package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.*;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.CatalogPickerPane;
import clinicapp.ui.components.EmployeePickerPane;
import clinicapp.ui.components.UiAlerts;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.time.LocalDateTime;

public class CreateMedicationOrderScreen implements FunctionalityScreen {
    private final Parent root;

    public CreateMedicationOrderScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
        Label selected = new Label("Paciente seleccionado: " + (patient == null ? "ninguno" : patient.getCedula()));
        EmployeePickerPane doctorPicker = new EmployeePickerPane(appContext.getEmployeeService(), Role.MEDICO);
        CatalogPickerPane<Medication> picker = new CatalogPickerPane<>(appContext.getInventoryService().listMedications());

        javafx.scene.control.Button save = new javafx.scene.control.Button("Crear orden de medicamentos");
        save.setOnAction(e -> {
            if (patient == null || doctorPicker.getSelected() == null || picker.getSelectedItems().isEmpty()) {
                UiAlerts.error("Validación", "Seleccione paciente, médico y al menos un item."); return;
            }
            String orderNumber = "MED-" + System.currentTimeMillis();
            MedicationOrder order = new MedicationOrder(orderNumber, patient, doctorPicker.getSelected(), LocalDateTime.now(), picker.getSelectedItems(), OrderStatus.CREADA);
            appContext.getOrderService().crear(order);
            UiAlerts.info("Órdenes", "Orden creada: " + orderNumber);
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, selected, doctorPicker.getRoot(), picker.getRoot(), save);
    }

    @Override public Parent getRoot() { return root; }
}
