package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.*;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GenerateInvoiceScreen implements FunctionalityScreen {
    private final Parent root;

    public GenerateInvoiceScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Label patientLabel = new Label("Paciente seleccionado: " + appContext.getSelectedPatientContext().getSelected().map(Patient::getCedula).orElse("ninguno"));

        TableView<Appointment> appointments = new TableView<>();
        TableColumn<Appointment, String> apId = new TableColumn<>("Cita");
        apId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        TableColumn<Appointment, String> apDate = new TableColumn<>("Fecha");
        apDate.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFechaHora().toString()));
        appointments.getColumns().addAll(apId, apDate);

        Button loadAppointments = new Button("Cargar citas del paciente");
        loadAppointments.setOnAction(e -> {
            Patient p = appContext.getSelectedPatientContext().getSelected().orElse(null);
            if (p == null) { UiAlerts.error("Validación", "Seleccione paciente."); return; }
            appointments.setItems(FXCollections.observableArrayList(appContext.getAppointmentService().listarPorPaciente(p.getId())));
            patientLabel.setText("Paciente seleccionado: " + p.getCedula());
        });

        TextField itemDesc = new TextField(); itemDesc.setPromptText("Descripción item manual");
        TextField itemQty = new TextField(); itemQty.setPromptText("Cantidad");
        TextField itemPrice = new TextField(); itemPrice.setPromptText("Valor unitario");
        List<InvoiceItem> manualItems = new ArrayList<>();
        TableView<InvoiceItem> itemsTable = new TableView<>();
        TableColumn<InvoiceItem, String> dCol = new TableColumn<>("Descripción"); dCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));
        TableColumn<InvoiceItem, String> sCol = new TableColumn<>("Subtotal"); sCol.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getSubtotal())));
        itemsTable.getColumns().addAll(dCol, sCol);

        Button addManual = new Button("Agregar item manual");
        addManual.setOnAction(e -> {
            if (itemDesc.getText().isBlank() || itemQty.getText().isBlank() || itemPrice.getText().isBlank()) { UiAlerts.error("Validación", "Complete item manual."); return; }
            try {
                manualItems.add(new InvoiceItem(itemDesc.getText().trim(), Integer.parseInt(itemQty.getText().trim()), Double.parseDouble(itemPrice.getText().trim())));
                itemsTable.setItems(FXCollections.observableArrayList(manualItems));
            } catch (NumberFormatException ex) { UiAlerts.error("Validación", "Cantidad/valor inválidos."); }
        });

        TextArea printArea = new TextArea();
        Button generateFromAppointment = new Button("Generar factura desde cita seleccionada");
        generateFromAppointment.setOnAction(e -> {
            Appointment ap = appointments.getSelectionModel().getSelectedItem();
            if (ap == null) { UiAlerts.error("Validación", "Seleccione cita."); return; }
            Invoice invoice = appContext.getInvoiceService().generarDesdeAppointment("INV-" + UUID.randomUUID(), ap,
                    List.of(new InvoiceItem("Consulta médica", 1, 120000)));
            printArea.setText(buildPrintable(invoice));
            UiAlerts.info("Factura", "Factura generada.");
        });

        Button generateManual = new Button("Generar factura manual");
        generateManual.setOnAction(e -> {
            Patient p = appContext.getSelectedPatientContext().getSelected().orElse(null);
            if (p == null) { UiAlerts.error("Validación", "Seleccione paciente."); return; }
            if (manualItems.isEmpty()) { UiAlerts.error("Validación", "Agregue items manuales."); return; }
            Invoice invoice = appContext.getInvoiceService().generarManual("INV-" + UUID.randomUUID(), p, manualItems);
            printArea.setText(buildPrintable(invoice));
            UiAlerts.info("Factura", "Factura manual generada.");
        });

        Button copy = new Button("Imprimir/Copiar resumen");
        copy.setOnAction(e -> {
            if (printArea.getText().isBlank()) { UiAlerts.error("Factura", "No hay factura generada."); return; }
            UiAlerts.info("Impresión", "Resumen listo para copiar:\n" + printArea.getText());
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator,
                patientLabel, loadAppointments, appointments,
                new Label("Items manuales"), itemDesc, itemQty, itemPrice, addManual, itemsTable,
                generateFromAppointment, generateManual, printArea, copy);
    }

    private String buildPrintable(Invoice invoice) {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura ").append(invoice.getId()).append("\n");
        sb.append("Paciente: ").append(invoice.getPatient().getNombres()).append(" ").append(invoice.getPatient().getApellidos()).append("\n");
        sb.append("Estado: ").append(invoice.getStatus()).append("\n\n");
        for (InvoiceItem item : invoice.getItems()) {
            sb.append("- ").append(item.getDescripcion()).append(" x").append(item.getCantidad()).append(" = ").append(item.getSubtotal()).append("\n");
        }
        sb.append("\nTOTAL: ").append(invoice.getTotal());
        return sb.toString();
    }

    @Override public Parent getRoot() { return root; }
}
