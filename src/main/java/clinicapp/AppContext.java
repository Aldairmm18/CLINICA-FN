package clinicapp;

import clinicapp.services.*;

public class AppContext {
    private final PatientService patientService;
    private final EmployeeService employeeService;
    private final AppointmentService appointmentService;
    private final ClinicalRecordService clinicalRecordService;
    private final OrderService orderService;
    private final InventoryService inventoryService;
    private final InvoiceService invoiceService;
    private final NursingService nursingService;
    private final SelectedPatientContext selectedPatientContext;

    public AppContext() {
        this.patientService = new PatientService();
        this.employeeService = new EmployeeService();
        this.appointmentService = new AppointmentService();
        this.clinicalRecordService = new ClinicalRecordService();
        this.orderService = new OrderService();
        this.inventoryService = new InventoryService();
        this.invoiceService = new InvoiceService();
        this.nursingService = new NursingService();
        this.selectedPatientContext = new SelectedPatientContext();

        DemoDataSeeder seeder = new DemoDataSeeder(employeeService, patientService, inventoryService);
        seeder.seed();
    }

    public PatientService getPatientService() { return patientService; }
    public EmployeeService getEmployeeService() { return employeeService; }
    public AppointmentService getAppointmentService() { return appointmentService; }
    public ClinicalRecordService getClinicalRecordService() { return clinicalRecordService; }
    public OrderService getOrderService() { return orderService; }
    public InventoryService getInventoryService() { return inventoryService; }
    public InvoiceService getInvoiceService() { return invoiceService; }
    public NursingService getNursingService() { return nursingService; }
    public SelectedPatientContext getSelectedPatientContext() { return selectedPatientContext; }
}
