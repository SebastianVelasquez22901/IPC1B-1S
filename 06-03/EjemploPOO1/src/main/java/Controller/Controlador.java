package Controller;

import Model.Usuario;
import View.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Controlador {
    private Vista vista;
    private Map<String, Usuario> usuarios;

    public Controlador(Vista vista) {
        this.vista = vista;
        this.usuarios = new HashMap<>();

        this.vista.setCrearUsuarioListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearUsuario(vista.getNombre(), vista.getApellido(), vista.getCorreo(), vista.getCui());
            }
        });

        this.vista.setCrearCuentaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearCuenta(vista.getUsuarioSeleccionado(), vista.getTipoCuenta());
            }
        });

        this.vista.setMostrarInfoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarInformacion(vista.getUsuarioSeleccionado());
            }
        });
    }

    public void CrearUsuario(String nombre, String apellido, String correo, String cui) {
        Usuario usuario = new Usuario(nombre, apellido, correo, cui);
        usuarios.put(usuario.getIdUsuario(), usuario);
        vista.agregarUsuarioComboBox(nombre + " " + apellido);
        vista.imprimir("Usuario creado: " + nombre + " " + apellido);
    }

    public void CrearCuenta(String nombreCompleto, String tipoCuenta) {
        Usuario usuario = usuarios.values().stream()
                .filter(u -> (u.getNombre() + " " + u.getApellido()).equals(nombreCompleto))
                .findFirst()
                .orElse(null);
        if (usuario != null) {
            usuario.agregarCuenta(tipoCuenta);
            vista.imprimir("Cuenta creada para usuario: " + nombreCompleto);
        } else {
            vista.imprimir("Usuario no encontrado: " + nombreCompleto);
        }
    }

    public void MostrarInformacion(String nombreCompleto) {
        Usuario usuario = usuarios.values().stream()
                .filter(u -> (u.getNombre() + " " + u.getApellido()).equals(nombreCompleto))
                .findFirst()
                .orElse(null);
        if (usuario != null) {
            vista.imprimir(usuario.usuarioYCuentas());
        } else {
            vista.imprimir("Usuario no encontrado: " + nombreCompleto);
        }
    }
}