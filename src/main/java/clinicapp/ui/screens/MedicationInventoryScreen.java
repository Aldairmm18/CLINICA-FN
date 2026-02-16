package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.Medication;
import clinicapp.domain.MedicationForm;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import clinicapp.ui.controllers.screens.MedicationInventoryScreenController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MedicationInventoryScreen implements FunctionalityScreen {
    private final VBox root;

    public MedicationInventoryScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        MedicationInventoryScreenController controller = new MedicationInventoryScreenController(appContext);

        Label title = new Label("Inventario medicamentos");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TableView<Medication> table = new TableView<>();
        TableColumn<Medication, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        TableColumn<Medication, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        TableColumn<Medication, Number> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getStock()));
        table.getColumns().addAll(idCol, nameCol, stockCol);
        table.setItems(FXCollections.observableArrayList(controller.list()));

        TextField id = new TextField();
        TextField nombre = new TextField();
        TextField descripcion = new TextField();
        TextField concentracion = new TextField();
        ComboBox<MedicationForm> forma = new ComboBox<>();
        forma.getItems().addAll(MedicationForm.values());
        TextField stock = new TextField();

        GridPane form = new GridPane();
        form.setHgap(8); form.setVgap(8);
        form.addRow(0, new Label("ID"), id);
        form.addRow(1, new Label("Nombre"), nombre);
        form.addRow(2, new Label("Descripción"), descripcion);
        form.addRow(3, new Label("Concentración"), concentracion);
        form.addRow(4, new Label("Forma"), forma);
        form.addRow(5, new Label("Stock"), stock);

        Button create = new Button("Crear medicamento");
        create.setOnAction(e -> {
            if (id.getText().isBlank() || nombre.getText().isBlank() || descripcion.getText().isBlank() || concentracion.getText().isBlank() || forma.getValue() == null || stock.getText().isBlank()) {
                UiAlerts.error("Validación", "Campos obligatorios incompletos.");
                return;
            }
            try {
                controller.save(new Medication(id.getText().trim(), nombre.getText().trim(), descripcion.getText().trim(), true,
                        concentracion.getText().trim(), forma.getValue(), Integer.parseInt(stock.getText().trim())));
                table.setItems(FXCollections.observableArrayList(controller.list()));
                UiAlerts.info("Inventario", "Medicamento creado.");
            } catch (NumberFormatException ex) {
                UiAlerts.error("Validación", "Stock debe ser numérico.");
            }
        });

        TextField delta = new TextField();
        delta.setPromptText("+/- stock");
        Button adjust = new Button("Ajustar stock seleccionado");
        adjust.setOnAction(e -> {
            Medication med = table.getSelectionModel().getSelectedItem();
            if (med == null || delta.getText().isBlank()) {
                UiAlerts.error("Validación", "Seleccione medicamento e indique delta.");
                return;
            }
            try {
                controller.adjustStock(med.getId(), Integer.parseInt(delta.getText().trim()));
                table.setItems(FXCollections.observableArrayList(controller.list()));
                UiAlerts.info("Inventario", "Stock ajustado.");
            } catch (NumberFormatException ex) {
                UiAlerts.error("Validación", "Delta debe ser numérico.");
            }
        });

        Button back = new Button("Volver al panel");
        back.setOnAction(e -> navigator.showRolePanel(role));

        root = new VBox(12, title, table, form, create, delta, adjust, back);
        root.setPadding(new Insets(24));
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
