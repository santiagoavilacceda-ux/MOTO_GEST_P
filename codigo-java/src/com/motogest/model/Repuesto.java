package com.motogest.model;

/**
 * Módulo 3 (RF02 - Conjunto de repuestos necesarios):
 * Controla stock disponible, repuestos faltantes por comprar y si es ORIGINAL o GENERICO.
 */
public class Repuesto {
    public enum TipoRepuesto {
        ORIGINAL,
        GENERICO
    }

    private String codigo;
    private String nombre;
    private TipoRepuesto tipo;
    private int cantidadRequerida;
    private int stockDisponible;
    private double precioUnitario;

    public Repuesto(String codigo, String nombre, TipoRepuesto tipo, int cantidadRequerida, int stockDisponible, double precioUnitario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidadRequerida = cantidadRequerida;
        this.stockDisponible = stockDisponible;
        this.precioUnitario = precioUnitario;
    }

    public boolean hayStockSuficiente() {
        return stockDisponible >= cantidadRequerida;
    }

    public int getCantidadFaltanteCompra() {
        return Math.max(0, cantidadRequerida - stockDisponible);
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public TipoRepuesto getTipo() { return tipo; }
    public int getCantidadRequerida() { return cantidadRequerida; }
    public int getStockDisponible() { return stockDisponible; }
    public double getPrecioUnitario() { return precioUnitario; }
}
