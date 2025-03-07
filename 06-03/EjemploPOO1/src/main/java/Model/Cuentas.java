package Model;

import java.util.UUID;

public class Cuentas {
    private String idCuenta;
    private String tipoCuenta;

    // Constructor
    public Cuentas(String tipoCuenta) {
        this.idCuenta = UUID.randomUUID().toString(); // auto-generate idCuenta
        this.tipoCuenta = tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    @Override
    public String toString() {
        return "idCuenta: " + this.idCuenta + "tipo: " + this.tipoCuenta;
    }

    public String retornoCuenta() {
        if (this.idCuenta.isEmpty() || this.tipoCuenta.isEmpty()) {
            return "No hay cuentas";
        }
        return "idCuenta: " + this.idCuenta + " - tipo: " + this.tipoCuenta + "\n";
    }

    public boolean tieneCuenta() {
        return !this.idCuenta.isEmpty() || !this.tipoCuenta.isEmpty();
    }
}