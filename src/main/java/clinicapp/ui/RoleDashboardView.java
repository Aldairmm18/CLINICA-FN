package clinicapp.ui;

import clinicapp.ui.controllers.RoleDashboardController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RoleDashboardView {
    private final BorderPane root;

    public RoleDashboardView(RoleDashboardController controller) {
        this.root = new BorderPane();
        this.root.setPadding(new Insets(24));

        Label title = new Label("Panel: " + controller.getRole().getDisplayName());
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button backButton = new Button("Volver");
        backButton.setOnAction(event -> controller.onBack());

        VBox topContainer = new VBox(12, title, backButton);
        topContainer.setAlignment(Pos.TOP_LEFT);

        Label placeholder = new Label("Funcionalidades pendientes");
        placeholder.setStyle("-fx-font-size: 16px;");

        StackPane centerArea = new StackPane(placeholder);
        centerArea.setAlignment(Pos.CENTER);
        centerArea.setPadding(new Insets(24));

        root.setTop(topContainer);
        root.setCenter(centerArea);
    }

    public Parent getRoot() {
        return root;
    }
}
