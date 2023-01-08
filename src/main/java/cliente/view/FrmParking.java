package cliente.view;

import cliente.logica.LogicaUsuario;
import cliente.logica.LogicaVehiculo;
import cliente.model.Usuario;
import cliente.model.Vehiculo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmParking extends JFrame {

    private JPanel contentPane;

    private Usuario user;
    private JTable table;
    private JTextField txtIdPlaza;
    private JTextField txtTipoPlaza;
    private JTextField txtOcupado;
    private JTextField txtVehiculoMarca;
    private JTextField txtVehiculoTipo;
    private JTextField txtVehiculoAparcado;
    private JTextField txtVehiculoPlaza;


    public FrmParking(Usuario user) {
    	setTitle("Parking");
        this.user = user;
        createForm();
    }

    public void createForm() {
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
        setBounds(100, 100, 702, 522);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JMenuBar menuBar = new JMenuBar();
        contentPane.add(menuBar, BorderLayout.NORTH);

        JMenu MenuSesion = new JMenu("Sesión");
        menuBar.add(MenuSesion);

        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
        MenuSesion.add(itemCerrarSesion);

        JMenu MenuAyuda = new JMenu("Ayuda");
        menuBar.add(MenuAyuda);

        JMenuItem itemAyuda = new JMenuItem("Ayuda");
        MenuAyuda.add(itemAyuda);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblParking = new JLabel("Parking");
        lblParking.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblParking.setHorizontalAlignment(SwingConstants.CENTER);
        lblParking.setBounds(184, 24, 146, 37);
        panel.add(lblParking);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 0, 0));
        separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(10, 72, 658, 2);
        panel.add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(new Color(0, 0, 0));
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setBounds(506, 78, 2, 262);
        panel.add(separator_1);

        JLabel lblPlaza = new JLabel("Plaza");
        lblPlaza.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlaza.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblPlaza.setBounds(522, 24, 129, 37);
        panel.add(lblPlaza);

        table = new JTable();
        table.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
        table.setCellSelectionEnabled(true);
        table.setBounds(10, 85, 486, 255);
        //panel.add(table);

        JSeparator separator_2 = new JSeparator();
        separator_2.setForeground(Color.BLACK);
        separator_2.setBackground(Color.BLACK);
        separator_2.setBounds(10, 351, 658, 2);
        panel.add(separator_2);

        JLabel lblNewLabel = new JLabel("Vehiculo que desea aparcar o mover:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(68, 364, 238, 45);
        panel.add(lblNewLabel);

        JComboBox<String> comboVehiculo = new JComboBox<>();
        comboVehiculo.addActionListener(e -> updateOnClickByComboBox(comboVehiculo.getSelectedIndex()));
        comboVehiculo.setBounds(316, 375, 186, 22);
        panel.add(comboVehiculo);
        ComboBoxModel<String> model = new DefaultComboBoxModel<>(LogicaVehiculo.getMatriculas(user));
        comboVehiculo.setModel(model);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 85, 487, 255);
        scrollPane.setViewportView(table);
        panel.add(scrollPane);
        
        JLabel lblNewLabel_1 = new JLabel("Nº Plaza:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(522, 95, 64, 14);
        panel.add(lblNewLabel_1);
        
        txtIdPlaza = new JTextField();
        txtIdPlaza.setEditable(false);
        txtIdPlaza.setHorizontalAlignment(SwingConstants.CENTER);
        txtIdPlaza.setBounds(596, 93, 72, 17);
        panel.add(txtIdPlaza);
        txtIdPlaza.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Tipo Plaza:");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setBounds(522, 123, 64, 14);
        panel.add(lblNewLabel_1_1);
        
        txtTipoPlaza = new JTextField();
        txtTipoPlaza.setEditable(false);
        txtTipoPlaza.setHorizontalAlignment(SwingConstants.CENTER);
        txtTipoPlaza.setColumns(10);
        txtTipoPlaza.setBounds(596, 121, 72, 17);
        panel.add(txtTipoPlaza);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Ocupado:");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setBounds(518, 151, 68, 14);
        panel.add(lblNewLabel_1_1_1);
        
        txtOcupado = new JTextField();
        txtOcupado.setEditable(false);
        txtOcupado.setHorizontalAlignment(SwingConstants.CENTER);
        txtOcupado.setColumns(10);
        txtOcupado.setBounds(596, 149, 72, 17);
        panel.add(txtOcupado);
        
        JLabel lblVehiculo = new JLabel("Vehículo");
        lblVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblVehiculo.setBounds(522, 188, 146, 37);
        panel.add(lblVehiculo);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setForeground(Color.BLACK);
        separator_3.setBackground(Color.BLACK);
        separator_3.setBounds(512, 186, 156, 2);
        panel.add(separator_3);
        
        JLabel lblNewLabel_1_2 = new JLabel("Marca:");
        lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2.setBounds(522, 230, 64, 14);
        panel.add(lblNewLabel_1_2);
        
        txtVehiculoMarca = new JTextField();
        txtVehiculoMarca.setEditable(false);
        txtVehiculoMarca.setHorizontalAlignment(SwingConstants.CENTER);
        txtVehiculoMarca.setColumns(10);
        txtVehiculoMarca.setBounds(596, 228, 72, 17);
        panel.add(txtVehiculoMarca);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Tipo:");
        lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_2.setBounds(522, 258, 64, 14);
        panel.add(lblNewLabel_1_1_2);
        
        txtVehiculoTipo = new JTextField();
        txtVehiculoTipo.setEditable(false);
        txtVehiculoTipo.setHorizontalAlignment(SwingConstants.CENTER);
        txtVehiculoTipo.setColumns(10);
        txtVehiculoTipo.setBounds(596, 256, 72, 17);
        panel.add(txtVehiculoTipo);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Aparcado:");
        lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1_1.setBounds(518, 286, 68, 14);
        panel.add(lblNewLabel_1_1_1_1);
        
        txtVehiculoAparcado = new JTextField();
        txtVehiculoAparcado.setEditable(false);
        txtVehiculoAparcado.setHorizontalAlignment(SwingConstants.CENTER);
        txtVehiculoAparcado.setColumns(10);
        txtVehiculoAparcado.setBounds(596, 284, 72, 17);
        panel.add(txtVehiculoAparcado);
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Plaza asignada:");
        lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1_1_1.setBounds(506, 311, 91, 16);
        panel.add(lblNewLabel_1_1_1_1_1);
        
        txtVehiculoPlaza = new JTextField();
        txtVehiculoPlaza.setEditable(false);
        txtVehiculoPlaza.setHorizontalAlignment(SwingConstants.CENTER);
        txtVehiculoPlaza.setColumns(10);
        txtVehiculoPlaza.setBounds(596, 311, 72, 17);
        panel.add(txtVehiculoPlaza);

        JPanel panelBotones = new JPanel();
        contentPane.add(panelBotones, BorderLayout.SOUTH);
        
        JButton btnDesaparcar = new JButton("Desaparcar");
        btnDesaparcar.addActionListener(e -> {
            if (user.getListaVehiculos()[comboVehiculo.getSelectedIndex()].isEstacionado()){
                desaparcarVehiculo(comboVehiculo.getSelectedIndex());
            }else{
                JOptionPane.showMessageDialog(null,"El vehiculo no se encuentra en el garaje.");
            }
        });
        panelBotones.add(btnDesaparcar);

        JButton btnAparcar = new JButton("Aparcar");
        panelBotones.add(btnAparcar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                new FrmEleccion(user);
                dispose();
            }
        });
        panelBotones.add(btnVolver);

        setVisible(true);
    }

    private void updateOnClickByComboBox(int comboIndex){
        Vehiculo pickedVehiculo = user.getListaVehiculos()[comboIndex];

        updateTxtVehiculo(pickedVehiculo);

    }

    private void updateTxtVehiculo(Vehiculo pickedVehiculo) {
        txtVehiculoMarca.setText(pickedVehiculo.getMarca());
        txtVehiculoTipo.setText(pickedVehiculo.getTipoVehiculo()==2?"Moto":"Coche");
        txtVehiculoAparcado.setText(pickedVehiculo.isEstacionado()?"Sí":"No");
        if (pickedVehiculo.isEstacionado()){
            txtVehiculoPlaza.setText(pickedVehiculo.getId_plaza());
        }else {
            txtVehiculoPlaza.setText("N/A");
        }
    }

    private void desaparcarVehiculo(int comboIndex){
        Vehiculo pickedVehiculo = user.getListaVehiculos()[comboIndex];
        pickedVehiculo.setEstacionado(false);
        pickedVehiculo.setId_plaza("N/A");

        LogicaUsuario.updateUsuario(user);
        updateTxtVehiculo(pickedVehiculo);

    }
}
