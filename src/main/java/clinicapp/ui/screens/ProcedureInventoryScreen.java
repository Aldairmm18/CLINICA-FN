package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.Procedure;
import clinicapp.domain.ProcedureCategory;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;

public class ProcedureInventoryScreen implements FunctionalityScreen {
    private final Parent root;

    public ProcedureInventoryScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        TableView<Procedure> table = new TableView<>();
        TableColumn<Procedure, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        TableColumn<Procedure, String> nCol = new TableColumn<>("Nombre");
        nCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        table.getColumns().addAll(idCol, nCol);
        table.setItems(FXCollections.observableArrayList(appContext.getInventoryService().listProcedures()));

        TextField id = new TextField(); TextField nombre = new TextField(); TextField desc = new TextField();
        ComboBox<ProcedureCategory> cat = new ComboBox<>(FXCollections.observableArrayList(ProcedureCategory.values()));

        Button create = new Button("Crear procedimiento");
        create.setOnAction(e -> {
            if (id.getText().isBlank() || nombre.getText().isBlank() || desc.getText().isBlank() || cat.getValue()==null) { UiAlerts.error("Validación", "Campos obligatorios."); return; }
            appContext.getInventoryService().saveProcedure(new Procedure(id.getText().trim(), nombre.getText().trim(), desc.getText().trim(), true, cat.getValue()));
            table.setItems(FXCollections.observableArrayList(appContext.getInventoryService().listProcedures()));
            UiAlerts.info("Inventario", "Procedimiento creado.");
        });

        Button delete = new Button("Eliminar seleccionado");
        delete.setOnAction(e -> {
            Procedure p = table.getSelectionModel().getSelectedItem();
            if (p == null) { UiAlerts.error("Validación", "Seleccione un procedimiento."); return; }
            appContext.getInventoryService().deleteProcedure(p.getId());
            table.setItems(FXCollections.observableArrayList(appContext.getInventoryService().listProcedures()));
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, table, id, nombre, desc, cat, create, delete);
    }

    @Override public Parent getRoot() { return root; }
}
