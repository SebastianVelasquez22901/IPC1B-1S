package Model;

public class Taller {
    private Vehiculo[] colaEspera = new Vehiculo[5];
    private Vehiculo[] enServicio = new Vehiculo[5];
    private Vehiculo[] entregaVehiculo = new Vehiculo[5];
    private int indiceColaEspera = 0;
    private int indiceEnServicio = 0;
    private int indiceEntregaVehiculo = 0;

    public synchronized boolean agregarVehiculoCola(Vehiculo vehiculo) {
        if (indiceColaEspera < colaEspera.length) {
            colaEspera[indiceColaEspera++] = vehiculo;
            return true;
        }
        return false;
    }

    public synchronized Vehiculo procesarColaEspera() {
        if (indiceColaEspera > 0) {
            Vehiculo vehiculo = colaEspera[0];
            System.arraycopy(colaEspera, 1, colaEspera, 0, --indiceColaEspera);
            return vehiculo;
        }
        return null;
    }

    public synchronized boolean agregarVehiculoEnServicio(Vehiculo vehiculo) {
        if (indiceEnServicio < enServicio.length) {
            enServicio[indiceEnServicio++] = vehiculo;
            return true;
        }
        return false;
    }

    public synchronized Vehiculo procesarEnServicio() {
        if (indiceEnServicio > 0) {
            System.out.println("Vehiculo en servicio: " + enServicio[0].getId());
            Vehiculo vehiculo = enServicio[0];
            System.arraycopy(enServicio, 1, enServicio, 0, --indiceEnServicio);
            return vehiculo;
        }
        return null;
    }

    public synchronized boolean agregarVehiculoEntrega(Vehiculo vehiculo) {
        if (indiceEntregaVehiculo < entregaVehiculo.length) {
            entregaVehiculo[indiceEntregaVehiculo++] = vehiculo;
            return true;
        }
        return false;
    }

    public synchronized Vehiculo procesarEntregaVehiculo() {
        if (indiceEntregaVehiculo > 0) {
            Vehiculo vehiculo = entregaVehiculo[0];
            System.arraycopy(entregaVehiculo, 1, entregaVehiculo, 0, --indiceEntregaVehiculo);
            return vehiculo;
        }
        return null;
    }

    public synchronized int getVehiculosEnCola() {
        return indiceColaEspera;
    }
    
    public synchronized int getVehiculosEnServicio() {
        return indiceEnServicio;
    }
    
    public synchronized int getVehiculosParaEntrega() {
        return indiceEntregaVehiculo;
    }
}