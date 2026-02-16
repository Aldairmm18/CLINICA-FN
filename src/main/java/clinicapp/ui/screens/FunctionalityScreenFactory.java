package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.navigation.SceneNavigator;

import java.util.EnumMap;
import java.util.Map;

public class FunctionalityScreenFactory {
    private final Map<RoleFunctionality, ScreenBuilder> builders = new EnumMap<>(RoleFunctionality.class);

    public FunctionalityScreenFactory() {
        builders.put(RoleFunctionality.ADMIN_REGISTRAR_PACIENTES, RegisterPatientScreen::new);
        builders.put(RoleFunctionality.ADMIN_PROGRAMAR_CITAS, ScheduleAppointmentScreen::new);

        builders.put(RoleFunctionality.MED_REALIZAR_CONSULTA, SelectPatientScreen::new);
        builders.put(RoleFunctionality.MED_REGISTRAR_EVOLUCION_CLINICA, CreateClinicalRecordScreen::new);
        builders.put(RoleFunctionality.MED_DIAGNOSTICO_Y_PLAN_TRATAMIENTO, ViewClinicalHistoryScreen::new);

        builders.put(RoleFunctionality.ENF_TRIAGE_Y_SIGNOS_VITALES, RegisterNursingVisitScreen::new);

        builders.put(RoleFunctionality.SOPORTE_MONITOREAR_SISTEMA, MedicationInventoryScreen::new);

        builders.put(RoleFunctionality.RH_CONTROL_ASISTENCIA, ListEmployeesScreen::new);
        builders.put(RoleFunctionality.RH_REGISTRAR_PERSONAL, CreateEmployeeScreen::new);
    }

    public FunctionalityScreen create(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        ScreenBuilder builder = builders.get(functionality);
        if (builder == null) {
            return new PlaceholderFunctionalityScreen(role, functionality, navigator);
        }
        return builder.build(role, functionality, appContext, navigator);
    }

    @FunctionalInterface
    private interface ScreenBuilder {
        FunctionalityScreen build(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator);
    }
}
