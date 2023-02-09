package cliente.view;

import cliente.logica.LogicaParking;
import cliente.logica.LogicaVehiculo;
import cliente.model.Parking;
import cliente.model.Reserva;
import cliente.model.Usuario;
import cliente.model.Vehiculo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FrmParking extends JFrame {

    private final Usuario user;
    private JTable table;
    private JTextField txtIdPlaza;
    private JTextField txtTipoPlaza;
    private JTextField txtOcupado;
    private JTextField txtVehiculoMarca;
    private JTextField txtVehiculoTipo;
    private JTextField txtVehiculoAparcado;
    private JTextField txtVehiculoPlaza;
    private JTextField txtReservado;
    private Parking parking;

    private JButton btnReservar;

    public FrmParking(Usuario user, Parking parking) throws IOException {
        setTitle("Parking");
        this.user = user;
        this.parking = parking;
        createForm();
    }

    public void createForm() throws IOException {
        setTitle("iParking - Lista de parkings y plazas");
        setIconImage(new ImageIcon(ImageIO.read(new File("src/main/resources/aparcamiento.png"))).getImage());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        setBounds(100, 100, 736, 561);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JMenuBar menuBar = new JMenuBar();
        contentPane.add(menuBar, BorderLayout.NORTH);

        JMenu MenuSesion = new JMenu("Sesión");
        menuBar.add(MenuSesion);

        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
        itemCerrarSesion.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
        MenuSesion.add(itemCerrarSesion);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblParking = new JLabel("Parking");
        lblParking.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblParking.setHorizontalAlignment(SwingConstants.CENTER);
        lblParking.setBounds(184, 24, 146, 37);
        panel.add(lblParking);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 0, 0));
        separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(10, 72, 692, 2);
        panel.add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(new Color(0, 0, 0));
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setBounds(506, 78, 2, 301);
        panel.add(separator_1);

        JLabel lblPlaza = new JLabel("Plaza");
        lblPlaza.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlaza.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPlaza.setBounds(534, 24, 129, 37);
        panel.add(lblPlaza);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 0, 0)));
        table.setBounds(10, 85, 486, 255);
        table.getSelectionModel().addListSelectionListener(event -> updateOnClickByTable());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 115, 487, 264);
        scrollPane.setViewportView(table);
        panel.add(scrollPane);


        JSeparator separator_2 = new JSeparator();
        separator_2.setForeground(Color.BLACK);
        separator_2.setBackground(Color.BLACK);
        separator_2.setBounds(10, 390, 692, 2);
        panel.add(separator_2);

        JLabel lblNewLabel = new JLabel("Seleccione el vehicualo para realizar la reserva:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(68, 403, 238, 45);
        panel.add(lblNewLabel);

        JComboBox<String> comboVehiculo = new JComboBox<>();
        comboVehiculo.addActionListener(e -> updateOnClickByComboBoxVehiculo(comboVehiculo.getSelectedIndex()));
        comboVehiculo.setBounds(316, 414, 186, 22);
        panel.add(comboVehiculo);

        ComboBoxModel<String> model = new DefaultComboBoxModel<>(LogicaVehiculo.getMatriculas(user));
        comboVehiculo.setModel(model);


        JLabel lblNewLabel_1 = new JLabel("Nº Plaza:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(506, 95, 92, 14);
        panel.add(lblNewLabel_1);

        txtIdPlaza = new JTextField();
        txtIdPlaza.setEditable(false);
        txtIdPlaza.setHorizontalAlignment(SwingConstants.CENTER);
        txtIdPlaza.setBounds(608, 93, 94, 17);
        panel.add(txtIdPlaza);
        txtIdPlaza.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Tipo Plaza:");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setBounds(506, 123, 92, 14);
        panel.add(lblNewLabel_1_1);

        txtTipoPlaza = new JTextField();
        txtTipoPlaza.setEditable(false);
        txtTipoPlaza.setHorizontalAlignment(SwingConstants.CENTER);
        txtTipoPlaza.setColumns(10);
        txtTipoPlaza.setBounds(608, 121, 94, 17);
        panel.add(txtTipoPlaza);

        JLabel lblNewLabel_1_1_1 = new JLabel("Ocupado:");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setBounds(506, 151, 92, 14);
        panel.add(lblNewLabel_1_1_1);

        txtOcupado = new JTextField();
        txtOcupado.setEditable(false);
        txtOcupado.setHorizontalAlignment(SwingConstants.CENTER);
        txtOcupado.setColumns(10);
        txtOcupado.setBounds(608, 149, 94, 17);

        panel.add(txtOcupado);

        JLabel lblVehiculo = new JLabel("Vehículo");
        lblVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblVehiculo.setBounds(534, 227, 146, 37);
        panel.add(lblVehiculo);

        JSeparator separator_3 = new JSeparator();
        separator_3.setForeground(Color.BLACK);
        separator_3.setBackground(Color.BLACK);
        separator_3.setBounds(518, 225, 178, 12);
        panel.add(separator_3);

        JLabel lblNewLabel_1_2 = new JLabel("Marca:");
        lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2.setBounds(506, 269, 92, 14);
        panel.add(lblNewLabel_1_2);

        txtVehiculoMarca = new JTextField();
        txtVehiculoMarca.setEditable(false);
        txtVehiculoMarca.setHorizontalAlignment(SwingConstants.CENTER);
        txtVehiculoMarca.setColumns(10);
        txtVehiculoMarca.setBounds(608, 267, 94, 17);
        panel.add(txtVehiculoMarca);

        JLabel lblNewLabel_1_1_2 = new JLabel("Tipo:");
        lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_2.setBounds(506, 297, 92, 14);
        panel.add(lblNewLabel_1_1_2);

        txtVehiculoTipo = new JTextField();
        txtVehiculoTipo.setEditable(false);
        txtVehiculoTipo.setHorizontalAlignment(SwingConstants.CENTER);
        txtVehiculoTipo.setColumns(10);
        txtVehiculoTipo.setBounds(608, 295, 94, 17);
        panel.add(txtVehiculoTipo);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Aparcado:");
        lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1_1.setBounds(506, 325, 92, 14);
        panel.add(lblNewLabel_1_1_1_1);

        txtVehiculoAparcado = new JTextField();
        txtVehiculoAparcado.setEditable(false);
        txtVehiculoAparcado.setHorizontalAlignment(SwingConstants.CENTER);
        txtVehiculoAparcado.setColumns(10);
        txtVehiculoAparcado.setBounds(608, 323, 94, 17);
        panel.add(txtVehiculoAparcado);

        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Plaza asignada:");
        lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1_1_1.setBounds(506, 350, 103, 16);
        panel.add(lblNewLabel_1_1_1_1_1);

        txtVehiculoPlaza = new JTextField();
        txtVehiculoPlaza.setEditable(false);
        txtVehiculoPlaza.setHorizontalAlignment(SwingConstants.CENTER);
        txtVehiculoPlaza.setColumns(10);
        txtVehiculoPlaza.setBounds(608, 350, 94, 17);
        panel.add(txtVehiculoPlaza);

        JLabel lblReservado = new JLabel("Reservado:");
        lblReservado.setHorizontalAlignment(SwingConstants.CENTER);
        lblReservado.setBounds(506, 178, 92, 14);
        panel.add(lblReservado);

        txtReservado = new JTextField();
        txtReservado.setHorizontalAlignment(SwingConstants.CENTER);
        txtReservado.setEditable(false);
        txtReservado.setColumns(10);
        txtReservado.setBounds(608, 176, 94, 17);
        panel.add(txtReservado);

        JLabel lblSeleccion = new JLabel("Selecciona un parking:");
        lblSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccion.setBounds(20, 85, 164, 19);
        panel.add(lblSeleccion);

        JComboBox<String> comboParkings = new JComboBox<>();
        comboParkings.addActionListener(e -> updateTableByComboBoxParking(Objects.requireNonNull(comboParkings.getSelectedItem())));
        comboParkings.setBounds(194, 82, 238, 22);
        panel.add(comboParkings);

        DefaultComboBoxModel<String> modelComboParking = new DefaultComboBoxModel<>(LogicaParking.getNombres());
        comboParkings.setModel(modelComboParking);

        JPanel panelBotones = new JPanel();
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        JButton btnEditar = new JButton("Lista reserva");
        btnEditar.addActionListener(e -> {
            try {
                new FrmListaReservas(user, parking);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panelBotones.add(btnEditar);

        btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(e -> {
            if (checkReserva()) {
                try {
                    new FrmReserva(table.getSelectedRow(), new Reserva(), parking, user, comboVehiculo.getSelectedIndex());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                comboParkings.setModel(new DefaultComboBoxModel<>(LogicaParking.getNombres()));
            }
        });
        panelBotones.add(btnReservar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                try {
                    new FrmEleccion(user);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        panelBotones.add(btnVolver);

        setVisible(true);
    }

    private boolean checkReserva() {
        boolean bExito = true;
        String tipoMembresia = user.getMembresia();
        String tipoAparcamiento = table.getValueAt(table.getSelectedRow(), 1).toString();
        if (tipoMembresia.equalsIgnoreCase("Free")) {
            bExito = false;
            JOptionPane.showMessageDialog(null, "No tienes una membresía que permita reservar.");
        } else if (!tipoAparcamiento.equalsIgnoreCase(txtVehiculoTipo.getText())) {
            bExito = false;
            JOptionPane.showMessageDialog(null, "El vehículo no puede aparcar en esta plaza.");
        }
        return bExito;
    }

    private void updateOnClickByTable() {
        txtIdPlaza.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
        txtTipoPlaza.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
        String inOcupado = table.getValueAt(table.getSelectedRow(), 2).toString();
        if (inOcupado.equals("Ocupada")) {
            txtOcupado.setForeground(Color.red);
            btnReservar.setEnabled(false);
        } else {
            txtOcupado.setForeground(new Color(30, 124, 58));
            btnReservar.setEnabled(true);
        }
        txtOcupado.setText(inOcupado);
        String inReservado = table.getValueAt(table.getSelectedRow(), 3).toString();
        if (inReservado.equals("No disponible")) {
            txtReservado.setForeground(Color.red);

        } else {
            txtReservado.setForeground(new Color(30, 124, 58));
        }
        txtReservado.setText(inReservado);
    }

    private void updateTableByComboBoxParking(Object selectedItem) {
        parking = LogicaParking.getParking(selectedItem.toString());
        table.setModel(LogicaParking.getDefaultTableModel(selectedItem.toString()));
    }

    private void updateOnClickByComboBoxVehiculo(int comboIndex) {
        Vehiculo vehiculo = user.getListaVehiculos()[comboIndex];
        updateTxtVehiculo(vehiculo);
    }

    private void updateTxtVehiculo(Vehiculo pickedVehiculo) {
        txtVehiculoMarca.setText(pickedVehiculo.getMarca());
        txtVehiculoTipo.setText(pickedVehiculo.getTipoVehiculo() == 2 ? "Moto" : "Coche");
        txtVehiculoAparcado.setText(pickedVehiculo.isEstacionado() ? "Sí" : "No");
        if (pickedVehiculo.isEstacionado()) {
            txtVehiculoPlaza.setText(pickedVehiculo.getId_plaza());
        } else {
            txtVehiculoPlaza.setText("N/A");
        }
    }

}
