package clinicapp.services;

import clinicapp.domain.ClinicalRecord;

import java.util.List;

public class ClinicalRecordService {
    private final Repository<ClinicalRecord, String> repository;

    public ClinicalRecordService() {
        this.repository = new InMemoryRepository<>(ClinicalRecord::getId);
    }

    public ClinicalRecordService(Repository<ClinicalRecord, String> repository) {
        this.repository = repository;
    }

    public ClinicalRecord crear(ClinicalRecord record) { return repository.save(record); }

    public List<ClinicalRecord> listarPorPaciente(String patientId) {
        return repository.findAll().stream().filter(r -> r.getPatient().getId().equals(patientId)).toList();
    }
}
