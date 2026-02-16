package clinicapp.ui.controllers.screens;

import clinicapp.AppContext;
import clinicapp.domain.ClinicalRecord;

public class CreateClinicalRecordScreenController {
    private final AppContext appContext;

    public CreateClinicalRecordScreenController(AppContext appContext) {
        this.appContext = appContext;
    }

    public void save(ClinicalRecord record) {
        appContext.getClinicalRecordService().crear(record);
    }
}
