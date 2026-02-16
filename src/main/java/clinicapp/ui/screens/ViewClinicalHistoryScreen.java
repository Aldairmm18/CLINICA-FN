package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.ClinicalRecord;
import clinicapp.domain.Patient;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import clinicapp.ui.controllers.screens.ViewClinicalHistoryScreenController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ViewClinicalHistoryScreen implements FunctionalityScreen {
    private final VBox root;

    public ViewClinicalHistoryScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        ViewClinicalHistoryScreenController controller = new ViewClinicalHistoryScreenController(appContext);
        Label title = new Label(functionality.getDisplayName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TableView<ClinicalRecord> table = new TableView<>();
        TableColumn<ClinicalRecord, String> fechaCol = new TableColumn<>("Fecha");
        fechaCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFechaHora().toString()));
        TableColumn<ClinicalRecord, String> motivoCol = new TableColumn<>("Motivo");
        motivoCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMotivo()));
        TableColumn<ClinicalRecord, String> diagCol = new TableColumn<>("Diagnóstico");
        diagCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDiagnostico()));
        table.getColumns().addAll(fechaCol, motivoCol, diagCol);

        Button load = new Button("Cargar historia del paciente seleccionado");
        load.setOnAction(e -> {
            Patient patient = appContext.getSelectedPatientContext().getSelected().orElse(null);
            if (patient == null) {
                UiAlerts.error("Validación", "Debe seleccionar un paciente.");
                return;
            }
            table.setItems(FXCollections.observableArrayList(controller.loadByPatient(patient)));
        });

        Button back = new Button("Volver al panel");
        back.setOnAction(e -> navigator.showRolePanel(role));

        root = new VBox(12, title, load, table, back);
        root.setPadding(new Insets(24));
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
