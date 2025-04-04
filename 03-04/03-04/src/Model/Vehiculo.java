package Model;

public class Vehiculo {
    private int id;
    private String estado;

    public Vehiculo(int id) {
        this.id = id;
        this.estado = "En cola de espera";
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}