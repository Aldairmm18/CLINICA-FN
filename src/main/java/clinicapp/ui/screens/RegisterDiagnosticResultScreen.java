package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.*;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.EmployeePickerPane;
import clinicapp.ui.components.OrderPickerPane;
import clinicapp.ui.components.UiAlerts;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterDiagnosticResultScreen implements FunctionalityScreen {
    private final Parent root;

    public RegisterDiagnosticResultScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
        Label selected = new Label("Paciente seleccionado: " + (patient == null ? "ninguno" : patient.getCedula()));

        OrderPickerPane<DiagnosticAidOrder> orderPicker = new OrderPickerPane<>(patient == null ? java.util.List.of() : appContext.getOrderService().listarPorPacienteYTipo(patient.getId(), DiagnosticAidOrder.class));
        EmployeePickerPane doctorPicker = new EmployeePickerPane(appContext.getEmployeeService(), Role.MEDICO);
        TextArea resultado = new TextArea(); resultado.setPromptText("Resultado");
        TextArea observaciones = new TextArea(); observaciones.setPromptText("Observaciones");

        javafx.scene.control.Button save = new javafx.scene.control.Button("Registrar resultado y crear evolución");
        save.setOnAction(e -> {
            if (patient == null || orderPicker.getSelectedOrder() == null || orderPicker.getSelectedItem() == null || doctorPicker.getSelected() == null || resultado.getText().isBlank()) {
                UiAlerts.error("Validación", "Complete campos obligatorios."); return;
            }
            String motivo = "Resultado ayuda diagnóstica: " + orderPicker.getSelectedItem().getNombre();
            ClinicalRecord record = new ClinicalRecord("CR-" + UUID.randomUUID(), patient, doctorPicker.getSelected(), LocalDateTime.now(),
                    motivo, resultado.getText().trim(), "Resultado registrado", observaciones.getText().trim());
            appContext.getClinicalRecordService().crear(record);
            appContext.getOrderService().marcarEstado(orderPicker.getSelectedOrder().getOrderNumber(), OrderStatus.COMPLETADA);
            UiAlerts.info("Médico", "Resultado registrado, evolución creada y orden completada.");
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, selected, orderPicker.getRoot(), doctorPicker.getRoot(), resultado, observaciones, save);
    }

    @Override public Parent getRoot() { return root; }
}
