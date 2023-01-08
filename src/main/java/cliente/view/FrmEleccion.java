package cliente.view;

import cliente.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;

public class FrmEleccion {

	private JFrame frame;
	private final Usuario user;

	Logger logger = LoggerFactory.getLogger(FrmEleccion.class);

	public FrmEleccion(Usuario user) {
		createForm();
		this.user = user;
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 223);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblSeleccion = new JLabel("Selecciona la pantalla a la que quieres ir:");
		lblSeleccion.setBounds(2, 2, 430, 57);
		lblSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblSeleccion);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 61, 412, 76);
		panel_1.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnDatosUsuario = new JButton("Perfil de usuario");
		btnDatosUsuario.addActionListener(e -> {
			logger.info(user.toString());
			new FrmPerfil(user,false);
			frame.dispose();
		});
		btnDatosUsuario.setPreferredSize(new Dimension(190, 50));
		panel.add(btnDatosUsuario, BorderLayout.WEST);
		
		JButton btnAparcamientos = new JButton("Aparcamientos");
		btnAparcamientos.addActionListener(e -> {
			new FrmParking(user);
			frame.dispose();
		});
		btnAparcamientos.setPreferredSize(new Dimension(190, 50));
		panel.add(btnAparcamientos, BorderLayout.EAST);
		
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
		
		JMenu MenuAyuda = new JMenu("Ayuda");
		menuBar.add(MenuAyuda);
		
		JMenuItem itemAyuda = new JMenuItem("Ayuda");
		itemAyuda.addActionListener(e -> {
			//TODO
		});
		MenuAyuda.add(itemAyuda);

	}

}
