package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.PatientSearchPane;
import clinicapp.ui.controllers.screens.SelectPatientScreenController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SelectPatientScreen implements FunctionalityScreen {
    private final VBox root;

    public SelectPatientScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        SelectPatientScreenController controller = new SelectPatientScreenController(appContext);

        Label title = new Label(functionality.getDisplayName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label selected = new Label("Paciente seleccionado: ninguno");
        appContext.getSelectedPatientContext().getSelected().ifPresent(p ->
                selected.setText("Paciente seleccionado: " + p.getCedula() + " - " + p.getNombres()));

        PatientSearchPane searchPane = new PatientSearchPane(appContext, p -> {
            controller.setSelected(p);
            selected.setText("Paciente seleccionado: " + p.getCedula() + " - " + p.getNombres());
        });

        Button back = new Button("Volver al panel");
        back.setOnAction(e -> navigator.showRolePanel(role));

        root = new VBox(12, title, selected, searchPane.getRoot(), back);
        root.setPadding(new Insets(24));
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
