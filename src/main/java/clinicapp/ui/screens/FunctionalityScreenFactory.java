package clinicapp.ui.screens;

import clinicapp.AppContext;
import clinicapp.MainApp;
import clinicapp.Role;
import clinicapp.RoleFunctionality;
import clinicapp.navigation.SceneNavigator;

public class FunctionalityScreenFactory {
    private final SceneNavigator navigator;

    public FunctionalityScreenFactory(SceneNavigator navigator) {
        this.navigator = navigator;
    }

    public FunctionalityScreen create(RoleFunctionality functionality, MainApp mainApp, AppContext appContext) {
        Role role = functionality.getRole();
        return switch (functionality) {
            // Recursos humanos (4)
            case RH_CREAR_USUARIO_EMPLEADO -> new CreateEmployeeScreen(role, functionality, appContext, navigator);
            case RH_ELIMINAR_USUARIO_EMPLEADO -> new DeleteEmployeeScreen(role, functionality, appContext, navigator);
            case RH_ACTUALIZAR_DATOS_EMPLEADO -> new UpdateEmployeeScreen(role, functionality, appContext, navigator);
            case RH_LISTAR_EMPLEADOS_USUARIOS -> new ListEmployeesScreen(role, functionality, appContext, navigator);

            // Administrativo (5)
            case ADM_REGISTRAR_PACIENTE -> new RegisterPatientScreen(role, functionality, appContext, navigator);
            case ADM_REGISTRAR_CONTACTO_EMERGENCIA -> new RegisterEmergencyContactScreen(role, functionality, appContext, navigator);
            case ADM_REGISTRAR_POLIZA_SEGURO -> new RegisterInsurancePolicyScreen(role, functionality, appContext, navigator);
            case ADM_PROGRAMAR_CITA -> new ScheduleAppointmentScreen(role, functionality, appContext, navigator);
            case ADM_GENERAR_IMPRIMIR_FACTURA -> new GenerateInvoiceScreen(role, functionality, appContext, navigator);

            // Soporte (4)
            case SOP_GESTION_INVENTARIO_MEDICAMENTOS -> new MedicationInventoryScreen(role, functionality, appContext, navigator);
            case SOP_GESTION_INVENTARIO_PROCEDIMIENTOS -> new ProcedureInventoryScreen(role, functionality, appContext, navigator);
            case SOP_GESTION_INVENTARIO_AYUDAS -> new DiagnosticAidInventoryScreen(role, functionality, appContext, navigator);
            case SOP_SOPORTE_TECNICO_USUARIOS -> new SupportTicketScreen(role, functionality, appContext, navigator);

            // Enfermería (5)
            case ENF_BUSCAR_SELECCIONAR_PACIENTE -> new SelectPatientScreen(role, functionality, appContext, navigator);
            case ENF_REGISTRAR_VISITA_SIGNOS_VITALES -> new RegisterNursingVisitScreen(role, functionality, appContext, navigator);
            case ENF_REGISTRAR_MEDICAMENTOS_ADMINISTRADOS -> new RegisterAdministeredMedicationScreen(role, functionality, appContext, navigator);
            case ENF_REGISTRAR_PROCEDIMIENTOS_REALIZADOS -> new RegisterPerformedProcedureScreen(role, functionality, appContext, navigator);
            case ENF_REGISTRAR_PRUEBAS_OBSERVACIONES -> new RegisterPerformedDiagnosticTestScreen(role, functionality, appContext, navigator);

            // Médico (7)
            case MED_BUSCAR_SELECCIONAR_PACIENTE -> new SelectPatientScreen(role, functionality, appContext, navigator);
            case MED_CREAR_REGISTRO_HISTORIA_CLINICA -> new CreateClinicalRecordScreen(role, functionality, appContext, navigator);
            case MED_ACTUALIZAR_VER_HISTORIA_CLINICA -> new ViewClinicalHistoryScreen(role, functionality, appContext, navigator);
            case MED_CREAR_ORDEN_MEDICAMENTOS -> new CreateMedicationOrderScreen(role, functionality, appContext, navigator);
            case MED_CREAR_ORDEN_PROCEDIMIENTOS -> new CreateProcedureOrderScreen(role, functionality, appContext, navigator);
            case MED_CREAR_ORDEN_AYUDA_DIAGNOSTICA -> new CreateDiagnosticAidOrderScreen(role, functionality, appContext, navigator);
            case MED_REGISTRAR_RESULTADOS_AYUDA -> new RegisterDiagnosticResultScreen(role, functionality, appContext, navigator);
        };
    }
}
