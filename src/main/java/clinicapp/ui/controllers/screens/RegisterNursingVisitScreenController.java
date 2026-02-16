package clinicapp.ui.controllers.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.domain.Employee;
import clinicapp.domain.NursingVisit;

import java.util.List;

public class RegisterNursingVisitScreenController {
    private final AppContext appContext;

    public RegisterNursingVisitScreenController(AppContext appContext) {
        this.appContext = appContext;
    }

    public List<Employee> getNurses() {
        return appContext.getEmployeeService().buscarPorRole(Role.ENFERMERIA);
    }

    public void saveVisit(NursingVisit visit) {
        appContext.getNursingService().registrarVisita(visit);
    }
}
