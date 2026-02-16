package clinicapp.ui.controllers.screens;

import clinicapp.AppContext;
import clinicapp.domain.Employee;

public class CreateEmployeeScreenController {
    private final AppContext appContext;

    public CreateEmployeeScreenController(AppContext appContext) {
        this.appContext = appContext;
    }

    public void save(Employee employee) {
        appContext.getEmployeeService().crear(employee);
    }
}
