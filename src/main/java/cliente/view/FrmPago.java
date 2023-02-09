package cliente.view;

import cliente.logica.LogicaUsuario;
import cliente.model.Usuario;
import com.toedter.components.JSpinField;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FrmPago extends JFrame {

    private JComboBox<String> comboBoxMensualidades;
    private JComboBox<String> comboBoxTipoMembresia;
    private Double precio;
    private final String[] listaMembresias = {"Básica", "Trabajador", "Premium"};
    private final String[] listaPago = {"Mensual", "Semestral", "Anual"};
    private final String infoMembresia = "Básica: podrás reservar por un maximo de 3 horas. \n" +
            "Trabajador: podrás reservar por un maximo de 10 horas. \n" +
            "Premium: podrás reservar sin limite de tiempo en el mismo día y poder asignarte una plaza para cada vehículo. \n" +
            "Nota: Solo se puede reservar una vez por vehículo.";
    private final Map<String, Double> mapPagos = new HashMap<String, Double>() {{
        put("00", 4.99);
        put("01", 24.99);
        put("02", 49.99);
        put("10", 9.99);
        put("11", 49.99);
        put("12", 99.99);
        put("20", 19.99);
        put("21", 99.99);
        put("22", 199.99);

    }};
    private JTextField txtPrecio;
    private final Usuario userLogged;
    private JTextField txtTitular;
    private JTextField txtNumTarjeta;
    private JTextField txtCCV;

    public FrmPago(Usuario userLogged) throws IOException {
        this.userLogged = userLogged;

        createForm();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    dispose();
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        setVisible(true);

    }

    /**
     * Create the frame.
     */
    public void createForm() throws IOException {
        setTitle("iParking - Pago de membresía");
        setIconImage(new ImageIcon(ImageIO.read(new File("src/main/resources/aparcamiento.png"))).getImage());

        setTitle("Formulario de pago");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 471, 445);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuSesion = new JMenu("Sesión");
        menuBar.add(menuSesion);

        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
        itemCerrarSesion.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
        menuSesion.add(itemCerrarSesion);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelEleccion = new JPanel();
        panelEleccion.setBounds(10, 44, 437, 100);
        panelEleccion.setLayout(null);
        contentPane.add(panelEleccion);

        JLabel lblSeleccionaElTipo = new JLabel("Selecciona el tipo de membresía:");
        lblSeleccionaElTipo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccionaElTipo.setBounds(10, 13, 218, 18);
        panelEleccion.add(lblSeleccionaElTipo);

        comboBoxTipoMembresia = new JComboBox<>();
        comboBoxTipoMembresia.setBounds(238, 11, 111, 20);
        panelEleccion.add(comboBoxTipoMembresia);
        comboBoxTipoMembresia.setModel(new DefaultComboBoxModel<>(listaMembresias));

        JButton btnNewButton = new JButton("?");
        btnNewButton.addActionListener(e -> JOptionPane.showMessageDialog(null, infoMembresia));
        btnNewButton.setBounds(359, 10, 68, 23);
        panelEleccion.add(btnNewButton);

        JLabel lblCoste = new JLabel("Peridiocidad de pago:");
        lblCoste.setHorizontalAlignment(SwingConstants.CENTER);
        lblCoste.setBounds(10, 39, 191, 21);
        panelEleccion.add(lblCoste);

        comboBoxMensualidades = new JComboBox<>();
        comboBoxMensualidades.addActionListener(e -> calcularPrecio());
        comboBoxMensualidades.setBounds(238, 40, 111, 20);
        panelEleccion.add(comboBoxMensualidades);
        comboBoxMensualidades.setModel(new DefaultComboBoxModel<>(listaPago));

        JLabel lblTotalAPagar = new JLabel("Total a pagar:");
        lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalAPagar.setBounds(20, 71, 191, 18);
        panelEleccion.add(lblTotalAPagar);

        txtPrecio = new JTextField();
        txtPrecio.setEditable(false);
        txtPrecio.setBounds(238, 71, 111, 18);
        panelEleccion.add(txtPrecio);
        txtPrecio.setColumns(10);

        JLabel lblNewLabel = new JLabel("Datos de Membresía");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 437, 22);
        contentPane.add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBounds(10, 155, 437, 191);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Datos de Pago");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setEnabled(true);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(10, 11, 407, 25);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Titular tarjeta:");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(20, 47, 135, 25);
        panel.add(lblNewLabel_2);

        txtTitular = new JTextField();
        txtTitular.setBounds(165, 52, 252, 20);
        panel.add(txtTitular);
        txtTitular.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Nº tarjeta:");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setBounds(20, 91, 135, 25);
        panel.add(lblNewLabel_2_1);

        txtNumTarjeta = new JTextField();
        txtNumTarjeta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.setKeyChar((char) 0);
                }
            }
        });
        txtNumTarjeta.setColumns(10);
        txtNumTarjeta.setBounds(165, 96, 252, 20);
        panel.add(txtNumTarjeta);

        JLabel lblNewLabel_2_1_1 = new JLabel("CCV:");
        lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1.setBounds(292, 140, 42, 20);
        panel.add(lblNewLabel_2_1_1);

        txtCCV = new JTextField();
        txtCCV.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.setKeyChar((char) 0);
                }
            }
        });
        txtCCV.setColumns(10);
        txtCCV.setBounds(344, 140, 73, 20);
        panel.add(txtCCV);

        JLabel lblNewLabel_2_1_1_1 = new JLabel("Fecha vencimiento:");
        lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1_1.setBounds(55, 140, 104, 25);
        panel.add(lblNewLabel_2_1_1_1);


        JSpinField spinYear = new JSpinField();
        spinYear.setBounds(228, 140, 42, 20);
        spinYear.setMaximum(99);
        spinYear.setMinimum(23);
        panel.add(spinYear);

        JSpinField spinField = new JSpinField();
        spinField.setBounds(174, 140, 42, 20);
        spinField.setMaximum(12);
        spinField.setMinimum(1);
        panel.add(spinField);

        JLabel lblNewLabel_3 = new JLabel("/");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(199, 140, 47, 20);
        panel.add(lblNewLabel_3);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 0, 418, 10);
        panel.add(separator);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> dispose());
        btnVolver.setBounds(333, 357, 89, 23);
        contentPane.add(btnVolver);

        JButton btnPagar = new JButton("Pagar");
        btnPagar.addActionListener(e -> {
            if (checkCampos()) {
                LogicaUsuario.pagoMembresia(userLogged, Objects.requireNonNull(comboBoxTipoMembresia.getSelectedItem()).toString(), Objects.requireNonNull(comboBoxMensualidades.getSelectedItem()).toString());
                JOptionPane.showMessageDialog(null,"Membresía actualizada.");
            }else {
                JOptionPane.showMessageDialog(null, "Rellena todos los datos.");
            }
        });
        btnPagar.setBounds(168, 357, 89, 23);
        contentPane.add(btnPagar);

    }

    private boolean checkCampos() {
        return comboBoxTipoMembresia.getSelectedIndex() > -1 && comboBoxMensualidades.getSelectedIndex() > -1 && precio > 0 && txtCCV.getText().length() == 3 && txtNumTarjeta.getText().length() == 16 && txtTitular.getText().length() > 0;
    }

    private void calcularPrecio() {
        int indexMensualidad = comboBoxMensualidades.getSelectedIndex();
        int indexTipoMembresia = comboBoxTipoMembresia.getSelectedIndex();
        if (indexMensualidad != -1 && indexTipoMembresia != -1) {
            String indexMap = indexTipoMembresia + String.valueOf(indexMensualidad);
            precio = mapPagos.get(indexMap);
            txtPrecio.setText(precio + " €");
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona todos los datos. ");
        }
    }
}
