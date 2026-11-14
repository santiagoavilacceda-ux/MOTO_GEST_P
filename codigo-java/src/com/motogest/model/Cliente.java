package com.motogest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Módulo 2 (RF01, RF03):
 * Representa al propietario de una o más motocicletas con validación de campos obligatorios.
 */
public class Cliente {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private String id;
    private String nombre;
    private String identificacion; // Cédula única
    private String telefono;
    private String correo;
    private String direccion;
    private final List<Motocicleta> motocicletas = new ArrayList<>();

    public Cliente(String id, String nombre, String identificacion, String telefono, String correo, String direccion) {
        validarCampos(nombre, identificacion, telefono, correo);
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public static void validarCampos(String nombre, String identificacion, String telefono, String correo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("RF03: El nombre del cliente es obligatorio.");
        }
        if (identificacion == null || identificacion.isBlank()) {
            throw new IllegalArgumentException("RF03: La identificación (CC) es obligatoria.");
        }
        if (telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("RF03: El teléfono de contacto es obligatorio.");
        }
        if (correo != null && !correo.isBlank() && !EMAIL_PATTERN.matcher(correo).matches()) {
            throw new IllegalArgumentException("RF03: El formato del correo electrónico no es válido.");
        }
    }

    public void agregarMotocicleta(Motocicleta moto) {
        if (moto != null) {
            this.motocicletas.add(moto);
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getIdentificacion() { return identificacion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public List<Motocicleta> getMotocicletas() { return motocicletas; }
}
