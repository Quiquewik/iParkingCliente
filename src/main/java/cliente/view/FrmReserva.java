package cliente.view;

import cliente.logica.LogicaReserva;
import cliente.model.Parking;
import cliente.model.Plaza;
import cliente.model.Reserva;
import cliente.model.Usuario;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class FrmReserva extends JDialog {

    private JTextField txtNumeroPlaza;
    private JTextField txtTipoPlaza;
    private JTextField txtMatricula;
    private JTextField txtTipoVehiculo;
    private JTextField txtDNI;
    private JTextField txtTipoMembresia;

    private JSpinField spinMinutosFin;
    private JSpinField spinHoraFin;
    private JSpinField spinMinutosInicio;
    private JSpinField spinHoraInicio;
    private JDateChooser fechaReserva;
    private final int indexTabla;

    private final Reserva reserva;
    private final Parking parking;
    private final Usuario user;
    private final Plaza plaza;
    private final int indexVehiculo;

    public FrmReserva(int indexTabla, Reserva reserva, Parking parking, Usuario user, int indexVehiculo) throws IOException {
        this.reserva = reserva;
        this.indexTabla = indexTabla;
        this.parking = parking;
        this.user = user;
        this.plaza = parking.getPlazas()[indexTabla];
        this.indexVehiculo = indexVehiculo;
        createForm();
        rellenarcampos();
        setVisible(true);
    }


    /**
     * Create the frame.
     */
    public void createForm() throws IOException {

        setTitle("iParking - Reserva");
        setIconImage(new ImageIcon(ImageIO.read(new File("src/main/resources/aparcamiento.png"))).getImage());

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
        setModal(true);
        setResizable(false);
        setTitle("Reserva");
        setBounds(100, 100, 455, 380);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Reserva");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 409, 33);
        panel.add(lblNewLabel);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            if (checkCampos()) {
                setDates();
                if (LogicaReserva.addReserva(reserva,user,parking,indexTabla,indexVehiculo)){
                    JOptionPane.showMessageDialog(null,"Reserva guardada");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"No se ha podido guardar la reserva.");
                }
            }
        });
        btnGuardar.setBounds(62, 274, 107, 33);
        panel.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        btnCancelar.setBounds(291, 274, 102, 33);
        panel.add(btnCancelar);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBackground(new Color(0, 0, 0));
        separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(213, 65, 2, 191);
        panel.add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(Color.BLACK);
        separator_1.setBackground(Color.BLACK);
        separator_1.setBounds(10, 52, 409, 14);
        panel.add(separator_1);

        JLabel lblNewLabel_1 = new JLabel("Plaza");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(10, 75, 193, 14);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Vehículo");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setBounds(225, 77, 194, 14);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_2 = new JLabel("Numero plaza:");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(10, 100, 89, 20);
        panel.add(lblNewLabel_2);

        txtNumeroPlaza = new JTextField();
        txtNumeroPlaza.setBounds(109, 100, 94, 20);
        panel.add(txtNumeroPlaza);
        txtNumeroPlaza.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Tipo plaza:");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setBounds(10, 131, 89, 20);
        panel.add(lblNewLabel_2_1);

        txtTipoPlaza = new JTextField();
        txtTipoPlaza.setColumns(10);
        txtTipoPlaza.setBounds(109, 131, 94, 20);
        panel.add(txtTipoPlaza);

        fechaReserva = new JDateChooser();
        fechaReserva.setBounds(109, 162, 94, 20);
        panel.add(fechaReserva);

        JLabel lblNewLabel_2_2_1 = new JLabel("Fecha reserva:");
        lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2_1.setBounds(10, 162, 89, 20);
        panel.add(lblNewLabel_2_2_1);

        spinHoraInicio = new JSpinField();
        spinHoraInicio.setBounds(117, 196, 40, 20);
        spinHoraInicio.setMaximum(23);
        spinHoraInicio.setMinimum(0);
        panel.add(spinHoraInicio);

        spinMinutosInicio = new JSpinField();
        spinMinutosInicio.setBounds(158, 196, 40, 20);
        spinMinutosInicio.setMaximum(59);
        spinMinutosInicio.setMinimum(0);
        panel.add(spinMinutosInicio);

        spinHoraFin = new JSpinField();
        spinHoraFin.setBounds(117, 226, 40, 20);
        spinHoraFin.setMaximum(23);
        spinHoraFin.setMinimum(0);
        panel.add(spinHoraFin);

        spinMinutosFin = new JSpinField();
        spinMinutosFin.setBounds(158, 226, 40, 20);
        spinMinutosFin.setMaximum(59);
        spinMinutosFin.setMinimum(0);
        panel.add(spinMinutosFin);

        JLabel lblNewLabel_2_2_1_1 = new JLabel("Hora inicio:");
        lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2_1_1.setBounds(10, 196, 89, 20);
        panel.add(lblNewLabel_2_2_1_1);

        JLabel lblNewLabel_2_2_1_2 = new JLabel("Hora fin:");
        lblNewLabel_2_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2_1_2.setBounds(10, 226, 89, 20);
        panel.add(lblNewLabel_2_2_1_2);

        JLabel lblNewLabel_2_3 = new JLabel("Matricula:");
        lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3.setBounds(226, 102, 89, 20);
        panel.add(lblNewLabel_2_3);

        txtMatricula = new JTextField();
        txtMatricula.setColumns(10);
        txtMatricula.setBounds(333, 102, 86, 20);
        panel.add(txtMatricula);

        JLabel lblNewLabel_2_3_1 = new JLabel("Tipo vehículo:");
        lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3_1.setBounds(225, 131, 89, 20);
        panel.add(lblNewLabel_2_3_1);

        txtTipoVehiculo = new JTextField();
        txtTipoVehiculo.setColumns(10);
        txtTipoVehiculo.setBounds(332, 131, 86, 20);
        panel.add(txtTipoVehiculo);

        JLabel lblNewLabel_1_1_1 = new JLabel("Usuario");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setBounds(225, 162, 194, 14);
        panel.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_2_3_2 = new JLabel("DNI:");
        lblNewLabel_2_3_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3_2.setBounds(225, 186, 89, 20);
        panel.add(lblNewLabel_2_3_2);

        txtDNI = new JTextField();
        txtDNI.setColumns(10);
        txtDNI.setBounds(332, 186, 86, 20);
        panel.add(txtDNI);

        JLabel lblNewLabel_2_3_3 = new JLabel("Membresía:");
        lblNewLabel_2_3_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3_3.setBounds(226, 220, 89, 20);
        panel.add(lblNewLabel_2_3_3);

        txtTipoMembresia = new JTextField();
        txtTipoMembresia.setColumns(10);
        txtTipoMembresia.setBounds(333, 220, 86, 20);
        panel.add(txtTipoMembresia);

        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setForeground(Color.BLACK);
        separator_1_1.setBackground(Color.BLACK);
        separator_1_1.setBounds(10, 267, 409, 14);
        panel.add(separator_1_1);

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

    }

    private void rellenarcampos() {
        //Plaza
        txtNumeroPlaza.setText(plaza.getNumeroPlaza());
        txtTipoPlaza.setText(plaza.getTipoPlaza() == 1 ? "coche" : "moto");
        //Vehiculo
        txtMatricula.setText(user.getListaVehiculos()[indexVehiculo].getMatricula());
        txtTipoVehiculo.setText(user.getListaVehiculos()[indexVehiculo].getTipoVehiculo() == 1 ? "coche" : "moto");
        //User
        txtDNI.setText(user.getDni());
        txtTipoMembresia.setText(user.getMembresia());

    }

    private boolean checkCampos() {
        return fechaReserva.getDate() != null && spinMinutosFin.getValue() >= 0 && spinHoraFin.getValue() >= 0 && spinHoraInicio.getValue() >= 0 && spinMinutosInicio.getValue() >= 0;
    }

    private void setDates() {
        Calendar inicioReserva = Calendar.getInstance();
        Calendar finReserva = Calendar.getInstance();

        if (fechaReserva.getDate() != null) {
            inicioReserva.setTime(fechaReserva.getDate());
            inicioReserva.set(Calendar.HOUR_OF_DAY, 0);
            inicioReserva.set(Calendar.MINUTE, 0);
            inicioReserva.add(Calendar.HOUR_OF_DAY, spinHoraInicio.getValue());
            inicioReserva.add(Calendar.MINUTE, spinMinutosInicio.getValue());

            reserva.setHoraReserva(inicioReserva.getTime());

            finReserva.setTime(fechaReserva.getDate());
            finReserva.set(Calendar.HOUR_OF_DAY, 0);
            finReserva.set(Calendar.MINUTE, 0);
            finReserva.add(Calendar.HOUR_OF_DAY, spinHoraFin.getValue());
            finReserva.add(Calendar.MINUTE, spinMinutosFin.getValue());

            reserva.setFinReserva(finReserva.getTime());
        }


    }
}
