package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.navigation.SceneNavigator;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GeneratePrescriptionsScreen implements FunctionalityScreen {
    private final VBox root;

    public GeneratePrescriptionsScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        Label title = new Label(functionality.getDisplayName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField fieldOne = new TextField();
        fieldOne.setPromptText("Ingrese primer dato");

        TextField fieldTwo = new TextField();
        fieldTwo.setPromptText("Ingrese segundo dato");

        Button primaryAction = new Button("Generar receta");
        primaryAction.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acción ejecutada");
            alert.setHeaderText(functionality.getDisplayName());
            alert.setContentText("Operación completada correctamente.");
            alert.showAndWait();
        });

        Button back = new Button("Volver al panel");
        back.setOnAction(event -> navigator.showDashboard(role, appContext));

        root = new VBox(12, title, fieldOne, fieldTwo, primaryAction, back);
        root.setPadding(new Insets(24));
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
