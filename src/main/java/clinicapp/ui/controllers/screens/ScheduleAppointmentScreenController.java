package clinicapp.ui.controllers.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.domain.Appointment;
import clinicapp.domain.Employee;

import java.util.List;

public class ScheduleAppointmentScreenController {
    private final AppContext appContext;

    public ScheduleAppointmentScreenController(AppContext appContext) {
        this.appContext = appContext;
    }

    public List<Employee> getDoctors() {
        return appContext.getEmployeeService().buscarPorRole(Role.MEDICO);
    }

    public void save(Appointment appointment) {
        appContext.getAppointmentService().programar(appointment);
    }
}
