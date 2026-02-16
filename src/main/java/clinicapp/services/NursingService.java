package clinicapp.services;

import clinicapp.domain.AdministeredMedication;
import clinicapp.domain.NursingVisit;
import clinicapp.domain.PerformedDiagnosticTest;
import clinicapp.domain.PerformedProcedure;

import java.util.ArrayList;
import java.util.List;

public class NursingService {
    private final Repository<NursingVisit, String> visitRepository;
    private final Repository<AdministeredMedication, String> administeredMedicationRepository;
    private final Repository<PerformedProcedure, String> performedProcedureRepository;
    private final Repository<PerformedDiagnosticTest, String> performedDiagnosticTestRepository;

    public NursingService() {
        this.visitRepository = new InMemoryRepository<>(NursingVisit::getId);
        this.administeredMedicationRepository = new InMemoryRepository<>(AdministeredMedication::getId);
        this.performedProcedureRepository = new InMemoryRepository<>(PerformedProcedure::getId);
        this.performedDiagnosticTestRepository = new InMemoryRepository<>(PerformedDiagnosticTest::getId);
    }

    public NursingVisit registrarVisita(NursingVisit visit) { return visitRepository.save(visit); }
    public AdministeredMedication registrarMedicacion(AdministeredMedication medication) { return administeredMedicationRepository.save(medication); }
    public PerformedProcedure registrarProcedimiento(PerformedProcedure procedure) { return performedProcedureRepository.save(procedure); }
    public PerformedDiagnosticTest registrarPrueba(PerformedDiagnosticTest test) { return performedDiagnosticTestRepository.save(test); }

    public List<NursingVisit> listarVisitas() { return visitRepository.findAll(); }

    public List<AdministeredMedication> listarMedicacionesPorVisita(String visitId) {
        return filterByVisit(administeredMedicationRepository.findAll(), visitId);
    }

    public List<PerformedProcedure> listarProcedimientosPorVisita(String visitId) {
        return filterByVisit(performedProcedureRepository.findAll(), visitId);
    }

    public List<PerformedDiagnosticTest> listarPruebasPorVisita(String visitId) {
        return filterByVisit(performedDiagnosticTestRepository.findAll(), visitId);
    }

    private <T> List<T> filterByVisit(List<T> source, String visitId) {
        List<T> result = new ArrayList<>();
        for (T item : source) {
            String itemVisitId;
            if (item instanceof AdministeredMedication medication) {
                itemVisitId = medication.getVisit().getId();
            } else if (item instanceof PerformedProcedure procedure) {
                itemVisitId = procedure.getVisit().getId();
            } else if (item instanceof PerformedDiagnosticTest test) {
                itemVisitId = test.getVisit().getId();
            } else {
                continue;
            }
            if (itemVisitId.equals(visitId)) {
                result.add(item);
            }
        }
        return result;
    }
}
