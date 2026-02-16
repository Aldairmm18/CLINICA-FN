package clinicapp.services;

import clinicapp.Role;
import clinicapp.domain.*;

import java.time.LocalDate;

public class DemoDataSeeder {
    private final EmployeeService employeeService;
    private final PatientService patientService;
    private final InventoryService inventoryService;

    public DemoDataSeeder(EmployeeService employeeService, PatientService patientService, InventoryService inventoryService) {
        this.employeeService = employeeService;
        this.patientService = patientService;
        this.inventoryService = inventoryService;
    }

    public void seed() {
        employeeService.crear(new Employee("EMP-1", "Laura", "Mendez", "3001112233", "laura@clinicapp.com", "Cra 10 #10-10",
                "E-001", Role.MEDICO, LocalDate.now().minusYears(3), EmployeeStatus.ACTIVO));
        employeeService.crear(new Employee("EMP-2", "Paula", "Ruiz", "3004445566", "paula@clinicapp.com", "Cra 20 #20-20",
                "E-002", Role.ENFERMERIA, LocalDate.now().minusYears(2), EmployeeStatus.ACTIVO));

        patientService.registrar(new Patient("PAT-1", "Carlos", "Gomez", "3112223344", "carlos@mail.com", "Calle 1",
                "10001", LocalDate.of(1990, 5, 12), Sex.MASCULINO,
                new EmergencyContact("Ana Gomez", "3115556677", RelationshipType.PAREJA),
                new InsurancePolicy("EPS Salud", "POL-100", InsuranceType.EPS, true,
                        LocalDate.now().minusYears(1), LocalDate.now().plusYears(1), 80)));

        patientService.registrar(new Patient("PAT-2", "Maria", "Lopez", "3121110099", "maria@mail.com", "Calle 2",
                "10002", LocalDate.of(1985, 10, 8), Sex.FEMENINO,
                new EmergencyContact("Jose Lopez", "3125550001", RelationshipType.HERMANO),
                new InsurancePolicy("Prepagada Plus", "POL-200", InsuranceType.PREPAGADA, true,
                        LocalDate.now().minusMonths(6), LocalDate.now().plusYears(2), 90)));

        inventoryService.saveMedication(new Medication("MED-1", "Acetaminofén", "Analgésico", true,
                "500mg", MedicationForm.TABLETA, 200));
        inventoryService.saveMedication(new Medication("MED-2", "Amoxicilina", "Antibiótico", true,
                "250mg", MedicationForm.CAPSULA, 120));
        inventoryService.saveProcedure(new Procedure("PROC-1", "Curación", "Curación básica", true,
                ProcedureCategory.TERAPIA));
        inventoryService.saveDiagnosticAid(new DiagnosticAid("DIA-1", "Radiografía de tórax", "Imagen diagnóstica", true,
                DiagnosticAidCategory.IMAGEN));
    }
}
