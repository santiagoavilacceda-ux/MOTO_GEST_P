package com.motogest.service;

import com.motogest.model.AuditoriaLog;
import com.motogest.model.Garantia;
import com.motogest.model.SolicitudGarantia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Módulo 4 (RF01, RF02, RF03, RF04):
 * Servicio de consulta rápida por placa/factura/documento, validación automática y auditoría.
 */
public class GarantiaService {
    private final List<Garantia> garantias = new ArrayList<>();
    private final List<SolicitudGarantia> solicitudes = new ArrayList<>();
    private final List<AuditoriaLog> logsAuditoria = new ArrayList<>();

    public void registrarGarantia(Garantia g) {
        garantias.add(g);
    }

    // RF01: Buscar por placa, número de factura o documento del cliente
    public List<Garantia> buscar(String criterio) {
        String q = criterio == null ? "" : criterio.trim().toUpperCase();
        return garantias.stream()
                .filter(g -> g.getPlacaMoto().equalsIgnoreCase(q)
                        || g.getNumeroFactura().equalsIgnoreCase(q)
                        || g.getDocumentoCliente().equalsIgnoreCase(q))
                .toList();
    }

    // RF03: Radicar solicitud únicamente si la garantía está vigente según fecha actual
    public SolicitudGarantia radicarSolicitud(String garantiaId, String motivo, LocalDate fechaActual) {
        Garantia garantia = garantias.stream()
                .filter(g -> g.getId().equals(garantiaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Garantía no encontrada."));

        if (!garantia.estaVigente(fechaActual)) {
            throw new IllegalStateException("RF03: No se puede radicar la solicitud porque la garantía está VENCIDA.");
        }

        SolicitudGarantia solicitud = new SolicitudGarantia(
                "SOL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
                garantia.getId(),
                garantia.getNumeroOrden(),
                garantia.getPlacaMoto(),
                motivo
        );
        solicitudes.add(solicitud);
        return solicitud;
    }

    // RF04: Actualizar días de cobertura con control de rol y registro de auditoría inmutable
    public void actualizarCobertura(String garantiaId, int nuevosDias, String correoResponsable, String rolResponsable) {
        if (!"ADMINISTRADOR".equalsIgnoreCase(rolResponsable)) {
            throw new SecurityException("RF04: Solo un usuario autorizado puede modificar garantías.");
        }
        Garantia garantia = garantias.stream()
                .filter(g -> g.getId().equals(garantiaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Garantía no encontrada."));

        int diasAnteriores = garantia.getDiasCobertura();
        garantia.setDiasCobertura(nuevosDias);

        logsAuditoria.add(new AuditoriaLog(
                UUID.randomUUID().toString(),
                correoResponsable,
                "Garantia#" + garantiaId,
                "ACTUALIZACION_COBERTURA",
                "Días modificados de " + diasAnteriores + " a " + nuevosDias
        ));
    }

    public List<AuditoriaLog> getLogsAuditoria() {
        return List.copyOf(logsAuditoria);
    }
}
