package clinicapp.services;

import clinicapp.Role;
import clinicapp.domain.Employee;

import java.util.List;

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

    public List<Employee> buscarPorRole(Role role) {
        return repository.findAll().stream().filter(e -> e.getRole() == role).toList();
    }
}
