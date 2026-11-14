# MotoGest - Backend y Modelos en Java (JDK 17+)

Estructura de clases Java basada en la Tabla de Características de MotoGest (Módulos 1, 2, 3 y 4).

## Estructura del Proyecto
- `src/com/motogest/model/Empleado.java` (Módulo 1: Administrador - Empleados y roles)
- `src/com/motogest/model/Cliente.java` (Módulo 2: Registro de Clientes con cédula única)
- `src/com/motogest/model/Motocicleta.java` (Módulo 2: Motocicletas vinculadas al cliente)
- `src/com/motogest/model/Repuesto.java` (Módulo 3 RF02: Stock, originales y genéricos)
- `src/com/motogest/model/FotoInspeccion.java` (Módulo 3 RF06: Galería de check-in fotográfico)
- `src/com/motogest/model/OrdenServicio.java` (Módulo 3 RF01-RF06: Orden de taller completa)
- `src/com/motogest/model/Garantia.java` (Módulo 4 RF01-RF02: Cobertura y cálculo automático)
- `src/com/motogest/model/SolicitudGarantia.java` (Módulo 4 RF03: Radicación de reclamo)
- `src/com/motogest/model/AuditoriaLog.java` (Módulo 3 RF05 y Módulo 4 RF04: Auditoría inmutable)
- `src/com/motogest/service/AuthService.java` (Módulo 1 RF01-RF04 y RNF01-RNF11: Seguridad y bloqueo)
- `src/com/motogest/service/GarantiaService.java` (Módulo 4 RF01-RF04: Semáforo y validaciones)
- `src/com/motogest/MotoGestApp.java` (Clase principal ejecutable con datos de demostración)

## Cómo compilar y ejecutar en terminal
```bash
javac -d out src/com/motogest/model/*.java src/com/motogest/service/*.java src/com/motogest/*.java
java -cp out com.motogest.MotoGestApp
```
