package controller;

import model.SerializableModel;
import view.MainView;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;

public class MainController {
    private SerializableModel[] models; // Cambiado a un arreglo
    private MainView view;

    public MainController(MainView view) {
        this.models = new SerializableModel[0]; // Inicialización del arreglo vacío
        this.view = view;

        // Agregar listeners a los botones
        view.addSerializeButtonListener(e -> serializeModel("model.ser"));
        view.addDeserializeButtonListener(e -> deserializeModel("model.ser"));

        // Listener para doble clic en la tabla
        view.table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = view.table.getSelectedRow();
                    if (row != -1) {
                        String name = (String) view.tableModel.getValueAt(row, 0);
                        int age = (int) view.tableModel.getValueAt(row, 1);

                        view.openEditModal(row, name, age, (rowIndex, newName, newAge) -> {
                            // Actualiza el modelo y la tabla
                            models[rowIndex].setName(newName);
                            models[rowIndex].setAge(newAge);
                            view.tableModel.setValueAt(newName, rowIndex, 0);
                            view.tableModel.setValueAt(newAge, rowIndex, 1);

                            // Actualiza el archivo serializado
                            updateSerializedFile("model.ser");
                        });
                    }
                }
            }
        });
    }

    public void serializeModel(String filePath) {
        new Thread(() -> {
            JDialog loadingDialog = view.createLoadingDialog("Serializando datos...");
            SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));

            try {
                // Simula un retraso en la operación
                Thread.sleep(2000);

                String name = view.getNameInput();
                int age = Integer.parseInt(view.getAgeInput());
                SerializableModel model = new SerializableModel(name, age);

                // Agregar el nuevo modelo al arreglo
                models = Arrays.copyOf(models, models.length + 1);
                models[models.length - 1] = model;

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                    oos.writeObject(models);
                }

                SwingUtilities.invokeLater(() -> {
                    view.addRowToTable(name, age);
                    view.clearForm();
                    loadingDialog.dispose();
                    view.displayMessage("Modelo serializado correctamente.");
                });
            } catch (InterruptedException | NumberFormatException e) {
                SwingUtilities.invokeLater(() -> {
                    loadingDialog.dispose();
                    view.displayMessage("La edad debe ser un número válido.");
                });
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    loadingDialog.dispose();
                    view.displayMessage("Error al serializar: " + e.getMessage());
                });
            }
        }).start();
    }

    public void deserializeModel(String filePath) {
        new Thread(() -> {
            JDialog loadingDialog = view.createLoadingDialog("Deserializando datos...");
            SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));

            try {
                // Simula un retraso en la operación
                Thread.sleep(2000);

                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                    models = (SerializableModel[]) ois.readObject(); // Leer como arreglo
                }

                SwingUtilities.invokeLater(() -> {
                    view.clearTable();
                    for (SerializableModel model : models) {
                        view.addRowToTable(model.getName(), model.getAge());
                    }
                    loadingDialog.dispose();
                    view.displayMessage("Modelos deserializados correctamente.");
                });
            } catch (InterruptedException | IOException | ClassNotFoundException e) {
                SwingUtilities.invokeLater(() -> {
                    loadingDialog.dispose();
                    view.displayMessage("Error al deserializar: " + e.getMessage());
                });
            }
        }).start();
    }

    public void updateSerializedFile(String filePath) {
        new Thread(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(models);
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> view.displayMessage("Error al actualizar el archivo: " + e.getMessage()));
            }
        }).start();
    }
}