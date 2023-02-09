package cliente.view;

import cliente.logica.LogicaUsuario;
import cliente.model.Usuario;
import cliente.model.Vehiculo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class FrmVehiculo extends JDialog {

    private JTextField txtMatricula;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtPlaza;
    private JTextField txtTipo;
    private JTextField txtEstacionado;
    private Usuario user;
    private final int indexVehiculo;
    private final boolean add;
    private final Vehiculo vehiculoIn;


    public FrmVehiculo(Usuario user, int indexVehiculo, boolean add) throws IOException {
        this.add = add;
        this.user = user;
        this.indexVehiculo = indexVehiculo;
        this.vehiculoIn = indexVehiculo >= 0 ? user.getListaVehiculos()[indexVehiculo] : new Vehiculo();
        createFrom();
        rellenarCampos();
        setVisible(true);

    }


    public void createFrom() throws IOException {

        setTitle("iParking - Información del vehículo");
        setIconImage(new ImageIcon(ImageIO.read(new File("src/main/resources/aparcamiento.png"))).getImage());
        setModal(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        setBounds(100, 100, 595, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JMenuBar menuBar = new JMenuBar();
        contentPane.add(menuBar, BorderLayout.NORTH);

        JMenu mnSesin = new JMenu("Sesión");
        menuBar.add(mnSesin);

        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
        itemCerrarSesion.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
        mnSesin.add(itemCerrarSesion);

        JPanel panelBotones = new JPanel();
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> {
            if (checkCampos()) {
                vehiculoIn.setMatricula(txtMatricula.getText().trim());
                vehiculoIn.setTipoVehiculo(txtTipo.getText().trim().equalsIgnoreCase("coche") ? 1 : 2);
                vehiculoIn.setEstacionado(txtEstacionado.getText().trim().equalsIgnoreCase("si"));
                vehiculoIn.setMarca(txtMarca.getText().trim());
                vehiculoIn.setModelo(txtModelo.getText().trim());
                vehiculoIn.setId_plaza(txtPlaza.getText().trim());
                user = LogicaUsuario.updateUsuario(LogicaUsuario.updateVehiculos(user, vehiculoIn, indexVehiculo, add));
                JOptionPane.showMessageDialog(null, "Cambios guardados con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "Comprueba los campos.");
            }
        });
        panelBotones.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> dispose());
        panelBotones.add(btnVolver);

        JPanel panelEdicion = new JPanel();
        contentPane.add(panelEdicion);
        panelEdicion.setLayout(null);

        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
        lblMatricula.setBounds(24, 58, 100, 26);
        panelEdicion.add(lblMatricula);

        JLabel lblTitulo = new JLabel("DATOS DEL VEHICULO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(220, 11, 140, 26);
        panelEdicion.add(lblTitulo);

        txtMatricula = new JTextField();
        txtMatricula.setBounds(134, 61, 123, 20);
        panelEdicion.add(txtMatricula);
        txtMatricula.setColumns(10);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
        lblMarca.setBounds(24, 95, 100, 26);
        panelEdicion.add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setColumns(10);
        txtMarca.setBounds(134, 98, 123, 20);
        panelEdicion.add(txtMarca);


        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
        lblModelo.setBounds(24, 132, 100, 26);
        panelEdicion.add(lblModelo);

        txtModelo = new JTextField();
        txtModelo.setColumns(10);
        txtModelo.setBounds(134, 135, 123, 20);
        panelEdicion.add(txtModelo);


        JSeparator separator = new JSeparator();
        separator.setBounds(24, 37, 521, 2);
        panelEdicion.add(separator);

        JLabel lblIdPlaza = new JLabel("Plaza asiganada:");
        lblIdPlaza.setHorizontalAlignment(SwingConstants.CENTER);
        lblIdPlaza.setBounds(312, 58, 100, 26);
        panelEdicion.add(lblIdPlaza);

        txtPlaza = new JTextField();
        txtPlaza.setColumns(10);
        txtPlaza.setBounds(422, 61, 123, 20);
        panelEdicion.add(txtPlaza);
        txtPlaza.setText(null != vehiculoIn.getId_plaza() ? vehiculoIn.getId_plaza() : "");


        JLabel lblTipoDeVehculo = new JLabel("Tipo de Vehículo:");
        lblTipoDeVehculo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoDeVehculo.setBounds(312, 95, 100, 26);
        panelEdicion.add(lblTipoDeVehculo);

        txtTipo = new JTextField();
        txtTipo.setColumns(10);
        txtTipo.setBounds(422, 98, 123, 20);
        panelEdicion.add(txtTipo);


        txtEstacionado = new JTextField();
        txtEstacionado.setColumns(10);
        txtEstacionado.setBounds(422, 135, 123, 20);
        panelEdicion.add(txtEstacionado);


        JLabel lblEstacionado = new JLabel("Estacionado:");
        lblEstacionado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstacionado.setBounds(312, 132, 100, 26);
        panelEdicion.add(lblEstacionado);
    }

    private void rellenarCampos() {
        if (!user.getMembresia().equalsIgnoreCase("premium")){
            txtPlaza.setEnabled(false);
        }
        txtMatricula.setText(null != vehiculoIn.getMatricula() ? vehiculoIn.getMatricula() : "");

        txtMarca.setText(null != vehiculoIn.getMarca() ? vehiculoIn.getMarca() : "");

        txtModelo.setText(null != vehiculoIn.getModelo() ? vehiculoIn.getModelo() : "");

        switch (vehiculoIn.getTipoVehiculo()) {
            case 1:
                txtTipo.setText("Coche");
                break;
            case 2:
                txtTipo.setText("Moto");
                break;
            default:
                txtTipo.setText("");
        }
        txtEstacionado.setText(vehiculoIn.isEstacionado() ? "Si" : "No");

    }


    private boolean checkCampos() {
        return txtMatricula.getText().trim().length() > 0 && txtTipo.getText().trim().length() > 0 && txtEstacionado.getText().trim().length() > 0 && txtMarca.getText().trim().length() > 0 && txtModelo.getText().trim().length() > 0 && (txtEstacionado.getText().trim().equalsIgnoreCase("si") || txtEstacionado.getText().trim().equalsIgnoreCase("no"));
    }


}
