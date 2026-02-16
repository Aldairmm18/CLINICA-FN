package clinicapp.ui.components;

import clinicapp.domain.CatalogItem;
import clinicapp.domain.OrderItem;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CatalogPickerPane<T extends CatalogItem> {
    private final ComboBox<T> catalogCombo;
    private final TextField quantityField;
    private final TextField notesField;
    private final TableView<OrderItem> selectedItemsTable;
    private final List<OrderItem> selectedItems;

    public CatalogPickerPane(List<T> catalogItems) {
        this.selectedItems = new ArrayList<>();

        catalogCombo = new ComboBox<>();
        catalogCombo.getItems().addAll(catalogItems);
        catalogCombo.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getId() + " - " + item.getNombre());
            }
        });
        catalogCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getId() + " - " + item.getNombre());
            }
        });

        quantityField = new TextField();
        quantityField.setPromptText("Cantidad");
        notesField = new TextField();
        notesField.setPromptText("Notas");

        Button addButton = new Button("Agregar item");
        addButton.setOnAction(e -> addCurrentItem());

        selectedItemsTable = new TableView<>();
        TableColumn<OrderItem, String> itemCol = new TableColumn<>("Item");
        itemCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNombre()));
        TableColumn<OrderItem, String> qtyCol = new TableColumn<>("Cant");
        qtyCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(String.valueOf(c.getValue().getCantidad())));
        selectedItemsTable.getColumns().addAll(itemCol, qtyCol);

        VBox container = new VBox(8, new Label("Catálogo"), catalogCombo, quantityField, notesField, addButton, selectedItemsTable);
        this.root = container;
    }

    private final VBox root;

    private void addCurrentItem() {
        T selected = catalogCombo.getValue();
        if (selected == null || quantityField.getText().isBlank()) {
            UiAlerts.error("Validación", "Seleccione item y cantidad.");
            return;
        }
        try {
            int qty = Integer.parseInt(quantityField.getText().trim());
            if (qty <= 0) {
                UiAlerts.error("Validación", "Cantidad debe ser mayor a cero.");
                return;
            }
            OrderItem item = new OrderItem(selected.getId(), selected.getNombre(), qty, notesField.getText().trim());
            selectedItems.add(item);
            selectedItemsTable.setItems(FXCollections.observableArrayList(selectedItems));
        } catch (NumberFormatException ex) {
            UiAlerts.error("Validación", "Cantidad inválida.");
        }
    }

    public VBox getRoot() {
        return root;
    }

    public List<OrderItem> getSelectedItems() {
        return List.copyOf(selectedItems);
    }
}
