package clinicapp.ui.controllers;

import clinicapp.MainApp;
import clinicapp.Role;

public class RoleDashboardController {
    private final MainApp mainApp;
    private final Role role;

    public RoleDashboardController(MainApp mainApp, Role role) {
        this.mainApp = mainApp;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void onBack() {
        mainApp.showRoleSelection();
    }
}
