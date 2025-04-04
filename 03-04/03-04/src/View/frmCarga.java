package View;

import Controller.ControladorTaller;
import javax.swing.JOptionPane;

public class frmCarga extends javax.swing.JFrame {

    private ControladorTaller controlador; // Referencia al controlador

    public frmCarga() {
        initComponents();
    }

    // Método para asignar el controlador desde la clase principal
    public void setControlador(ControladorTaller controlador) {
        this.controlador = controlador;
    }

    
    private void initComponents() {
        pbColaEspera = new javax.swing.JProgressBar();
        pbEnServicio = new javax.swing.JProgressBar();
        pbEntregaVehiculo = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblColaEspera = new javax.swing.JLabel();
        lblEnServicio = new javax.swing.JLabel();
        lblEntregaVehiculo = new javax.swing.JLabel();
        btnAgregarVehiculo = new javax.swing.JButton();
        txtBitacora = new javax.swing.JTextArea();
        scrollBitacora = new javax.swing.JScrollPane();
        lblCarrito = new javax.swing.JLabel(); // Inicializar el JLabel del carrito
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    
        jLabel1.setText("Cola de Espera");
        jLabel2.setText("En Servicio");
        jLabel3.setText("Listo, Entrega de Vehiculo");
    
        lblColaEspera.setText("0/5");
        lblEnServicio.setText("0/5");
        lblEntregaVehiculo.setText("0/5");
    
        btnAgregarVehiculo.setText("Agregar Vehículo");
        btnAgregarVehiculo.addActionListener(evt -> agregarVehiculoActionPerformed(evt));
    
        txtBitacora.setColumns(20);
        txtBitacora.setRows(5);
        txtBitacora.setEditable(false);
        scrollBitacora.setViewportView(txtBitacora);
    
        // Configurar el JLabel del carrito
        lblCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/carrito.png"))); // Ruta del ícono
        lblCarrito.setSize(50, 50); // Tamaño del carrito
        lblCarrito.setLocation(0, 0); // Posición inicial del carrito
    
        // Usar un JLayeredPane para superponer el carrito sobre la barra de progreso
        javax.swing.JLayeredPane layeredPane = new javax.swing.JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setPreferredSize(new java.awt.Dimension(400, 50));
        pbColaEspera.setBounds(0, 0, 400, 30); // Configurar tamaño y posición de la barra de progreso
        lblCarrito.setBounds(0, 0, 50, 30); // Configurar tamaño y posición inicial del carrito
        layeredPane.add(pbColaEspera, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(lblCarrito, javax.swing.JLayeredPane.PALETTE_LAYER);
    
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
    
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblColaEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pbEntregaVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                            .addComponent(pbEnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEntregaVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnAgregarVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollBitacora))
                .addContainerGap(68, Short.MAX_VALUE))
        );
    
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblColaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pbEnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEntregaVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pbEntregaVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarVehiculo)
                .addGap(18, 18, 18)
                .addComponent(scrollBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
    
        pack();
    }

    private void agregarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir un cuadro de diálogo para ingresar el ID del vehículo
        String input = JOptionPane.showInputDialog(this, "Ingrese el ID del vehículo:", "Agregar Vehículo", JOptionPane.PLAIN_MESSAGE);
        if (input != null && !input.isEmpty()) {
            try {
                int idVehiculo = Integer.parseInt(input); // Convertir el ID a entero
                controlador.agregarVehiculo(idVehiculo); // Llamar al controlador para procesar el vehículo
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para actualizar la barra de progreso de la cola de espera
    public void actualizarColaEspera(int cantidad) {
        lblColaEspera.setText(cantidad + "/5");
        pbColaEspera.setValue((cantidad * 100) / 5);
    }

    public void actualizarEnServicio(int cantidad) {
        System.out.println("Cantidad de vehículos en servicio: " + cantidad); // Debugging
        lblEnServicio.setText(cantidad + "/5");
        pbEnServicio.setValue((cantidad * 100) / 5);
    }
    
    public void actualizarEntregaVehiculo(int cantidad) {
        System.out.println("Cantidad de vehículos para entrega: " + cantidad); // Debugging
        lblEntregaVehiculo.setText(cantidad + "/5");
        pbEntregaVehiculo.setValue((cantidad * 100) / 5);
    }
    // Método para registrar eventos en la bitácora
    public void registrarEvento(String mensaje) {
        txtBitacora.append(mensaje + "\n");
    }

    public void moverCarrito(int progreso) {
    int maxWidth = pbColaEspera.getWidth() - lblCarrito.getWidth(); // Ancho máximo disponible para el carrito
    int nuevaPosX = (progreso * maxWidth) / 100; // Calcular la nueva posición X
    lblCarrito.setLocation(nuevaPosX, lblCarrito.getY()); // Actualizar posición del carrito
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new frmCarga().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnAgregarVehiculo;
    private javax.swing.JLabel lblCarrito; 
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel lblColaEspera;
    public javax.swing.JLabel lblEnServicio;
    public javax.swing.JLabel lblEntregaVehiculo;
    public javax.swing.JProgressBar pbColaEspera;
    public javax.swing.JProgressBar pbEnServicio;
    public javax.swing.JProgressBar pbEntregaVehiculo;
    private javax.swing.JScrollPane scrollBitacora;
    public javax.swing.JTextArea txtBitacora;
}