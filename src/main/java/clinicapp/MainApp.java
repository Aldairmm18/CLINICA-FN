package clinicapp;

import clinicapp.navigation.SceneNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    private static final double WINDOW_WIDTH = 960;
    private static final double WINDOW_HEIGHT = 640;

    @Override
    public void start(Stage stage) {
        AppContext appContext = new AppContext();
        SceneNavigator navigator = new SceneNavigator(stage, WINDOW_WIDTH, WINDOW_HEIGHT, appContext);
        stage.setTitle("ClinicApp - Selección de Rol");
        navigator.showRoleSelection();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
