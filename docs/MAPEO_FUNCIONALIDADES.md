# Mapeo de funcionalidades (auditoría Step 5)

Este documento audita la consistencia entre `RoleFunctionality`, las pantallas JavaFX y el dominio/services en memoria.

## Resultado de auditoría

- Total funcionalidades en `RoleFunctionality`: **25**.
- Total funcionalidades mapeadas explícitamente en `FunctionalityScreenFactory`: **25**.
- Cobertura de mapping: **100%**.
- `PlaceholderFunctionalityScreen` queda solo como fallback de seguridad.

## Tabla de mapeo

| Rol | Enum RoleFunctionality | Pantalla (Screen class) | Service(s) usados | Entidad principal del dominio tocada |
|---|---|---|---|---|
| Recursos Humanos | `RH_REGISTRAR_PERSONAL` | `CreateEmployeeScreen` | `EmployeeService` | `Employee` |
| Recursos Humanos | `RH_ELIMINAR_USUARIO_EMPLEADO` | `DeleteEmployeeScreen` | `EmployeeService` | `Employee` |
| Recursos Humanos | `RH_ACTUALIZAR_DATOS_EMPLEADO` | `UpdateEmployeeScreen` | `EmployeeService` | `Employee` |
| Recursos Humanos | `RH_CONTROL_ASISTENCIA` | `ListEmployeesScreen` | `EmployeeService` | `Employee` |
| Administrativo | `ADMIN_REGISTRAR_PACIENTES` | `RegisterPatientScreen` | `PatientService` | `Patient` |
| Administrativo | `ADMIN_PROGRAMAR_CITAS` | `ScheduleAppointmentScreen` | `AppointmentService`, `EmployeeService`, `PatientService` | `Appointment` |
| Administrativo | `ADM_REGISTRAR_CONTACTO_EMERGENCIA` | `RegisterEmergencyContactScreen` | `PatientService` | `EmergencyContact`, `Patient` |
| Administrativo | `ADM_REGISTRAR_POLIZA_SEGURO` | `RegisterInsurancePolicyScreen` | `PatientService` | `InsurancePolicy`, `Patient` |
| Administrativo | `ADM_GENERAR_IMPRIMIR_FACTURA` | `GenerateInvoiceScreen` | `InvoiceService`, `AppointmentService` | `Invoice`, `InvoiceItem` |
| Soporte | `SOP_GESTION_INVENTARIO_MEDICAMENTOS` | `MedicationInventoryScreen` | `InventoryService` | `Medication` |
| Soporte | `SOP_GESTION_INVENTARIO_PROCEDIMIENTOS` | `ProcedureInventoryScreen` | `InventoryService` | `Procedure` |
| Soporte | `SOP_GESTION_INVENTARIO_AYUDAS` | `DiagnosticAidInventoryScreen` | `InventoryService` | `DiagnosticAid` |
| Soporte | `SOP_SOPORTE_TECNICO_USUARIOS` | `SupportTicketScreen` | `SupportTicketService` | `SupportTicket` |
| Enfermería | `ENF_TRIAGE_Y_SIGNOS_VITALES` | `RegisterNursingVisitScreen` | `NursingService`, `EmployeeService` | `NursingVisit`, `VitalSigns` |
| Enfermería | `ENF_REGISTRAR_MEDICAMENTOS_ADMINISTRADOS` | `RegisterAdministeredMedicationScreen` | `NursingService`, `OrderService`, `EmployeeService` | `AdministeredMedication`, `MedicationOrder` |
| Enfermería | `ENF_REGISTRAR_PROCEDIMIENTOS_REALIZADOS` | `RegisterPerformedProcedureScreen` | `NursingService`, `OrderService` | `PerformedProcedure`, `ProcedureOrder` |
| Enfermería | `ENF_REGISTRAR_PRUEBAS_OBSERVACIONES` | `RegisterPerformedDiagnosticTestScreen` | `NursingService`, `OrderService` | `PerformedDiagnosticTest`, `DiagnosticAidOrder` |
| Enfermería | `ENF_GESTIONAR_INSUMOS_BASICOS` | `NursingSuppliesScreen` | `NursingService` | `NursingVisit` |
| Médico | `MED_REALIZAR_CONSULTA` | `SelectPatientScreen` | `PatientService` | `Patient` |
| Médico | `MED_DIAGNOSTICO_Y_PLAN_TRATAMIENTO` | `ViewClinicalHistoryScreen` | `ClinicalRecordService` | `ClinicalRecord` |
| Médico | `MED_REGISTRAR_EVOLUCION_CLINICA` | `CreateClinicalRecordScreen` | `ClinicalRecordService`, `EmployeeService` | `ClinicalRecord` |
| Médico | `MED_CREAR_ORDEN_MEDICAMENTOS` | `CreateMedicationOrderScreen` | `OrderService`, `InventoryService`, `EmployeeService` | `MedicationOrder`, `OrderItem` |
| Médico | `MED_CREAR_ORDEN_PROCEDIMIENTOS` | `CreateProcedureOrderScreen` | `OrderService`, `InventoryService`, `EmployeeService` | `ProcedureOrder`, `OrderItem` |
| Médico | `MED_CREAR_ORDEN_AYUDA_DIAGNOSTICA` | `CreateDiagnosticAidOrderScreen` | `OrderService`, `InventoryService`, `EmployeeService` | `DiagnosticAidOrder`, `OrderItem` |
| Médico | `MED_REGISTRAR_RESULTADOS_AYUDA` | `RegisterDiagnosticResultScreen` | `ClinicalRecordService`, `OrderService`, `EmployeeService` | `DiagnosticAidOrder`, `ClinicalRecord` |

## Nota de nomenclatura (refactor realizado)

Se renombraron enums que resultaban ambiguos respecto al dominio clínico real para reflejar exactamente las funcionalidades pedidas (por ejemplo, `MED_EMITIR_CERTIFICADOS` -> `MED_CREAR_ORDEN_PROCEDIMIENTOS`, `MED_DERIVAR_PACIENTES` -> `MED_REGISTRAR_RESULTADOS_AYUDA`, y equivalentes en Administrativo/Soporte/Enfermería/RRHH).
