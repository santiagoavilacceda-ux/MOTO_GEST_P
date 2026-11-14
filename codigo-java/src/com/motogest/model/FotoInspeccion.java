package com.motogest.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Módulo 3 (RF06 - Galería de estado inicial del vehículo):
 * Almacena evidencia fotográfica de check-in validando tipo y tamaño máximo (5 MB).
 */
public class FotoInspeccion {
    private static final long MAX_BYTES = 5L * 1024L * 1024L;
    private static final List<String> EXTENSIONES_PERMITIDAS = List.of("jpg", "jpeg", "png");

    private String id;
    private String titulo;
    private String descripcion;
    private String rutaArchivo;
    private String extension;
    private long tamanoBytes;
    private LocalDateTime fechaCaptura;

    public FotoInspeccion(String id, String titulo, String descripcion, String rutaArchivo, String extension, long tamanoBytes) {
        if (extension == null || !EXTENSIONES_PERMITIDAS.contains(extension.toLowerCase())) {
            throw new IllegalArgumentException("RF06: Formato de imagen no permitido. Solo JPG o PNG.");
        }
        if (tamanoBytes <= 0 || tamanoBytes > MAX_BYTES) {
            throw new IllegalArgumentException("RF06: La imagen excede el límite permitido de 5 MB.");
        }
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaArchivo = rutaArchivo;
        this.extension = extension.toLowerCase();
        this.tamanoBytes = tamanoBytes;
        this.fechaCaptura = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getRutaArchivo() { return rutaArchivo; }
    public LocalDateTime getFechaCaptura() { return fechaCaptura; }
}
