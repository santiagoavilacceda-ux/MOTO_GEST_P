package com.motogest.model;

/**
 * Módulo 1 (RF01 - Registro de empleados):
 * Representa a un empleado del taller (Administrador, Asesor/Recepcionista o Mecánico).
 */
public class Empleado {
    private String id;
    private String nombre;
    private String cedula;
    private String correoInstitucional;
    private String rol; // ADMINISTRADOR, ASESOR, MECANICO
    private String passwordHash;
    private int intentosFallidos;
    private boolean bloqueado;
    private boolean activo;

    public Empleado(String id, String nombre, String cedula, String correoInstitucional, String rol) {
        if (nombre == null || nombre.isBlank() || cedula == null || cedula.isBlank() || correoInstitucional == null || correoInstitucional.isBlank()) {
            throw new IllegalArgumentException("Nombre, cédula y correo institucional son obligatorios.");
        }
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.correoInstitucional = correoInstitucional;
        this.rol = rol;
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.activo = true;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getCorreoInstitucional() { return correoInstitucional; }
    public String getRol() { return rol; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public void incrementarIntentosFallidos() {
        this.intentosFallidos++;
        if (this.intentosFallidos >= 5) {
            this.bloqueado = true; // RNF07: Bloquear cuenta después de 5 intentos fallidos
        }
    }
    public void reiniciarIntentos() { this.intentosFallidos = 0; }
    public boolean isBloqueado() { return bloqueado; }
    public void setBloqueado(boolean bloqueado) { this.bloqueado = bloqueado; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
