import Controller.ControladorTaller;
import Model.Taller;
import View.frmCarga;

public class SimulacionHilos {

    public static void main(String[] args) {
        // Crear el modelo y la vista
        Taller taller = new Taller();
        frmCarga vista = new frmCarga();

        // Crear el controlador y asignarlo a la vista
        ControladorTaller controlador = new ControladorTaller(taller, vista);
        vista.setControlador(controlador); // Asignar el controlador a la vista

        // Mostrar la vista
        vista.setVisible(true);

        // Agregar vehículos de forma simulada
        for (int i = 1; i <= 5; i++) {
            controlador.agregarVehiculo(i);
            try {
                Thread.sleep(5000); // Tiempo de llegada ajustado (22 segundos)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}