package Model;

import java.util.UUID;

public class Usuario {
    private String nombre;
    private String idUsuario;
    private String apellido;
    private String correo;
    private String cui;
    private Cuentas[] cuentas = new Cuentas[4]; // almacenamos 4 cuentas

    // constructor que inicialize los valores
    public Usuario(String nombre, String apellido, String correo, String cui) {
        this.nombre = nombre;
        this.idUsuario = UUID.randomUUID().toString(); // auto-generate idUsuario
        this.apellido = apellido;
        this.correo = correo;
        this.cui = cui;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    // Método para agregar cuenta
    public void agregarCuenta(String tipoCuenta) {
        int i = 0; // contador (vector empieza en 0)
        while (true) {
            if (i >= 4) {
                System.out.println("No se pueden agregar más cuentas");
                break;
            }
            if (cuentas[i] != null) {
                i++;
                continue;
            } else {
                Cuentas auxiliar = new Cuentas(tipoCuenta);
                cuentas[i] = auxiliar;
                break;
            }
        }
    }

    public String retornoCuentasUsuario() {
        String variable = "";
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null) {
                variable += cuentas[i].retornoCuenta();
            }
        }
        return variable;
    }

    public String usuarioYCuentas() {
        return "usuario (" + this.nombre + ") id [" + this.idUsuario + "] --- \nCuentas: \n" + retornoCuentasUsuario();
    }
}