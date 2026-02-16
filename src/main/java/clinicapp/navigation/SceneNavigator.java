package clinicapp.navigation;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.ui.RolePanelView;
import clinicapp.ui.RoleSelectionView;
import clinicapp.ui.controllers.RolePanelController;
import clinicapp.ui.controllers.RoleSelectionController;
import clinicapp.ui.screens.FunctionalityScreen;
import clinicapp.ui.screens.FunctionalityScreenFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneNavigator {
    private final Stage primaryStage;
    private final double windowWidth;
    private final double windowHeight;
    private final AppContext appContext;
    private final FunctionalityScreenFactory screenFactory;

    public SceneNavigator(Stage primaryStage, double windowWidth, double windowHeight, AppContext appContext) {
        this.primaryStage = primaryStage;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.appContext = appContext;
        this.screenFactory = new FunctionalityScreenFactory(this);
    }

    public void showRoleSelection() {
        RoleSelectionController controller = new RoleSelectionController(this);
        RoleSelectionView view = new RoleSelectionView(controller);
        setScene(view.getRoot(), "ClinicApp - Seleccionar Rol");
    }

    public void showRolePanel(Role role) {
        RolePanelController controller = new RolePanelController(this, appContext, role);
        RolePanelView view = new RolePanelView(controller);
        setScene(view.getRoot(), "ClinicApp - Panel de Rol");
    }

    public void showFunctionality(Role role, RoleFunctionality functionality) {
        FunctionalityScreen screen = screenFactory.create(functionality, null, appContext);
        BorderPane wrapper = new BorderPane(screen.getRoot());
        setScene(wrapper, "ClinicApp - Funcionalidad");
    }

    private void setScene(javafx.scene.Parent root, String title) {
        primaryStage.setScene(new Scene(root, windowWidth, windowHeight));
        primaryStage.setTitle(title);
    }
}
