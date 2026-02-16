package clinicapp.services;

import clinicapp.domain.DiagnosticAid;
import clinicapp.domain.Medication;
import clinicapp.domain.Procedure;

import java.util.List;
import java.util.Optional;

public class InventoryService {
    private final Repository<Medication, String> medicationRepository;
    private final Repository<Procedure, String> procedureRepository;
    private final Repository<DiagnosticAid, String> diagnosticAidRepository;

    public InventoryService() {
        this.medicationRepository = new InMemoryRepository<>(Medication::getId);
        this.procedureRepository = new InMemoryRepository<>(Procedure::getId);
        this.diagnosticAidRepository = new InMemoryRepository<>(DiagnosticAid::getId);
    }

    public Medication saveMedication(Medication medication) { return medicationRepository.save(medication); }
    public Procedure saveProcedure(Procedure procedure) { return procedureRepository.save(procedure); }
    public DiagnosticAid saveDiagnosticAid(DiagnosticAid diagnosticAid) { return diagnosticAidRepository.save(diagnosticAid); }

    public void deleteMedication(String id) { medicationRepository.deleteById(id); }
    public void deleteProcedure(String id) { procedureRepository.deleteById(id); }
    public void deleteDiagnosticAid(String id) { diagnosticAidRepository.deleteById(id); }

    public List<Medication> listMedications() { return medicationRepository.findAll(); }
    public List<Procedure> listProcedures() { return procedureRepository.findAll(); }
    public List<DiagnosticAid> listDiagnosticAids() { return diagnosticAidRepository.findAll(); }

    public Optional<Medication> findMedicationById(String id) { return medicationRepository.findById(id); }
    public Optional<Procedure> findProcedureById(String id) { return procedureRepository.findById(id); }
    public Optional<DiagnosticAid> findDiagnosticAidById(String id) { return diagnosticAidRepository.findById(id); }

    public void ajustarStock(String medicationId, int delta) {
        medicationRepository.findById(medicationId).ifPresent(m -> {
            m.adjustStock(delta);
            medicationRepository.save(m);
        });
    }
}
