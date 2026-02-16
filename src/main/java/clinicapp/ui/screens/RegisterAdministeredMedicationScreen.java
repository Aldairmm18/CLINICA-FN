package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.*;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.EmployeePickerPane;
import clinicapp.ui.components.OrderPickerPane;
import clinicapp.ui.components.UiAlerts;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterAdministeredMedicationScreen implements FunctionalityScreen {
    private final Parent root;

    public RegisterAdministeredMedicationScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
        Label selected = new Label("Paciente seleccionado: " + (patient == null ? "ninguno" : patient.getCedula()));

        ComboBox<NursingVisit> visitCombo = new ComboBox<>();
        if (patient != null) {
            visitCombo.setItems(FXCollections.observableArrayList(appContext.getNursingService().listarVisitasPorPaciente(patient.getId())));
        }
        visitCombo.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(NursingVisit item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getId() + " - " + item.getFechaHora());
            }
        });
        visitCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(NursingVisit item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getId() + " - " + item.getFechaHora());
            }
        });

        EmployeePickerPane nursePicker = new EmployeePickerPane(appContext.getEmployeeService(), Role.ENFERMERIA);
        Button quickVisit = new Button("Crear visita rápida ahora");
        quickVisit.setOnAction(e -> {
            if (patient == null || nursePicker.getSelected() == null) { UiAlerts.error("Validación", "Seleccione paciente y enfermera."); return; }
            NursingVisit visit = new NursingVisit("NV-" + UUID.randomUUID(), patient, nursePicker.getSelected(), LocalDateTime.now(), new VitalSigns("120/80", 36.5, 80, 97), "Visita rápida");
            appContext.getNursingService().registrarVisita(visit);
            visitCombo.setItems(FXCollections.observableArrayList(appContext.getNursingService().listarVisitasPorPaciente(patient.getId())));
            visitCombo.setValue(visit);
        });

        OrderPickerPane<MedicationOrder> orderPicker = new OrderPickerPane<>(patient == null ? java.util.List.of() : appContext.getOrderService().listarPorPacienteYTipo(patient.getId(), MedicationOrder.class));

        TextField dosis = new TextField(); dosis.setPromptText("Dosis");
        TextArea obs = new TextArea(); obs.setPromptText("Observaciones");

        Button save = new Button("Registrar medicación administrada");
        save.setOnAction(e -> {
            if (patient == null || visitCombo.getValue() == null || orderPicker.getSelectedOrder() == null || orderPicker.getSelectedItem() == null || dosis.getText().isBlank()) {
                UiAlerts.error("Validación", "Complete todos los campos requeridos."); return;
            }
            AdministeredMedication record = new AdministeredMedication("ADM-" + UUID.randomUUID(), visitCombo.getValue(),
                    orderPicker.getSelectedOrder().getOrderNumber(), orderPicker.getSelectedItem().getCatalogItemId(),
                    dosis.getText().trim(), LocalDateTime.now(), obs.getText().trim());
            appContext.getNursingService().registrarMedicacion(record);
            UiAlerts.info("Enfermería", "Medicamento administrado registrado.");
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, selected, nursePicker.getRoot(), quickVisit, visitCombo, orderPicker.getRoot(), dosis, obs, save);
    }

    @Override public Parent getRoot() { return root; }
}
