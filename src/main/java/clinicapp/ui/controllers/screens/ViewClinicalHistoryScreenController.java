package clinicapp.ui.controllers.screens;

import clinicapp.AppContext;
import clinicapp.domain.ClinicalRecord;
import clinicapp.domain.Patient;

import java.util.List;

public class ViewClinicalHistoryScreenController {
    private final AppContext appContext;

    public ViewClinicalHistoryScreenController(AppContext appContext) {
        this.appContext = appContext;
    }

    public List<ClinicalRecord> loadByPatient(Patient patient) {
        return appContext.getClinicalRecordService().listarPorPaciente(patient.getId());
    }
}
