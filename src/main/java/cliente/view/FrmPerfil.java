package cliente.view;

import cliente.controller.UsuarioController;
import cliente.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class FrmPerfil {

    private JFrame frame;

    private final Usuario userIn;
    private final boolean isAdmin;
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

    public FrmPerfil(boolean isAdmin, Usuario userIn) throws IOException {
        this.userIn = userIn;
        this.isAdmin = isAdmin;
        createForm();
        rellenarCampos();

    }

    /**
     * Create the application.
     */
    public FrmPerfil(Usuario userIn, boolean isAdmin, boolean firstLogging)  {
        this.userIn = userIn;
        this.isAdmin = isAdmin;
        if (firstLogging) {
            JOptionPane.showMessageDialog(null, "Por favor rellena los datos para poder usar la applicación correctamente.");
        }
        createForm();
        rellenarCampos();

    }

    /**
     * Initialize the contents of the frame.
     */
    private void createForm() {

        controller = new UsuarioController();

        frame = new JFrame();
        frame.setTitle("iParking - Información del perfil");
        //frame.setIconImage(new ImageIcon(ImageIO.read(new File("src/main/resources/aparcamiento.png"))).getImage());
        frame.setTitle("iParking - Menú");
        frame.setBounds(100, 100, 550, 498);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    if (isAdmin) {
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    } else {
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                } else {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        JMenuBar menuBar = new JMenuBar();
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);

        JMenu MenuSesion = new JMenu("Sesión");
        menuBar.add(MenuSesion);

        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
        itemCerrarSesion.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.dispose();
            }
        });
        MenuSesion.add(itemCerrarSesion);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(15, 10));

        JPanel panelDatos = new JPanel();
        panel.add(panelDatos, BorderLayout.CENTER);
        panelDatos.setLayout(new GridLayout(0, 2, 15, 5));

        JLabel lblTipoMembresia = new JLabel("Suscripción:");
        lblTipoMembresia.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblTipoMembresia);

        JTextField txtTipoMembresia = new JTextField();
        txtTipoMembresia.setText("<dynamic>");
        txtTipoMembresia.setHorizontalAlignment(SwingConstants.CENTER);
        txtTipoMembresia.setEnabled(false);
        txtTipoMembresia.setColumns(10);
        txtTipoMembresia.setText(userIn.getMembresia());
        panelDatos.add(txtTipoMembresia);

        JLabel lblId = new JLabel("ID:");
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblId);

        txtID = new JTextField();
        txtID.setHorizontalAlignment(SwingConstants.CENTER);
        txtID.setColumns(10);
        txtID.setEnabled(isAdmin);
        panelDatos.add(txtID);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        txtUsuario.setColumns(10);
        panelDatos.add(txtUsuario);

        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblDNI);

        txtDNI = new JTextField();
        txtDNI.setHorizontalAlignment(SwingConstants.CENTER);
        txtDNI.setColumns(10);
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
        panelDatos.add(passwordField);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
        txtNombre.setColumns(10);
        panelDatos.add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblApellidos);

        txtApellido = new JTextField();
        txtApellido.setHorizontalAlignment(SwingConstants.CENTER);
        txtApellido.setColumns(10);
        panelDatos.add(txtApellido);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblCorreo);

        txtMail = new JTextField();
        txtMail.setHorizontalAlignment(SwingConstants.CENTER);
        txtMail.setColumns(10);
        panelDatos.add(txtMail);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(lblDireccion);

        txtAddress = new JTextField();
        txtAddress.setHorizontalAlignment(SwingConstants.CENTER);
        txtAddress.setColumns(10);
        panelDatos.add(txtAddress);

        JPanel panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.SOUTH);

        JButton btnListaVehiculos = new JButton("Lista vehículos");
        btnListaVehiculos.addActionListener(e -> new FrmListaVehiculos(userIn));

        JButton btnActualizarSuscripcion = new JButton("Actualizar suscripción");
        btnActualizarSuscripcion.addActionListener(e -> {
            new FrmPago(userIn);
            rellenarCampos();
        });
        panel_2.add(btnActualizarSuscripcion);
        panel_2.add(btnListaVehiculos);

        JButton btnAceptar = new JButton("Guardar cambios");
        btnAceptar.addActionListener(e -> {
            if (checkCampos()) {
                logger.info("Check Usuario correcto");
                if (isAdmin) {
                    logger.info("Check edicion Usuario siendo admin correcto");
                    userIn.setId(txtID.getText().trim());
                    userIn.setDni(txtDNI.getText().trim());
                }
                userIn.setNombreUsuario(txtUsuario.getText().trim());
                userIn.setPassword(new String(passwordField.getPassword()).trim());
                userIn.setNombre(txtNombre.getText().trim());
                userIn.setApellidos(txtApellido.getText().trim());
                userIn.setCorreo(txtMail.getText().trim());
                userIn.setDireccion(txtAddress.getText().trim());
                controller.callUpdateUsuarioById(userIn);
                JOptionPane.showMessageDialog(null, "Datos del perfin actualizados.");
            } else {
                JOptionPane.showMessageDialog(null, "Compruebe que todos los campos estan correctamente cumplimentados");
            }

        });
        panel_2.add(btnAceptar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Compruebe que ha guardado los cambios.", "Cuidado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                if (isAdmin) {
                    frame.dispose();
                } else {
                    frame.dispose();
                    try {
                        new FrmEleccion(userIn);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

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


        frame.setVisible(true);
    }

    private boolean checkCampos() {
        return (txtID.getText().trim().length() > 0 && txtUsuario.getText().trim().length() > 0 && txtDNI.getText().trim().length() == 9 && passwordField.getPassword().length > 0 && txtNombre.getText().trim().length() > 0 && txtApellido.getText().trim().length() > 0 && txtMail.getText().trim().length() > 0 && txtMail.getText().contains("@") && txtAddress.getText().trim().length() > 0);
    }

    private void rellenarCampos() {
        txtDNI.setText(null != userIn.getDni() ? userIn.getDni().trim() : "");

        txtID.setText(null != userIn.getId() ? userIn.getId().trim() : "");

        txtUsuario.setText(null != userIn.getNombreUsuario() ? userIn.getNombreUsuario().trim() : "");

        passwordField.setText(null != userIn.getPassword() ? userIn.getPassword().trim() : "");

        txtNombre.setText(null != userIn.getNombre() ? userIn.getNombre().trim() : "");

        txtApellido.setText(null != userIn.getApellidos() ? userIn.getApellidos().trim() : "");

        txtMail.setText(null != userIn.getCorreo() ? userIn.getCorreo().trim() : "");

        txtAddress.setText(null != userIn.getDireccion() ? userIn.getDireccion().trim() : "");

    }

}
