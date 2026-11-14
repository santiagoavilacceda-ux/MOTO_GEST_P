package com.motogest.model;

import java.time.LocalDateTime;

/**
 * Módulo 3 (RF05) y Módulo 4 (RF04):
 * Registro inmutable de auditoría (quién, fecha, hora, entidad y tipo de modificación).
 */
public final class AuditoriaLog {
    private final String id;
    private final String responsableCorreo;
    private final String entidadAfectada;
    private final String tipoModificacion; // CREACION, EDICION, RADICACION_GARANTIA
    private final String detalleCambio;
    private final LocalDateTime fechaHora;

    public AuditoriaLog(String id, String responsableCorreo, String entidadAfectada, String tipoModificacion, String detalleCambio) {
        this.id = id;
        this.responsableCorreo = responsableCorreo;
        this.entidadAfectada = entidadAfectada;
        this.tipoModificacion = tipoModificacion;
        this.detalleCambio = detalleCambio;
        this.fechaHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s (%s): %s", fechaHora, responsableCorreo, entidadAfectada, tipoModificacion, detalleCambio);
    }
}
