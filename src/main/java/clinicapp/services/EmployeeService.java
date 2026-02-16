package clinicapp.services;

import clinicapp.Role;
import clinicapp.domain.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final Repository<Employee, String> repository;

    public EmployeeService() {
        this.repository = new InMemoryRepository<>(Employee::getId);
    }

    public EmployeeService(Repository<Employee, String> repository) {
        this.repository = repository;
    }

    public Employee crear(Employee employee) { return repository.save(employee); }
    public Employee actualizar(Employee employee) { return repository.save(employee); }
    public void eliminar(String id) { repository.deleteById(id); }
    public List<Employee> listar() { return repository.findAll(); }
    public Optional<Employee> buscarPorId(String id) { return repository.findById(id); }

    public List<Employee> buscarPorRole(Role role) {
        return repository.findAll().stream().filter(e -> e.getRole() == role).toList();
    }
}
