package clinicapp.services;

import clinicapp.domain.Appointment;

import java.util.List;
import java.util.Optional;

public class AppointmentService {
    private final Repository<Appointment, String> repository;

    public AppointmentService() {
        this.repository = new InMemoryRepository<>(Appointment::getId);
    }

    public AppointmentService(Repository<Appointment, String> repository) {
        this.repository = repository;
    }

    public Appointment programar(Appointment appointment) { return repository.save(appointment); }

    public void cancelar(String appointmentId) {
        repository.findById(appointmentId).ifPresent(Appointment::cancel);
    }

    public Optional<Appointment> buscarPorId(String id) { return repository.findById(id); }

    public List<Appointment> listarPorPaciente(String patientId) {
        return repository.findAll().stream().filter(a -> a.getPatient().getId().equals(patientId)).toList();
    }

    public List<Appointment> listarPorDoctor(String doctorId) {
        return repository.findAll().stream().filter(a -> a.getDoctor().getId().equals(doctorId)).toList();
    }
}
