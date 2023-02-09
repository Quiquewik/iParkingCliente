package cliente.view;

import cliente.model.Usuario;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class FrmEleccionAdmin {

    private JFrame frame;
    private final Usuario user;


    public FrmEleccionAdmin(Usuario user) throws IOException {
        this.user = user;

        createFrom();
    }


    private void createFrom() throws IOException {
        frame = new JFrame();
        frame.setTitle("iParking - Menú administrador");
        frame.setIconImage(new ImageIcon(ImageIO.read(new File("src/main/resources/aparcamiento.png"))).getImage());
        frame.setBounds(100, 100, 450, 213);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        frame.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);

        JMenu MenuSesion = new JMenu("Sesión");
        menuBar.add(MenuSesion);

        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
        itemCerrarSesion.addActionListener(e -> {
        	if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.dispose();
			}
        });
        MenuSesion.add(itemCerrarSesion);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        frame.getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(2, 1, 0, 0));

        JLabel lblSeleccion = new JLabel("Selecciona la pantalla a la que quieres ir:");
        lblSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(lblSeleccion);

        JButton btnDatosUsuarios = new JButton("Gestionar Usuarios");
        btnDatosUsuarios.addActionListener(e -> {
            frame.dispose();
            try {
                new FrmAdminUsuarios(user);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel_1.add(btnDatosUsuarios);

        frame.setVisible(true);
    }

}
