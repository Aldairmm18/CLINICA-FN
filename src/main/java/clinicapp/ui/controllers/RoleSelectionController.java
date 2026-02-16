package clinicapp.ui.controllers;

import clinicapp.MainApp;
import clinicapp.Role;

public class RoleSelectionController {
    private final MainApp mainApp;

    public RoleSelectionController(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void onRoleSelected(Role role) {
        mainApp.showRoleDashboard(role);
    }
}
