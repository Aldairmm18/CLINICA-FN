package clinicapp.ui.controllers;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.navigation.SceneNavigator;

import java.util.List;

public class RolePanelController {
    private final SceneNavigator navigator;
    private final AppContext appContext;
    private final Role role;

    public RolePanelController(SceneNavigator navigator, AppContext appContext, Role role) {
        this.navigator = navigator;
        this.appContext = appContext;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public List<RoleFunctionality> getFunctionalities() {
        return RoleFunctionality.forRole(role);
    }

    public String getSelectedPatientLabel() {
        return appContext.getSelectedPatientContext().getSelected()
                .map(p -> "Paciente seleccionado: " + p.getCedula() + " - " + p.getNombres() + " " + p.getApellidos())
                .orElse("Paciente seleccionado: ninguno");
    }

    public boolean hasSelectedPatient() {
        return appContext.getSelectedPatientContext().hasSelected();
    }

    public void onClearSelectedPatient() {
        appContext.getSelectedPatientContext().clear();
    }

    public void onFunctionalitySelected(RoleFunctionality functionality) {
        navigator.showFunctionality(role, functionality);
    }

    public void onBack() {
        navigator.showRoleSelection();
    }
}
