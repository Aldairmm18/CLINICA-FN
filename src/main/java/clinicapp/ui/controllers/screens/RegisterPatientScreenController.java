package clinicapp.ui.controllers.screens;

import clinicapp.AppContext;
import clinicapp.domain.Patient;

public class RegisterPatientScreenController {
    private final AppContext appContext;

    public RegisterPatientScreenController(AppContext appContext) {
        this.appContext = appContext;
    }

    public void save(Patient patient) {
        appContext.getPatientService().registrar(patient);
    }
}
