package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.SupportTicket;
import clinicapp.navigation.SceneNavigator;
import clinicapp.ui.components.UiAlerts;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class SupportTicketScreen implements FunctionalityScreen {
    private final Parent root;

    public SupportTicketScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        TextField usuario = new TextField(); usuario.setPromptText("Usuario");
        TextField asunto = new TextField(); asunto.setPromptText("Asunto");
        TextArea descripcion = new TextArea(); descripcion.setPromptText("Descripción");

        TableView<SupportTicket> table = new TableView<>();
        TableColumn<SupportTicket, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        TableColumn<SupportTicket, String> userCol = new TableColumn<>("Usuario");
        userCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUsuario()));
        TableColumn<SupportTicket, String> issueCol = new TableColumn<>("Asunto");
        issueCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAsunto()));
        table.getColumns().addAll(idCol, userCol, issueCol);
        table.setItems(FXCollections.observableArrayList(appContext.getSupportTicketService().listar()));

        Button create = new Button("Crear ticket");
        create.setOnAction(e -> {
            if (usuario.getText().isBlank() || asunto.getText().isBlank() || descripcion.getText().isBlank()) {
                UiAlerts.error("Validación", "Todos los campos son obligatorios."); return;
            }
            appContext.getSupportTicketService().crear(new SupportTicket("TCK-" + UUID.randomUUID(), usuario.getText().trim(), asunto.getText().trim(), descripcion.getText().trim(), LocalDateTime.now()));
            table.setItems(FXCollections.observableArrayList(appContext.getSupportTicketService().listar()));
            UiAlerts.info("Soporte", "Ticket creado.");
        });

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, usuario, asunto, descripcion, create, table);
    }

    @Override public Parent getRoot() { return root; }
}
