package clinicapp.ui.controllers.screens;

import clinicapp.AppContext;
import clinicapp.domain.Medication;

import java.util.List;

public class MedicationInventoryScreenController {
    private final AppContext appContext;

    public MedicationInventoryScreenController(AppContext appContext) {
        this.appContext = appContext;
    }

    public List<Medication> list() { return appContext.getInventoryService().listMedications(); }
    public void save(Medication medication) { appContext.getInventoryService().saveMedication(medication); }
    public void adjustStock(String id, int delta) { appContext.getInventoryService().ajustarStock(id, delta); }
}
