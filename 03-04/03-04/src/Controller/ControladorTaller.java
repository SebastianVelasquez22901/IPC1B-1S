package Controller;

import Model.Taller;
import Model.Vehiculo;
import View.frmCarga;

import javax.swing.SwingUtilities;

public class ControladorTaller {
    private final Taller taller;
    private final frmCarga vista;

    public ControladorTaller(Taller taller, frmCarga vista) {
        this.taller = taller;
        this.vista = vista;

        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);

        new Thread(this::procesarColaEspera).start();
        new Thread(this::procesarEnServicio).start();
        new Thread(this::procesarEntregaVehiculo).start();
    }

    public void agregarVehiculo(int idVehiculo) {
        Vehiculo vehiculo = new Vehiculo(idVehiculo);
        if (taller.agregarVehiculoCola(vehiculo)) {
            registrarEvento("Vehículo " + idVehiculo + " agregado a la cola de espera.");
            SwingUtilities.invokeLater(() -> vista.actualizarColaEspera(taller.getVehiculosEnCola())); // Actualizar la interfaz
        } else {
            registrarEvento("Cola de espera llena. No se pudo agregar el vehículo " + idVehiculo + ".");
        }
    }

    private void registrarEvento(String mensaje) {
        SwingUtilities.invokeLater(() -> vista.txtBitacora.append(mensaje + "\n"));
    }

    private void procesarColaEspera() {
        while (true) {
            Vehiculo vehiculo = taller.procesarColaEspera();
            if (vehiculo != null) {
                registrarEvento("Procesando vehículo " + vehiculo.getId() + " en Cola de Espera...");
                for (int i = 0; i <= 100; i++) {
                    final int progreso = i;
                    SwingUtilities.invokeLater(() -> {
                        vista.pbColaEspera.setValue(progreso);
                        vista.moverCarrito(progreso); // Mover el carrito
                    });
                    try {
                        Thread.sleep(50); // Más lento para observar el cambio
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                vehiculo.setEstado("En servicio");
                if (taller.agregarVehiculoEnServicio(vehiculo)) {
                    registrarEvento("Vehículo " + vehiculo.getId() + " pasó a En Servicio.");
                    SwingUtilities.invokeLater(() -> {
                        vista.actualizarColaEspera(taller.getVehiculosEnCola());
                        vista.actualizarEnServicio(taller.getVehiculosEnServicio());
                    });
                } else {
                    registrarEvento("No se pudo agregar el vehículo " + vehiculo.getId() + " a En Servicio.");
                }
            }
        }
    }
    
    private void procesarEnServicio() {
        while (true) {
            Vehiculo vehiculo = taller.procesarEnServicio();
            if (vehiculo != null) {
                SwingUtilities.invokeLater(() -> vista.actualizarEnServicio(taller.getVehiculosEnServicio())); // Actualizar antes de procesar
                registrarEvento("Procesando vehículo " + vehiculo.getId() + " en En Servicio...");
                for (int i = 0; i <= 100; i++) {
                    final int progreso = i;
                    SwingUtilities.invokeLater(() -> vista.pbEnServicio.setValue(progreso));
                    try {
                        Thread.sleep(100); // Moderado para observar el cambio
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                vehiculo.setEstado("Listo para entrega");
                if (taller.agregarVehiculoEntrega(vehiculo)) {
                    registrarEvento("Vehículo " + vehiculo.getId() + " pasó a Entrega de Vehículo.");
                    SwingUtilities.invokeLater(() -> vista.actualizarEntregaVehiculo(taller.getVehiculosParaEntrega()));
                } else {
                    registrarEvento("No se pudo agregar el vehículo " + vehiculo.getId() + " a Entrega de Vehículo.");
                }
            }
        }
    }
    
    private void procesarEntregaVehiculo() {
        while (true) {
            Vehiculo vehiculo = taller.procesarEntregaVehiculo();
            if (vehiculo != null) {
                registrarEvento("Procesando vehículo " + vehiculo.getId() + " en Entrega de Vehículo...");
                for (int i = 0; i <= 100; i++) {
                    final int progreso = i;
                    SwingUtilities.invokeLater(() -> vista.pbEntregaVehiculo.setValue(progreso));
                    try {
                        Thread.sleep(120); // Más rápido para observar el cambio
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                registrarEvento("Vehículo " + vehiculo.getId() + " ha sido entregado.");
                SwingUtilities.invokeLater(() -> vista.actualizarEntregaVehiculo(taller.getVehiculosParaEntrega()));
            }
        }
    }
}