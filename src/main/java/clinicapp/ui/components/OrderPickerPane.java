package clinicapp.ui.components;

import clinicapp.domain.MedicalOrder;
import clinicapp.domain.OrderItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.util.List;

public class OrderPickerPane<T extends MedicalOrder> {
    private final ComboBox<T> orderCombo;
    private final ComboBox<OrderItem> itemCombo;

    public OrderPickerPane(List<T> orders) {
        orderCombo = new ComboBox<>();
        orderCombo.getItems().addAll(orders);
        orderCombo.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getOrderNumber() + " (" + item.getStatus() + ")");
            }
        });
        orderCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getOrderNumber() + " (" + item.getStatus() + ")");
            }
        });

        itemCombo = new ComboBox<>();
        itemCombo.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(OrderItem item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre() + " x" + item.getCantidad());
            }
        });
        itemCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(OrderItem item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre() + " x" + item.getCantidad());
            }
        });

        orderCombo.setOnAction(e -> {
            T selected = orderCombo.getValue();
            itemCombo.getItems().clear();
            if (selected != null) {
                itemCombo.getItems().addAll(selected.getItems());
            }
        });
    }

    public VBox getRoot() {
        return new VBox(8, orderCombo, itemCombo);
    }

    public T getSelectedOrder() { return orderCombo.getValue(); }
    public OrderItem getSelectedItem() { return itemCombo.getValue(); }
}
