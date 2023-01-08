package cliente.view;

import cliente.model.Usuario;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmEleccionAdmin {

	private JFrame frame;
	private final Usuario user;

	
	public FrmEleccionAdmin(Usuario user) {
		this.user = user;
		createFrom();
	}


	private void createFrom() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
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
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu MenuSesion = new JMenu("Sesión");
		menuBar.add(MenuSesion);
		
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
		itemCerrarSesion.addActionListener(e -> {
			if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
				frame.dispose();
			}
		});
		MenuSesion.add(itemCerrarSesion);
		
		JMenu MenuAyuda = new JMenu("Ayuda");
		menuBar.add(MenuAyuda);
		
		JMenuItem itemAyuda = new JMenuItem("Ayuda");
		itemAyuda.addActionListener(e -> {

		});
		MenuAyuda.add(itemAyuda);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblSeleccion = new JLabel("Selecciona la pantalla a la que quieres ir:");
		lblSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblSeleccion);
		
		JButton btnDatosUsuarios = new JButton("Gestionar Usuarios");
		btnDatosUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new FrmAdminUsuarios(user);
			}
		});
		panel_1.add(btnDatosUsuarios);
		
		JButton btnParkings = new JButton("Gestionar Parkings");
		btnParkings.addActionListener(e -> {
			//TODO
		});
		panel_1.add(btnParkings);
		frame.setVisible(true);
	}

}
