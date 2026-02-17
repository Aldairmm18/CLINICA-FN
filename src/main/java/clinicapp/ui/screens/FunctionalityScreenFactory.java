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
        builders.put(RoleFunctionality.RH_REGISTRAR_PERSONAL, CreateEmployeeScreen::new);
        builders.put(RoleFunctionality.RH_GESTIONAR_TURNOS_Y_HORARIOS, ManageShiftsAndSchedulesScreen::new);
        builders.put(RoleFunctionality.RH_GESTIONAR_PERMISOS_Y_VACACIONES, ManageLeavesAndVacationsScreen::new);
        builders.put(RoleFunctionality.RH_CONTROL_ASISTENCIA, ListEmployeesScreen::new);

        builders.put(RoleFunctionality.ADMIN_REGISTRAR_PACIENTES, RegisterPatientScreen::new);
        builders.put(RoleFunctionality.ADMIN_PROGRAMAR_CITAS, ScheduleAppointmentScreen::new);
        builders.put(RoleFunctionality.ADMIN_GESTIONAR_FACTURACION, ManageBillingScreen::new);
        builders.put(RoleFunctionality.ADMIN_VERIFICAR_COBERTURA_SEGURO, VerifyInsuranceCoverageScreen::new);
        builders.put(RoleFunctionality.ADMIN_EMITIR_REPORTES_ADMINISTRATIVOS, GenerateAdministrativeReportsScreen::new);

        builders.put(RoleFunctionality.MED_REALIZAR_CONSULTA, SelectPatientScreen::new);
        builders.put(RoleFunctionality.MED_REGISTRAR_EVOLUCION_CLINICA, CreateClinicalRecordScreen::new);
        builders.put(RoleFunctionality.MED_DIAGNOSTICO_Y_PLAN_TRATAMIENTO, ViewClinicalHistoryScreen::new);
        builders.put(RoleFunctionality.MED_SOLICITAR_ESTUDIOS, RequestDiagnosticStudiesScreen::new);
        builders.put(RoleFunctionality.MED_GENERAR_RECETAS, GeneratePrescriptionsScreen::new);
        builders.put(RoleFunctionality.MED_EMITIR_CERTIFICADOS, IssueCertificatesScreen::new);
        builders.put(RoleFunctionality.MED_DERIVAR_PACIENTES, ReferPatientsScreen::new);

        builders.put(RoleFunctionality.ENF_TRIAGE_Y_SIGNOS_VITALES, RegisterNursingVisitScreen::new);
        builders.put(RoleFunctionality.ENF_ADMINISTRAR_MEDICACION, AdministerMedicationScreen::new);
        builders.put(RoleFunctionality.ENF_CUIDADOS_Y_PROCEDIMIENTOS, NursingCareAndProceduresScreen::new);
        builders.put(RoleFunctionality.ENF_REGISTRO_HOJAS_ENFERMERIA, NursingSheetsRecordScreen::new);
        builders.put(RoleFunctionality.ENF_GESTIONAR_INSUMOS_BASICOS, ManageBasicSuppliesScreen::new);

        builders.put(RoleFunctionality.SOPORTE_GESTIONAR_USUARIOS_Y_ROLES, ManageUsersAndRolesScreen::new);
        builders.put(RoleFunctionality.SOPORTE_MONITOREAR_SISTEMA, MedicationInventoryScreen::new);
        builders.put(RoleFunctionality.SOPORTE_RESPALDOS_Y_RECUPERACION, BackupAndRecoveryScreen::new);
        builders.put(RoleFunctionality.SOPORTE_MESA_DE_AYUDA, HelpDeskScreen::new);
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
