package clinicapp;

import java.util.List;
import java.util.stream.Stream;

public enum RoleFunctionality {
    // Recursos Humanos (4)
    RH_CREAR_USUARIO_EMPLEADO(Role.RECURSOS_HUMANOS, "Crear usuario/empleado"),
    RH_ELIMINAR_USUARIO_EMPLEADO(Role.RECURSOS_HUMANOS, "Eliminar usuario/empleado"),
    RH_ACTUALIZAR_DATOS_EMPLEADO(Role.RECURSOS_HUMANOS, "Actualizar datos de empleado"),
    RH_LISTAR_EMPLEADOS_USUARIOS(Role.RECURSOS_HUMANOS, "Listar empleados/usuarios"),

    // Administrativo (5)
    ADM_REGISTRAR_PACIENTE(Role.PERSONAL_ADMINISTRATIVO, "Registrar paciente"),
    ADM_REGISTRAR_CONTACTO_EMERGENCIA(Role.PERSONAL_ADMINISTRATIVO, "Registrar contacto de emergencia"),
    ADM_REGISTRAR_POLIZA_SEGURO(Role.PERSONAL_ADMINISTRATIVO, "Registrar póliza de seguro"),
    ADM_PROGRAMAR_CITA(Role.PERSONAL_ADMINISTRATIVO, "Programar cita"),
    ADM_GENERAR_IMPRIMIR_FACTURA(Role.PERSONAL_ADMINISTRATIVO, "Generar/imprimir factura"),

    // Soporte (4)
    SOP_GESTION_INVENTARIO_MEDICAMENTOS(Role.SOPORTE_DE_INFORMACION, "Gestión inventario medicamentos"),
    SOP_GESTION_INVENTARIO_PROCEDIMIENTOS(Role.SOPORTE_DE_INFORMACION, "Gestión inventario procedimientos"),
    SOP_GESTION_INVENTARIO_AYUDAS(Role.SOPORTE_DE_INFORMACION, "Gestión inventario ayudas"),
    SOP_SOPORTE_TECNICO_USUARIOS(Role.SOPORTE_DE_INFORMACION, "Soporte técnico usuarios"),

    // Enfermería (5)
    ENF_BUSCAR_SELECCIONAR_PACIENTE(Role.ENFERMERIA, "Buscar/seleccionar paciente"),
    ENF_REGISTRAR_VISITA_SIGNOS_VITALES(Role.ENFERMERIA, "Registrar visita signos vitales"),
    ENF_REGISTRAR_MEDICAMENTOS_ADMINISTRADOS(Role.ENFERMERIA, "Registrar medicamentos administrados"),
    ENF_REGISTRAR_PROCEDIMIENTOS_REALIZADOS(Role.ENFERMERIA, "Registrar procedimientos realizados"),
    ENF_REGISTRAR_PRUEBAS_OBSERVACIONES(Role.ENFERMERIA, "Registrar pruebas/observaciones"),

    // Médico (7)
    MED_BUSCAR_SELECCIONAR_PACIENTE(Role.MEDICO, "Buscar/seleccionar paciente"),
    MED_CREAR_REGISTRO_HISTORIA_CLINICA(Role.MEDICO, "Crear registro historia clínica"),
    MED_ACTUALIZAR_VER_HISTORIA_CLINICA(Role.MEDICO, "Actualizar/ver historia clínica"),
    MED_CREAR_ORDEN_MEDICAMENTOS(Role.MEDICO, "Crear orden medicamentos"),
    MED_CREAR_ORDEN_PROCEDIMIENTOS(Role.MEDICO, "Crear orden procedimientos"),
    MED_CREAR_ORDEN_AYUDA_DIAGNOSTICA(Role.MEDICO, "Crear orden ayuda diagnóstica"),
    MED_REGISTRAR_RESULTADOS_AYUDA(Role.MEDICO, "Registrar resultados ayuda diagnóstica");

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
