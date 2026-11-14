package com.motogest.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Módulo 4 (RF01, RF02 - Consulta y Validación Automática de Garantía):
 * Calcula automáticamente el semáforo de vigencia comparando la fecha del servicio con la fecha actual.
 */
public class Garantia {
    public enum EstadoSemaforo {
        VERDE_EN_GARANTIA,
        AMARILLO_POR_VENCER,
        ROJO_GARANTIA_VENCIDA
    }

    private String id;
    private String numeroOrden;
    private String numeroFactura;
    private String placaMoto;
    private String documentoCliente;
    private String trabajosRealizados;
    private String tecnicoResponsable;
    private LocalDate fechaServicio;
    private int diasCobertura; // Configurable: 30, 60, 90 días

    public Garantia(String id, String numeroOrden, String numeroFactura, String placaMoto, String documentoCliente, String trabajosRealizados, String tecnicoResponsable, LocalDate fechaServicio, int diasCobertura) {
        this.id = id;
        this.numeroOrden = numeroOrden;
        this.numeroFactura = numeroFactura;
        this.placaMoto = placaMoto.toUpperCase();
        this.documentoCliente = documentoCliente;
        this.trabajosRealizados = trabajosRealizados;
        this.tecnicoResponsable = tecnicoResponsable;
        this.fechaServicio = fechaServicio;
        this.diasCobertura = diasCobertura;
    }

    public long getDiasTranscurridos(LocalDate fechaActual) {
        return Math.max(0, ChronoUnit.DAYS.between(fechaServicio, fechaActual));
    }

    public long getDiasRestantes(LocalDate fechaActual) {
        return Math.max(0, diasCobertura - getDiasTranscurridos(fechaActual));
    }

    public boolean estaVigente(LocalDate fechaActual) {
        return getDiasTranscurridos(fechaActual) <= diasCobertura;
    }

    public EstadoSemaforo calcularSemaforo(LocalDate fechaActual) {
        if (!estaVigente(fechaActual)) {
            return EstadoSemaforo.ROJO_GARANTIA_VENCIDA;
        }
        long restantes = getDiasRestantes(fechaActual);
        if (restantes <= 5) {
            return EstadoSemaforo.AMARILLO_POR_VENCER;
        }
        return EstadoSemaforo.VERDE_EN_GARANTIA;
    }

    public String getId() { return id; }
    public String getNumeroOrden() { return numeroOrden; }
    public String getNumeroFactura() { return numeroFactura; }
    public String getPlacaMoto() { return placaMoto; }
    public String getDocumentoCliente() { return documentoCliente; }
    public String getTrabajosRealizados() { return trabajosRealizados; }
    public String getTecnicoResponsable() { return tecnicoResponsable; }
    public LocalDate getFechaServicio() { return fechaServicio; }
    public int getDiasCobertura() { return diasCobertura; }
    public void setDiasCobertura(int diasCobertura) { this.diasCobertura = diasCobertura; }
}
