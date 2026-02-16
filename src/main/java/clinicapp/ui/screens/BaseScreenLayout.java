package clinicapp.ui.screens;

import clinicapp.Role;
import clinicapp.navigation.SceneNavigator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public final class BaseScreenLayout {
    private BaseScreenLayout() {
    }

    public static VBox create(String titleText, Role role, SceneNavigator navigator, javafx.scene.Node... content) {
        Label title = new Label(titleText);
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button back = new Button("Volver al panel");
        back.setOnAction(e -> navigator.showRolePanel(role));
        VBox root = new VBox(12);
        root.setPadding(new Insets(24));
        root.getChildren().add(title);
        root.getChildren().addAll(content);
        root.getChildren().add(back);
        return root;
    }
}
