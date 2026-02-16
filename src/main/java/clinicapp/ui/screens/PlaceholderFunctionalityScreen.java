package clinicapp.ui.screens;

import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.navigation.SceneNavigator;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlaceholderFunctionalityScreen implements FunctionalityScreen {
    private final VBox root;

    public PlaceholderFunctionalityScreen(Role role, RoleFunctionality functionality, SceneNavigator navigator) {
        Label title = new Label(functionality.getDisplayName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label roleLabel = new Label("Rol: " + role.getDisplayName());
        Label pending = new Label("Pendiente paso 5");
        Button back = new Button("Volver al panel");
        back.setOnAction(e -> navigator.showRolePanel(role));

        root = new VBox(12, title, roleLabel, pending, back);
        root.setPadding(new Insets(24));
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
