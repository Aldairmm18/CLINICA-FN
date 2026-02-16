package clinicapp.services;

import clinicapp.domain.MedicalOrder;

import java.util.List;
import java.util.Optional;

public class OrderService {
    private final Repository<MedicalOrder, String> repository;

    public OrderService() {
        this.repository = new InMemoryRepository<>(MedicalOrder::getOrderNumber);
    }

    public OrderService(Repository<MedicalOrder, String> repository) {
        this.repository = repository;
    }

    public MedicalOrder crear(MedicalOrder order) { return repository.save(order); }

    public Optional<MedicalOrder> buscarPorOrderNumber(String orderNumber) {
        return repository.findById(orderNumber);
    }

    public List<MedicalOrder> listarPorPaciente(String patientId) {
        return repository.findAll().stream().filter(o -> o.getPatient().getId().equals(patientId)).toList();
    }

    public <T extends MedicalOrder> List<T> listarPorTipo(Class<T> orderType) {
        return repository.findAll().stream().filter(orderType::isInstance).map(orderType::cast).toList();
    }
}
