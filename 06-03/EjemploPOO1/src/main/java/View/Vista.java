package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Vista extends JFrame {
    private JTextArea textArea;
    private JTextField nombreField, apellidoField, correoField, cuiField;
    private JComboBox<String> tipoCuentaComboBox, usuarioComboBox;
    private JButton crearUsuarioButton, crearCuentaButton, mostrarInfoButton;

    public Vista() {
        setTitle("Gestión de Usuarios y Cuentas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createTitledBorder("Información"));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(createLabeledField("Nombre:", nombreField = new JTextField()));
        inputPanel.add(createLabeledField("Apellido:", apellidoField = new JTextField()));
        inputPanel.add(createLabeledField("Correo:", correoField = new JTextField()));
        inputPanel.add(createLabeledField("CUI:", cuiField = new JTextField()));

        JPanel tipoCuentaPanel = new JPanel(new BorderLayout(5, 5));
        JLabel tipoCuentaLabel = new JLabel("Tipo Cuenta:");
        tipoCuentaComboBox = new JComboBox<>(new String[]{"monetaria", "ahorro"});
        tipoCuentaPanel.add(tipoCuentaLabel, BorderLayout.WEST);
        tipoCuentaPanel.add(tipoCuentaComboBox, BorderLayout.CENTER);
        inputPanel.add(tipoCuentaPanel);

        JPanel usuarioPanel = new JPanel(new BorderLayout(5, 5));
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioComboBox = new JComboBox<>();
        usuarioPanel.add(usuarioLabel, BorderLayout.WEST);
        usuarioPanel.add(usuarioComboBox, BorderLayout.CENTER);
        inputPanel.add(usuarioPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        crearUsuarioButton = new JButton("Crear Usuario");
        crearCuentaButton = new JButton("Crear Cuenta");
        mostrarInfoButton = new JButton("Mostrar Información");

        buttonPanel.add(crearUsuarioButton);
        buttonPanel.add(crearCuentaButton);
        buttonPanel.add(mostrarInfoButton);

        inputPanel.add(buttonPanel);
        add(inputPanel, BorderLayout.NORTH);
    }

    private JPanel createLabeledField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    public void setCrearUsuarioListener(ActionListener listener) {
        crearUsuarioButton.addActionListener(listener);
    }

    public void setCrearCuentaListener(ActionListener listener) {
        crearCuentaButton.addActionListener(listener);
    }

    public void setMostrarInfoListener(ActionListener listener) {
        mostrarInfoButton.addActionListener(listener);
    }

    public String getNombre() {
        return nombreField.getText();
    }

    public String getApellido() {
        return apellidoField.getText();
    }

    public String getCorreo() {
        return correoField.getText();
    }

    public String getCui() {
        return cuiField.getText();
    }

    public String getTipoCuenta() {
        return (String) tipoCuentaComboBox.getSelectedItem();
    }

    public String getUsuarioSeleccionado() {
        return (String) usuarioComboBox.getSelectedItem();
    }

    public void agregarUsuarioComboBox(String nombreCompleto) {
        usuarioComboBox.addItem(nombreCompleto);
    }

    public void imprimir(String texto) {
        textArea.append(texto + "\n");
    }
}