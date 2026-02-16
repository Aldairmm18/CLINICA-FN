package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.*;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.OrderPickerPane;
import clinicapp.ui.components.UiAlerts;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.util.UUID;

public class RegisterPerformedDiagnosticTestScreen implements FunctionalityScreen {
    private final Parent root;

    public RegisterPerformedDiagnosticTestScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
        Label selected = new Label("Paciente seleccionado: " + (patient == null ? "ninguno" : patient.getCedula()));

        ComboBox<NursingVisit> visitCombo = new ComboBox<>();
        if (patient != null) {
            visitCombo.setItems(FXCollections.observableArrayList(appContext.getNursingService().listarVisitasPorPaciente(patient.getId())));
        }

        OrderPickerPane<DiagnosticAidOrder> orderPicker = new OrderPickerPane<>(patient == null ? java.util.List.of() : appContext.getOrderService().listarPorPacienteYTipo(patient.getId(), DiagnosticAidOrder.class));
        TextArea resultado = new TextArea(); resultado.setPromptText("Resultado");
        TextArea obs = new TextArea(); obs.setPromptText("Observaciones");

        Button save = new Button("Registrar prueba/observación");
        save.setOnAction(e -> {
            if (patient == null || visitCombo.getValue()==null || orderPicker.getSelectedOrder()==null || orderPicker.getSelectedItem()==null || resultado.getText().isBlank()) { UiAlerts.error("Validación", "Complete los campos obligatorios."); return; }
            PerformedDiagnosticTest test = new PerformedDiagnosticTest("DTS-" + UUID.randomUUID(), visitCombo.getValue(), orderPicker.getSelectedOrder().getOrderNumber(), orderPicker.getSelectedItem().getCatalogItemId(), resultado.getText().trim(), obs.getText().trim());
            appContext.getNursingService().registrarPrueba(test);
            UiAlerts.info("Enfermería", "Prueba registrada.");
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, selected, visitCombo, orderPicker.getRoot(), resultado, obs, save);
    }

    @Override public Parent getRoot() { return root; }
}
