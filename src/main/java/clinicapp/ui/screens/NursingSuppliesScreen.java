package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.domain.NursingVisit;
import clinicapp.navigation.SceneNavigator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class NursingSuppliesScreen implements FunctionalityScreen {
    private final Parent root;

    public NursingSuppliesScreen(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        TableView<NursingVisit> table = new TableView<>();
        TableColumn<NursingVisit, String> idCol = new TableColumn<>("Visita");
        idCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        TableColumn<NursingVisit, String> patCol = new TableColumn<>("Paciente");
        patCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPatient().getCedula()));
        TableColumn<NursingVisit, String> noteCol = new TableColumn<>("Notas");
        noteCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNotas()));
        table.getColumns().addAll(idCol, patCol, noteCol);
        table.setItems(FXCollections.observableArrayList(appContext.getNursingService().listarVisitas()));

        root = BaseScreenLayout.create(functionality.getDisplayName(), role, navigator, new Label("Control básico de insumos (visitas registradas)"), table);
    }

    @Override public Parent getRoot() { return root; }
}
