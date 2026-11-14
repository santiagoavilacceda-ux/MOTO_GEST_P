package com.motogest.service;

import com.motogest.model.Empleado;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Módulo 1 (RF01, RF02, RF03, RF04 y RNF01-RNF11):
 * Gestiona registro de empleados, hash de contraseñas, bloqueo tras 5 intentos y tokens UUID.
 */
public class AuthService {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
    private final Map<String, Empleado> empleadosPorCorreo = new HashMap<>();
    private final Map<String, TokenRecuperacion> tokensActivos = new HashMap<>();

    private static class TokenRecuperacion {
        String correo;
        LocalDateTime expiracion;
        boolean usado;
    }

    // RF01 + RNF01 + RNF02: Registro con contraseña segura incluso si falla correo
    public Empleado registrarEmpleado(String id, String nombre, String cedula, String correo, String rol, boolean servicioCorreoDisponible) {
        if (empleadosPorCorreo.containsKey(correo)) {
            throw new IllegalArgumentException("Ya existe un empleado registrado con ese correo.");
        }
        Empleado emp = new Empleado(id, nombre, cedula, correo, rol);
        String passwordTemporal = generarPasswordSegura();
        emp.setPasswordHash(hashearPassword(passwordTemporal));
        empleadosPorCorreo.put(correo, emp);

        if (servicioCorreoDisponible) {
            System.out.println("[Correo enviado a " + correo + "] Contraseña temporal generada.");
        } else {
            System.out.println("[RNF02] Servicio de correo no disponible. Empleado guardado igualmente en BD.");
        }
        return emp;
    }

    // RF02 + RF03 + RNF06 + RNF07: Validación y autenticación con bloqueo al 5to intento
    public boolean autenticar(String correo, String passwordIngresada) {
        Empleado emp = empleadosPorCorreo.get(correo);
        if (emp == null || !emp.isActivo()) {
            return false;
        }
        if (emp.isBloqueado()) {
            throw new IllegalStateException("RNF07: Cuenta bloqueada tras 5 intentos fallidos.");
        }
        String hashIngresado = hashearPassword(passwordIngresada);
        if (emp.getPasswordHash().equals(hashIngresado)) {
            emp.reiniciarIntentos();
            return true;
        } else {
            emp.incrementarIntentosFallidos();
            return false;
        }
    }

    // RF04 + RNF09 + RNF10 + RNF11: Token UUID de un solo uso con expiración de 30 min y respuesta genérica
    public String solicitarRecuperacion(String correo) {
        if (empleadosPorCorreo.containsKey(correo)) {
            TokenRecuperacion t = new TokenRecuperacion();
            t.correo = correo;
            t.expiracion = LocalDateTime.now().plusMinutes(30);
            t.usado = false;
            String uuid = UUID.randomUUID().toString();
            tokensActivos.put(uuid, t);
        }
        return "Si el correo está registrado, recibirás instrucciones para restablecer tu contraseña.";
    }

    // RNF01: Mínimo 8 caracteres, mayúsculas, números y símbolos
    private String generarPasswordSegura() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder("Mg1!");
        for (int i = 0; i < 8; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    // RNF06: Almacenamiento hasheado (nunca en texto plano)
    private String hashearPassword(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(raw.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error al hashear contraseña", e);
        }
    }
}
