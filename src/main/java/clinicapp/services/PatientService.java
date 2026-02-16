package clinicapp.services;

import clinicapp.domain.Patient;

import java.util.List;
import java.util.Optional;

public class PatientService {
    private final Repository<Patient, String> repository;

    public PatientService() {
        this.repository = new InMemoryRepository<>(Patient::getId);
    }

    public PatientService(Repository<Patient, String> repository) {
        this.repository = repository;
    }

    public Patient registrar(Patient patient) { return repository.save(patient); }
    public Patient actualizar(Patient patient) { return repository.save(patient); }
    public List<Patient> listar() { return repository.findAll(); }
    public Optional<Patient> buscarPorId(String id) { return repository.findById(id); }

    public Optional<Patient> buscarPorCedula(String cedula) {
        return repository.findAll().stream().filter(p -> p.getCedula().equals(cedula)).findFirst();
    }
}
