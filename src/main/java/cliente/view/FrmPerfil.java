package cliente.view;

import cliente.controller.UsuarioController;
import cliente.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.*;

public class FrmPerfil {

    private JFrame frame;

    private Usuario user;
    private boolean isAdmin;
    private JTextField txtID;
    private JTextField txtDNI;
    private JTextField txtUsuario;
    private JTextField txtAddress;
    private JTextField txtMail;
    private JTextField txtApellido;
    private JTextField txtNombre;
    private JPasswordField passwordField;
    private UsuarioController controller;
    Logger logger = LoggerFactory.getLogger(FrmPerfil.class);

    /**
     * Create the application.
     */
    public FrmPerfil(Usuario user, boolean isAdmin) {
        this.user = user;
        this.isAdmin = isAdmin;
        createForm();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void createForm() {

        controller = new UsuarioController();

        frame = new JFrame();
        frame.setTitle("iParking - Menú");
        frame.setBounds(100, 100, 403, 373);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension minSize = new Dimension(403, 373);
        frame.setMinimumSize(minSize);
        JMenuBar menuBar = new JMenuBar();
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);

        JMenu MenuSesion = new JMenu("Sesión");
        menuBar.add(MenuSesion);

        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
        MenuSesion.add(itemCerrarSesion);

        JMenu MenuAyuda = new JMenu("Ayuda");
        menuBar.add(MenuAyuda);

        JMenuItem itemAyuda = new JMenuItem("Ayuda");
        MenuAyuda.add(itemAyuda);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(15, 10));

        JPanel panelDatos = new JPanel();
        panel.add(panelDatos, BorderLayout.CENTER);
        panelDatos.setLayout(new GridLayout(0, 2, 15, 5));

        JLabel lblId = new JLabel("ID:");
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblId);

        txtID = new JTextField();
        txtID.setHorizontalAlignment(SwingConstants.CENTER);
        txtID.setColumns(10);
        txtID.setText(null != user.getId() ? user.getId().trim() : "");
        txtID.setEnabled(isAdmin);
        panelDatos.add(txtID);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        txtUsuario.setText(null != user.getNombreUsuario() ? user.getNombreUsuario().trim() : "");
        txtUsuario.setColumns(10);
        panelDatos.add(txtUsuario);

        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblDNI);

        txtDNI = new JTextField();
        txtDNI.setHorizontalAlignment(SwingConstants.CENTER);
        txtDNI.setColumns(10);
        txtDNI.setText(null != user.getDni() ? user.getDni().trim() : "");
        txtDNI.setEnabled(isAdmin);
        panelDatos.add(txtDNI);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == (char) 32) {
                    e.setKeyChar((char) 0);
                }

            }
        });
        passwordField.setText(null != user.getPassword() ? user.getPassword().trim() : "");
        panelDatos.add(passwordField);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
        txtNombre.setText(null != user.getNombre() ? user.getNombre().trim() : "");
        txtNombre.setColumns(10);
        panelDatos.add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblApellidos);

        txtApellido = new JTextField();
        txtApellido.setHorizontalAlignment(SwingConstants.CENTER);
        txtApellido.setText(null != user.getApellidos() ? user.getApellidos().trim() : "");
        txtApellido.setColumns(10);
        panelDatos.add(txtApellido);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblCorreo);

        txtMail = new JTextField();
        txtMail.setHorizontalAlignment(SwingConstants.CENTER);
        txtMail.setText(null != user.getCorreo() ? user.getCorreo().trim() : "");
        txtMail.setColumns(10);
        panelDatos.add(txtMail);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblDireccion);

        txtAddress = new JTextField();
        txtAddress.setHorizontalAlignment(SwingConstants.CENTER);
        txtAddress.setText(null != user.getDireccion() ? user.getDireccion().trim() : "");
        txtAddress.setColumns(10);
        panelDatos.add(txtAddress);

        JPanel panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.SOUTH);

        JButton btnListaVehiculos = new JButton("Lista vehículos");
        btnListaVehiculos.addActionListener(e -> {
            //TODO abrir ventana vehiculos
        });
        panel_2.add(btnListaVehiculos);

        JButton btnAceptar = new JButton("Guardar cambios");
        btnAceptar.addActionListener(e -> {
            if (checkCampos()) {
                logger.info("Check Usuario correcto");
                if (isAdmin) {
                    logger.info("Check edicion Usuario siendo admin correcto");
                    user.setId(txtID.getText().trim());
                    user.setNombreUsuario(txtUsuario.getText().trim());
                    user.setDni(txtDNI.getText().trim());
                }
                user.setPassword(new String(passwordField.getPassword()).trim());
                user.setNombre(txtNombre.getText().trim());
                user.setApellidos(txtApellido.getText().trim());
                user.setCorreo(txtMail.getText().trim());
                user.setDireccion(txtAddress.getText().trim());
                controller.callUpdateUsuarioById(user);
            } else {
                JOptionPane.showMessageDialog(null, "Compruebe que todos los campos estan correctamente cumplimentados");
            }

        });
        panel_2.add(btnAceptar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Si sale no se guardarán los cambios.", "Cuidado",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                if (isAdmin) {
                    //TODO volver a lista usuarios (admin).
                    new FrmEleccionAdmin(user);
                } else {
                    new FrmEleccion(user);
                }
                frame.dispose();
            }
        });
        panel_2.add(btnVolver);

        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblDatosDelPerfil = new JLabel("Datos del perfil");
        lblDatosDelPerfil.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatosDelPerfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_1.add(lblDatosDelPerfil);

        JSeparator separator_1 = new JSeparator();
        panel_1.add(separator_1);

    }

    private boolean checkCampos() {
        return (txtID.getText().trim().length() > 0 &&
                txtUsuario.getText().trim().length() > 0 &&
                txtDNI.getText().trim().length() == 9 &&
                passwordField.getPassword().length > 0 &&
                txtNombre.getText().trim().length() > 0 &&
                txtApellido.getText().trim().length() > 0 &&
                txtMail.getText().trim().length() > 0 &&
                txtMail.getText().contains("@") &&
                txtAddress.getText().trim().length() > 0
        );
    }

}
