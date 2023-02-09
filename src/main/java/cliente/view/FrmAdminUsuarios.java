package cliente.view;

import cliente.logica.LogicaUsuario;
import cliente.model.Usuario;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class FrmAdminUsuarios {

    private JFrame frmListaDeUsuarios;
    private JTable table;

    private final Usuario user;

    public FrmAdminUsuarios(Usuario user) {
        createForm();
        this.user = user;
        frmListaDeUsuarios.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    frmListaDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    frmListaDeUsuarios.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        frmListaDeUsuarios.setVisible(true);

    }


    private void createForm() {

        frmListaDeUsuarios = new JFrame();
        frmListaDeUsuarios.setTitle("iParking - Lista de usuarios");
        //frmListaDeUsuarios.setIconImage(new ImageIcon(ImageIO.read(new File("src/main/resources/aparcamiento.png"))).getImage());
        frmListaDeUsuarios.setTitle("Lista de usuarios");
        frmListaDeUsuarios.setBounds(100, 100, 700, 500);
        frmListaDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmListaDeUsuarios.getContentPane().setLayout(new BorderLayout(0, 0));
        frmListaDeUsuarios.setLocationRelativeTo(null);
        frmListaDeUsuarios.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        frmListaDeUsuarios.getContentPane().add(menuBar, BorderLayout.NORTH);

        JMenu MenuSesion = new JMenu("Sesión");
        menuBar.add(MenuSesion);

        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
        itemCerrarSesion.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                frmListaDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frmListaDeUsuarios.dispose();
            }
        });
        MenuSesion.add(itemCerrarSesion);

        JPanel panelListaUsuarios = new JPanel();
        frmListaDeUsuarios.getContentPane().add(panelListaUsuarios);

        DefaultTableModel model = LogicaUsuario.getDefaultTableModel();
        panelListaUsuarios.setLayout(null);
        table = new JTable(model);
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(true);
        table.setBounds(new Rectangle(10, 24, 655, 32));
        table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelListaUsuarios.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(new Rectangle(10, 0, 666, 397));
        panelListaUsuarios.add(scrollPane);
        JPanel panelBotones = new JPanel();
        frmListaDeUsuarios.getContentPane().add(panelBotones, BorderLayout.SOUTH);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> {
            int filaSeleccionada = table.getSelectedRow();
            String idUsuario;
            if (filaSeleccionada != -1) {
                idUsuario = table.getValueAt(filaSeleccionada, 0).toString();
                try {
                    new FrmPerfil(true, LogicaUsuario.getUsuarioById(idUsuario));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un usuario para editar.");
            }
        });

        JButton btnRefresh = new JButton("Refrescar");
        btnRefresh.addActionListener(e -> table.setModel(LogicaUsuario.getDefaultTableModel()));
        panelBotones.add(btnRefresh);
        panelBotones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada != -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea borrar este usuario?", "Cuidado",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    LogicaUsuario.deleteUsuarioByDni(table.getValueAt(filaSeleccionada, 2).toString());
                } else {
                    frmListaDeUsuarios.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un usuario para eliminar.");
            }
        });
        btnEliminar.setForeground(new Color(255, 0, 4));
        panelBotones.add(btnEliminar);

        JSeparator separator = new JSeparator();
        panelBotones.add(separator);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            frmListaDeUsuarios.dispose();
            try {
                new FrmEleccionAdmin(user);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panelBotones.add(btnVolver);
    }
}
