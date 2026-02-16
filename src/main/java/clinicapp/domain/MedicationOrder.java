package clinicapp.domain;

import java.time.LocalDateTime;
import java.util.List;

public class MedicationOrder extends MedicalOrder {
    public MedicationOrder(String orderNumber, Patient patient, Employee doctor, LocalDateTime fechaHora,
                           List<OrderItem> items, OrderStatus status) {
        super(orderNumber, patient, doctor, fechaHora, items, status);
    }
}
