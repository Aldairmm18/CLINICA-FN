package clinicapp.ui.controllers.screens;

import clinicapp.AppContext;
import clinicapp.domain.Employee;

import java.util.List;

public class ListEmployeesScreenController {
    private final AppContext appContext;

    public ListEmployeesScreenController(AppContext appContext) {
        this.appContext = appContext;
    }

    public List<Employee> list() {
        return appContext.getEmployeeService().listar();
    }
}
