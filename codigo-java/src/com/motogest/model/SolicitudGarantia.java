package com.motogest.model;

import java.time.LocalDateTime;

/**
 * Módulo 4 (RF03 - Registro de solicitud de garantía):
 * Representa un reclamo radicado por un cliente únicamente cuando la garantía está vigente.
 */
public class SolicitudGarantia {
    private String id;
    private String garantiaId;
    private String numeroOrdenOriginal;
    private String placa;
    private String motivoReclamo;
    private String estado; // Estado inicial: "Pendiente"
    private LocalDateTime fechaRadicacion;

    public SolicitudGarantia(String id, String garantiaId, String numeroOrdenOriginal, String placa, String motivoReclamo) {
        this.id = id;
        this.garantiaId = garantiaId;
        this.numeroOrdenOriginal = numeroOrdenOriginal;
        this.placa = placa;
        this.motivoReclamo = motivoReclamo;
        this.estado = "Pendiente"; // Requerimiento RF03 Módulo 4
        this.fechaRadicacion = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getGarantiaId() { return garantiaId; }
    public String getNumeroOrdenOriginal() { return numeroOrdenOriginal; }
    public String getPlaca() { return placa; }
    public String getMotivoReclamo() { return motivoReclamo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaRadicacion() { return fechaRadicacion; }
}
