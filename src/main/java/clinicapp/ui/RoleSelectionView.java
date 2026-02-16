package clinicapp.ui;

import clinicapp.Role;
import clinicapp.ui.controllers.RoleSelectionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RoleSelectionView {
    private final VBox root;

    public RoleSelectionView(RoleSelectionController controller) {
        this.root = new VBox(12);
        this.root.setPadding(new Insets(24));
        this.root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Seleccionar Rol");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label subtitle = new Label("Elige un rol para acceder a su panel.");
        subtitle.setStyle("-fx-font-size: 14px;");

        VBox buttonContainer = new VBox(10);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(16, 0, 0, 0));

        for (Role role : Role.values()) {
            Button roleButton = new Button(role.getDisplayName());
            roleButton.setMinWidth(280);
            roleButton.setOnAction(event -> controller.onRoleSelected(role));
            buttonContainer.getChildren().add(roleButton);
        }

        root.getChildren().addAll(title, subtitle, buttonContainer);
    }

    public Parent getRoot() {
        return root;
    }
}
