# ClinicApp (Java 21 + JavaFX)

Proyecto base en Java 21 con JavaFX para selección de rol y dashboard básico por rol.

## Requisitos

- JDK 21
- Maven 3.9+

## Ejecutar

```bash
mvn javafx:run
```

## Compilar

```bash
mvn clean compile
```

## Estructura MVC (simple)

- `clinicapp`: aplicación principal y enum de roles.
- `clinicapp.ui`: vistas JavaFX.
- `clinicapp.ui.controllers`: controladores de navegación.
- `clinicapp.domain`: reservado (vacío por ahora).
- `clinicapp.services`: reservado (vacío por ahora).
