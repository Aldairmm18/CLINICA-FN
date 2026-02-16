package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.DiagnosticAid;
import clinicapp.domain.DiagnosticAidCategory;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;

public class DiagnosticAidInventoryScreen implements FunctionalityScreen {
    private final Parent root;

    public DiagnosticAidInventoryScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        TableView<DiagnosticAid> table = new TableView<>();
        TableColumn<DiagnosticAid, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        TableColumn<DiagnosticAid, String> nCol = new TableColumn<>("Nombre");
        nCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        table.getColumns().addAll(idCol, nCol);
        table.setItems(FXCollections.observableArrayList(appContext.getInventoryService().listDiagnosticAids()));

        TextField id = new TextField(); TextField nombre = new TextField(); TextField desc = new TextField();
        ComboBox<DiagnosticAidCategory> cat = new ComboBox<>(FXCollections.observableArrayList(DiagnosticAidCategory.values()));

        Button create = new Button("Crear ayuda diagnóstica");
        create.setOnAction(e -> {
            if (id.getText().isBlank() || nombre.getText().isBlank() || desc.getText().isBlank() || cat.getValue()==null) { UiAlerts.error("Validación", "Campos obligatorios."); return; }
            appContext.getInventoryService().saveDiagnosticAid(new DiagnosticAid(id.getText().trim(), nombre.getText().trim(), desc.getText().trim(), true, cat.getValue()));
            table.setItems(FXCollections.observableArrayList(appContext.getInventoryService().listDiagnosticAids()));
            UiAlerts.info("Inventario", "Ayuda diagnóstica creada.");
        });

        Button delete = new Button("Eliminar seleccionado");
        delete.setOnAction(e -> {
            DiagnosticAid d = table.getSelectionModel().getSelectedItem();
            if (d == null) { UiAlerts.error("Validación", "Seleccione una ayuda."); return; }
            appContext.getInventoryService().deleteDiagnosticAid(d.getId());
            table.setItems(FXCollections.observableArrayList(appContext.getInventoryService().listDiagnosticAids()));
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, table, id, nombre, desc, cat, create, delete);
    }

    @Override public Parent getRoot() { return root; }
}
