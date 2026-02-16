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

public class RegisterPerformedProcedureScreen implements FunctionalityScreen {
    private final Parent root;

    public RegisterPerformedProcedureScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
        Label selected = new Label("Paciente seleccionado: " + (patient == null ? "ninguno" : patient.getCedula()));

        ComboBox<NursingVisit> visitCombo = new ComboBox<>();
        if (patient != null) {
            visitCombo.setItems(FXCollections.observableArrayList(appContext.getNursingService().listarVisitasPorPaciente(patient.getId())));
        }

        OrderPickerPane<ProcedureOrder> orderPicker = new OrderPickerPane<>(patient == null ? java.util.List.of() : appContext.getOrderService().listarPorPacienteYTipo(patient.getId(), ProcedureOrder.class));
        TextArea obs = new TextArea();

        Button save = new Button("Registrar procedimiento realizado");
        save.setOnAction(e -> {
            if (patient == null || visitCombo.getValue()==null || orderPicker.getSelectedOrder()==null || orderPicker.getSelectedItem()==null) { UiAlerts.error("Validación", "Complete selección requerida."); return; }
            PerformedProcedure performed = new PerformedProcedure("PRC-" + UUID.randomUUID(), visitCombo.getValue(), orderPicker.getSelectedOrder().getOrderNumber(), orderPicker.getSelectedItem().getCatalogItemId(), obs.getText().trim());
            appContext.getNursingService().registrarProcedimiento(performed);
            UiAlerts.info("Enfermería", "Procedimiento registrado.");
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, selected, visitCombo, orderPicker.getRoot(), obs, save);
    }

    @Override public Parent getRoot() { return root; }
}
