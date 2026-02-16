package clinicapp.ui.controllers.screens;

import clinicapp.AppContext;
import clinicapp.domain.Patient;

public class SelectPatientScreenController {
    private final AppContext appContext;

    public SelectPatientScreenController(AppContext appContext) {
        this.appContext = appContext;
    }

    public void setSelected(Patient patient) {
        appContext.getSelectedPatientContext().setSelected(patient);
    }
}
