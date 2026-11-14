package com.motogest;

import com.motogest.model.*;
import com.motogest.service.AuthService;
import com.motogest.service.GarantiaService;
import java.time.LocalDate;

/**
 * Demostración integrada de MotoGest en Java (Módulos 1, 2, 3 y 4)
 * usando los mismos datos del prototipo visual.
 */
public class MotoGestApp {
    public static void main(String[] args) {
        System.out.println("=== MOTOGEST: SISTEMA DE GESTION DE TALLER Y GARANTIAS ===");

        // Módulo 1: Registro de empleado (mecánico)
        AuthService auth = new AuthService();
        Empleado juanMecanico = auth.registrarEmpleado(
                "MEC-01", "Juan Mecanico", "1098765432", "juan.mecanico@motogest.com", "MECANICO", true
        );

        // Módulo 2: Registro de Cliente y Motocicleta
        Cliente carlos = new Cliente(
                "CLI-0142", "Carlos Perez", "1020304050", "310 987 6543", "carlos.perez@email.com", "Calle 45 # 18-22"
        );
        Motocicleta yamahaMt03 = new Motocicleta(
                "ABC-12D", "Yamaha", "MT-03", "321cc", 2024, "9C6RG3300R0124892", carlos.getId()
        );
        carlos.agregarMotocicleta(yamahaMt03);

        // Módulo 3: Orden de Servicio #OS-1045 + Repuesto + Foto Check-in
        OrdenServicio os1045 = new OrdenServicio(
                "#OS-1045", carlos, yamahaMt03, 24150,
                "Ruido seco en tren delantero al frenar fuerte y cambio pastillas", juanMecanico
        );
        os1045.agregarRepuesto(new Repuesto("REP-01", "Pastillas de freno Nissin", Repuesto.TipoRepuesto.ORIGINAL, 1, 4, 95000));
        os1045.agregarFotoCheckIn(new FotoInspeccion("F-01", "Odometro", "24,150 km", "fotos/odometro.jpg", "jpg", 420000));

        System.out.println("Orden creada: " + os1045.getCodigoOrden() + " | Placa: " + os1045.getMotocicleta().getPlaca());

        // Módulo 4: Semáforo Automático de Garantías
        GarantiaService garantiaService = new GarantiaService();
        LocalDate hoy = LocalDate.of(2026, 11, 7);

        Garantia gVigente = new Garantia(
                "WAR-01", "#OS-1020", "FAC-8821", "ABC-12D", "1020304050",
                "Cambio de pastillas de freno y liquido hidraulico DOT4", "Juan Mecanico",
                hoy.minusDays(14), 30
        );
        Garantia gVencida = new Garantia(
                "WAR-02", "#OS-0980", "FAC-7104", "ABC-12D", "1020304050",
                "Cambio de kit de arrastre y bujia Iridium", "Andres Gomez",
                hoy.minusDays(270), 60
        );

        garantiaService.registrarGarantia(gVigente);
        garantiaService.registrarGarantia(gVencida);

        for (Garantia g : garantiaService.buscar("ABC-12D")) {
            System.out.println(g.getNumeroOrden() + " -> Semaforo: " + g.calcularSemaforo(hoy)
                    + " | Dias restantes: " + g.getDiasRestantes(hoy));
        }

        // Radicar solicitud sobre garantía vigente
        SolicitudGarantia sol = garantiaService.radicarSolicitud("WAR-01", "Persiste ruido al frenar", hoy);
        System.out.println("Solicitud radicada: " + sol.getId() + " | Estado inicial: " + sol.getEstado());
    }
}
