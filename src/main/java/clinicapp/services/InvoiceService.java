package clinicapp.services;

import clinicapp.domain.Appointment;
import clinicapp.domain.Invoice;
import clinicapp.domain.InvoiceItem;
import clinicapp.domain.InvoiceStatus;
import clinicapp.domain.Patient;

import java.time.LocalDate;
import java.util.List;

public class InvoiceService {
    private final Repository<Invoice, String> repository;

    public InvoiceService() {
        this.repository = new InMemoryRepository<>(Invoice::getId);
    }

    public InvoiceService(Repository<Invoice, String> repository) {
        this.repository = repository;
    }

    public Invoice generarDesdeAppointment(String id, Appointment appointment, List<InvoiceItem> items) {
        Invoice invoice = new Invoice(id, appointment.getPatient(), appointment, LocalDate.now(), items, InvoiceStatus.GENERADA);
        return repository.save(invoice);
    }

    public Invoice generarManual(String id, Patient patient, List<InvoiceItem> items) {
        Invoice invoice = new Invoice(id, patient, null, LocalDate.now(), items, InvoiceStatus.GENERADA);
        return repository.save(invoice);
    }

    public List<Invoice> listar() {
        return repository.findAll();
    }
}
