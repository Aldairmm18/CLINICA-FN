package clinicapp.domain;

import java.time.LocalDateTime;
import java.util.List;

public class DiagnosticAidOrder extends MedicalOrder {
    private final boolean requiresSpecialist;
    private final SpecialistType specialistType;

    public DiagnosticAidOrder(String orderNumber, Patient patient, Employee doctor, LocalDateTime fechaHora,
                              List<OrderItem> items, OrderStatus status,
                              boolean requiresSpecialist, SpecialistType specialistType) {
        super(orderNumber, patient, doctor, fechaHora, items, status);
        this.requiresSpecialist = requiresSpecialist;
        this.specialistType = specialistType;
    }

    public boolean isRequiresSpecialist() { return requiresSpecialist; }
    public SpecialistType getSpecialistType() { return specialistType; }
}
