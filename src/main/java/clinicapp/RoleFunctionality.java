package clinicapp;

import java.util.List;
import java.util.stream.Stream;

public enum RoleFunctionality {
    // Recursos Humanos (4)
    RH_REGISTRAR_PERSONAL(Role.RECURSOS_HUMANOS, "Registrar personal"),
    RH_GESTIONAR_TURNOS_Y_HORARIOS(Role.RECURSOS_HUMANOS, "Gestionar turnos y horarios"),
    RH_GESTIONAR_PERMISOS_Y_VACACIONES(Role.RECURSOS_HUMANOS, "Gestionar permisos y vacaciones"),
    RH_CONTROL_ASISTENCIA(Role.RECURSOS_HUMANOS, "Control de asistencia"),

    // Administrativo (5)
    ADMIN_REGISTRAR_PACIENTES(Role.PERSONAL_ADMINISTRATIVO, "Registrar pacientes"),
    ADMIN_PROGRAMAR_CITAS(Role.PERSONAL_ADMINISTRATIVO, "Programar citas"),
    ADMIN_GESTIONAR_FACTURACION(Role.PERSONAL_ADMINISTRATIVO, "Gestionar facturación"),
    ADMIN_VERIFICAR_COBERTURA_SEGURO(Role.PERSONAL_ADMINISTRATIVO, "Verificar cobertura de seguro"),
    ADMIN_EMITIR_REPORTES_ADMINISTRATIVOS(Role.PERSONAL_ADMINISTRATIVO, "Emitir reportes administrativos"),

    // Soporte (4)
    SOPORTE_GESTIONAR_USUARIOS_Y_ROLES(Role.SOPORTE_DE_INFORMACION, "Gestionar usuarios y roles"),
    SOPORTE_MONITOREAR_SISTEMA(Role.SOPORTE_DE_INFORMACION, "Monitorear sistema"),
    SOPORTE_RESPALDOS_Y_RECUPERACION(Role.SOPORTE_DE_INFORMACION, "Respaldos y recuperación"),
    SOPORTE_MESA_DE_AYUDA(Role.SOPORTE_DE_INFORMACION, "Mesa de ayuda"),

    // Enfermería (5)
    ENF_TRIAGE_Y_SIGNOS_VITALES(Role.ENFERMERIA, "Triage y signos vitales"),
    ENF_ADMINISTRAR_MEDICACION(Role.ENFERMERIA, "Administrar medicación"),
    ENF_CUIDADOS_Y_PROCEDIMIENTOS(Role.ENFERMERIA, "Cuidados y procedimientos"),
    ENF_REGISTRO_HOJAS_ENFERMERIA(Role.ENFERMERIA, "Registro en hojas de enfermería"),
    ENF_GESTIONAR_INSUMOS_BASICOS(Role.ENFERMERIA, "Gestionar insumos básicos"),

    // Médico (7)
    MED_REALIZAR_CONSULTA(Role.MEDICO, "Realizar consulta"),
    MED_DIAGNOSTICO_Y_PLAN_TRATAMIENTO(Role.MEDICO, "Diagnóstico y plan de tratamiento"),
    MED_SOLICITAR_ESTUDIOS(Role.MEDICO, "Solicitar estudios"),
    MED_REGISTRAR_EVOLUCION_CLINICA(Role.MEDICO, "Registrar evolución clínica"),
    MED_GENERAR_RECETAS(Role.MEDICO, "Generar recetas"),
    MED_EMITIR_CERTIFICADOS(Role.MEDICO, "Emitir certificados"),
    MED_DERIVAR_PACIENTES(Role.MEDICO, "Derivar pacientes");

    private final Role role;
    private final String displayName;

    RoleFunctionality(Role role, String displayName) {
        this.role = role;
        this.displayName = displayName;
    }

    public Role getRole() {
        return role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<RoleFunctionality> forRole(Role role) {
        return Stream.of(values())
                .filter(functionality -> functionality.role == role)
                .toList();
    }
}
