package clinicapp.ui.controllers;

import clinicapp.Role;
import clinicapp.navigation.SceneNavigator;

public class RoleSelectionController {
    private final SceneNavigator navigator;

    public RoleSelectionController(SceneNavigator navigator) {
        this.navigator = navigator;
    }

    public void onRoleSelected(Role role) {
        navigator.showRolePanel(role);
    }
}
