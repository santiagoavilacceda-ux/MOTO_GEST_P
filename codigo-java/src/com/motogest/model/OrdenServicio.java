package com.motogest.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Módulo 3 (RF01, RF02, RF03, RF06):
 * Orden de trabajo del taller con reporte de fallas, mecánico responsable, repuestos y fotos.
 */
public class OrdenServicio {
    private String codigoOrden; // Ej: #OS-1045
    private Cliente cliente;
    private Motocicleta motocicleta;
    private int odometroKm;
    private String reporteFallas;
    private Empleado mecanicoResponsable;
    private String estado; // Ingresada, En Diagnóstico, En Reparación, Lista para Entrega
    private LocalDateTime fechaIngreso;
    private final List<Repuesto> repuestos = new ArrayList<>();
    private final List<FotoInspeccion> fotosCheckIn = new ArrayList<>();

    public OrdenServicio(String codigoOrden, Cliente cliente, Motocicleta motocicleta, int odometroKm, String reporteFallas, Empleado mecanicoResponsable) {
        if (mecanicoResponsable == null || !mecanicoResponsable.isActivo()) {
            throw new IllegalArgumentException("RF03: Debe asignar un empleado válido y activo en el sistema.");
        }
        this.codigoOrden = codigoOrden;
        this.cliente = cliente;
        this.motocicleta = motocicleta;
        this.odometroKm = odometroKm;
        this.reporteFallas = reporteFallas;
        this.mecanicoResponsable = mecanicoResponsable;
        this.estado = "Ingresada";
        this.fechaIngreso = LocalDateTime.now();
    }

    public void agregarRepuesto(Repuesto repuesto) {
        if (repuesto != null) this.repuestos.add(repuesto);
    }

    public void agregarFotoCheckIn(FotoInspeccion foto) {
        if (foto != null) this.fotosCheckIn.add(foto);
    }

    public String getCodigoOrden() { return codigoOrden; }
    public Cliente getCliente() { return cliente; }
    public Motocicleta getMotocicleta() { return motocicleta; }
    public int getOdometroKm() { return odometroKm; }
    public String getReporteFallas() { return reporteFallas; }
    public Empleado getMecanicoResponsable() { return mecanicoResponsable; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public List<Repuesto> getRepuestos() { return repuestos; }
    public List<FotoInspeccion> getFotosCheckIn() { return fotosCheckIn; }
}
