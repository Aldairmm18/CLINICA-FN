package clinicapp;

import clinicapp.ui.RoleDashboardView;
import clinicapp.ui.RoleSelectionView;
import clinicapp.ui.controllers.RoleDashboardController;
import clinicapp.ui.controllers.RoleSelectionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private static final double WINDOW_WIDTH = 720;
    private static final double WINDOW_HEIGHT = 480;

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("ClinicApp - Selección de Rol");
        showRoleSelection();
        this.primaryStage.show();
    }

    public void showRoleSelection() {
        RoleSelectionController controller = new RoleSelectionController(this);
        RoleSelectionView view = new RoleSelectionView(controller);
        Scene scene = new Scene(view.getRoot(), WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ClinicApp - Seleccionar Rol");
    }

    public void showRoleDashboard(Role role) {
        RoleDashboardController controller = new RoleDashboardController(this, role);
        RoleDashboardView view = new RoleDashboardView(controller);
        Scene scene = new Scene(view.getRoot(), WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ClinicApp - Panel");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
