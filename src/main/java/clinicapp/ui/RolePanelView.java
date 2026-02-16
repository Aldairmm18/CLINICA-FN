package clinicapp.ui;

import clinicapp.RoleFunctionality;
import clinicapp.ui.controllers.RolePanelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RolePanelView {
    private final BorderPane root;

    public RolePanelView(RolePanelController controller) {
        this.root = new BorderPane();
        this.root.setPadding(new Insets(24));

        Label title = new Label("Panel: " + controller.getRole().getDisplayName());
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button backButton = new Button("Volver a selección de rol");
        backButton.setOnAction(event -> controller.onBack());

        Label selectedPatientLabel = new Label(controller.getSelectedPatientLabel());
        Button clearButton = new Button("Limpiar selección");
        clearButton.setDisable(!controller.hasSelectedPatient());
        clearButton.setOnAction(event -> {
            controller.onClearSelectedPatient();
            selectedPatientLabel.setText(controller.getSelectedPatientLabel());
            clearButton.setDisable(!controller.hasSelectedPatient());
        });

        HBox selectionRow = new HBox(10, selectedPatientLabel, clearButton);
        selectionRow.setAlignment(Pos.CENTER_LEFT);

        VBox topContainer = new VBox(12, title, backButton, selectionRow);
        topContainer.setAlignment(Pos.TOP_LEFT);

        VBox functionalityList = new VBox(10);
        functionalityList.setAlignment(Pos.TOP_LEFT);
        functionalityList.setPadding(new Insets(24, 0, 0, 0));

        for (RoleFunctionality functionality : controller.getFunctionalities()) {
            Button functionalityButton = new Button(functionality.getDisplayName());
            functionalityButton.setMinWidth(360);
            functionalityButton.setOnAction(event -> controller.onFunctionalitySelected(functionality));
            functionalityList.getChildren().add(functionalityButton);
        }

        root.setTop(topContainer);
        root.setCenter(functionalityList);
    }

    public Parent getRoot() {
        return root;
    }
}
