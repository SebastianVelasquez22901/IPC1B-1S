package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JButton serializeButton;
    private JButton deserializeButton;
    public JTable table;
    public DefaultTableModel tableModel;

    public MainView() {
        // Configuración de la ventana principal
        setTitle("Serialización y Deserialización");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para el formulario
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(new JLabel("Nombre:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        formPanel.add(new JLabel("Edad:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        serializeButton = new JButton("Serializar");
        deserializeButton = new JButton("Deserializar");
        formPanel.add(serializeButton);
        formPanel.add(deserializeButton);

        add(formPanel, BorderLayout.NORTH);

        // Panel central para la tabla
        tableModel = new DefaultTableModel(new String[]{"Nombre", "Edad"}, 0);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null); // Deshabilita la edición directa de las celdas
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Métodos para obtener los datos del formulario
    public String getNameInput() {
        return nameField.getText();
    }

    public String getAgeInput() {
        return ageField.getText();
    }

    // Método para limpiar los campos del formulario
    public void clearForm() {
        nameField.setText("");
        ageField.setText("");
    }

    // Método para agregar una fila a la tabla
    public void addRowToTable(String name, int age) {
        tableModel.addRow(new Object[]{name, age});
    }

    // Método para limpiar la tabla
    public void clearTable() {
        tableModel.setRowCount(0);
    }

    // Métodos para agregar listeners a los botones
    public void addSerializeButtonListener(ActionListener listener) {
        serializeButton.addActionListener(listener);
    }

    public void addDeserializeButtonListener(ActionListener listener) {
        deserializeButton.addActionListener(listener);
    }

    // Método para mostrar mensajes al usuario
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Método para abrir la ventana modal de edición
    public void openEditModal(int rowIndex, String name, int age, EditListener editListener) {
        JDialog editDialog = new JDialog(this, "Editar Fila", true);
        editDialog.setSize(300, 200);
        editDialog.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField nameField = new JTextField(name);
        JTextField ageField = new JTextField(String.valueOf(age));
        JButton saveButton = new JButton("Guardar");

        editDialog.add(new JLabel("Nombre:"));
        editDialog.add(nameField);
        editDialog.add(new JLabel("Edad:"));
        editDialog.add(ageField);
        editDialog.add(new JLabel());
        editDialog.add(saveButton);

        saveButton.addActionListener(e -> {
            try {
                String newName = nameField.getText();
                int newAge = Integer.parseInt(ageField.getText());

                // Llama al listener con los datos editados
                editListener.onEdit(rowIndex, newName, newAge);

                editDialog.dispose();
            } catch (NumberFormatException ex) {
                displayMessage("La edad debe ser un número válido.");
            }
        });

        editDialog.setVisible(true);
    }

    // Interfaz personalizada para manejar la edición
    @FunctionalInterface
    public interface EditListener {
        void onEdit(int rowIndex, String newName, int newAge);
    }

    // Método para crear una ventana de carga
    public JDialog createLoadingDialog(String message) {
        JDialog dialog = new JDialog(this, "Cargando", true);
        dialog.setSize(300, 100);
        dialog.setLayout(new BorderLayout());
        dialog.add(new JLabel(message, SwingConstants.CENTER), BorderLayout.CENTER);
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        return dialog;
    }
}