package com.motogest.model;

import java.util.regex.Pattern;

/**
 * Módulo 2 (RF02 - Registro de motocicleta asociada al cliente):
 * Valida formato de placa y mantiene integridad referencial con el propietario.
 */
public class Motocicleta {
    private static final Pattern PLACA_MOTO_PATTERN = Pattern.compile("^[A-Z]{3}-?[0-9]{2}[A-Z]$");

    private String placa;
    private String marca;
    private String modelo;
    private String cilindraje;
    private int anio;
    private String vin;
    private String clienteId;

    public Motocicleta(String placa, String marca, String modelo, String cilindraje, int anio, String vin, String clienteId) {
        if (placa == null || !PLACA_MOTO_PATTERN.matcher(placa.toUpperCase()).matches()) {
            throw new IllegalArgumentException("RF02/RF03: Formato de placa inválido (ejemplo válido: ABC-12D).");
        }
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("RF02: La motocicleta debe estar asociada a un cliente existente.");
        }
        this.placa = placa.toUpperCase();
        this.marca = marca;
        this.modelo = modelo;
        this.cilindraje = cilindraje;
        this.anio = anio;
        this.vin = vin;
        this.clienteId = clienteId;
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getCilindraje() { return cilindraje; }
    public int getAnio() { return anio; }
    public String getVin() { return vin; }
    public String getClienteId() { return clienteId; }
}
