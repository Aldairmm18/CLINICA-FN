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
        // Recursos humanos
        builders.put(RoleFunctionality.RH_CREAR_USUARIO_EMPLEADO, CreateEmployeeScreen::new);
        builders.put(RoleFunctionality.RH_ELIMINAR_USUARIO_EMPLEADO, DeleteEmployeeScreen::new);
        builders.put(RoleFunctionality.RH_ACTUALIZAR_DATOS_EMPLEADO, UpdateEmployeeScreen::new);
        builders.put(RoleFunctionality.RH_LISTAR_EMPLEADOS_USUARIOS, ListEmployeesScreen::new);

        // Administrativo
        builders.put(RoleFunctionality.ADM_REGISTRAR_PACIENTE, RegisterPatientScreen::new);
        builders.put(RoleFunctionality.ADM_REGISTRAR_CONTACTO_EMERGENCIA, RegisterEmergencyContactScreen::new);
        builders.put(RoleFunctionality.ADM_REGISTRAR_POLIZA_SEGURO, RegisterInsurancePolicyScreen::new);
        builders.put(RoleFunctionality.ADM_PROGRAMAR_CITA, ScheduleAppointmentScreen::new);
        builders.put(RoleFunctionality.ADM_GENERAR_IMPRIMIR_FACTURA, GenerateInvoiceScreen::new);

        // Soporte
        builders.put(RoleFunctionality.SOP_GESTION_INVENTARIO_MEDICAMENTOS, MedicationInventoryScreen::new);
        builders.put(RoleFunctionality.SOP_GESTION_INVENTARIO_PROCEDIMIENTOS, ProcedureInventoryScreen::new);
        builders.put(RoleFunctionality.SOP_GESTION_INVENTARIO_AYUDAS, DiagnosticAidInventoryScreen::new);
        builders.put(RoleFunctionality.SOP_SOPORTE_TECNICO_USUARIOS, SupportTicketScreen::new);

        // Enfermería
        builders.put(RoleFunctionality.ENF_BUSCAR_SELECCIONAR_PACIENTE, SelectPatientScreen::new);
        builders.put(RoleFunctionality.ENF_REGISTRAR_VISITA_SIGNOS_VITALES, RegisterNursingVisitScreen::new);
        builders.put(RoleFunctionality.ENF_REGISTRAR_MEDICAMENTOS_ADMINISTRADOS, RegisterAdministeredMedicationScreen::new);
        builders.put(RoleFunctionality.ENF_REGISTRAR_PROCEDIMIENTOS_REALIZADOS, RegisterPerformedProcedureScreen::new);
        builders.put(RoleFunctionality.ENF_REGISTRAR_PRUEBAS_OBSERVACIONES, RegisterPerformedDiagnosticTestScreen::new);

        // Médico
        builders.put(RoleFunctionality.MED_BUSCAR_SELECCIONAR_PACIENTE, SelectPatientScreen::new);
        builders.put(RoleFunctionality.MED_CREAR_REGISTRO_HISTORIA_CLINICA, CreateClinicalRecordScreen::new);
        builders.put(RoleFunctionality.MED_ACTUALIZAR_VER_HISTORIA_CLINICA, ViewClinicalHistoryScreen::new);
        builders.put(RoleFunctionality.MED_CREAR_ORDEN_MEDICAMENTOS, CreateMedicationOrderScreen::new);
        builders.put(RoleFunctionality.MED_CREAR_ORDEN_PROCEDIMIENTOS, CreateProcedureOrderScreen::new);
        builders.put(RoleFunctionality.MED_CREAR_ORDEN_AYUDA_DIAGNOSTICA, CreateDiagnosticAidOrderScreen::new);
        builders.put(RoleFunctionality.MED_REGISTRAR_RESULTADOS_AYUDA, RegisterDiagnosticResultScreen::new);
    }

    public FunctionalityScreen create(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator) {
        ScreenBuilder builder = builders.get(functionality);
        if (builder == null) {
            return new PlaceholderFunctionalityScreen(role, functionality, navigator);
        }
        return builder.build(role, functionality, appContext, navigator);
    }

    public boolean isMapped(RoleFunctionality functionality) {
        return builders.containsKey(functionality);
    }

    @FunctionalInterface
    private interface ScreenBuilder {
        FunctionalityScreen build(Role role, RoleFunctionality functionality, AppContext appContext, SceneNavigator navigator);
    }
}
