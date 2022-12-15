package cliente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class FrmEleccion {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEleccion window = new FrmEleccion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmEleccion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblSeleccion = new JLabel("Selecciona la pantalla a la que quieres ir:");
		lblSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblSeleccion);
		
		JButton btnDatosUsuario = new JButton("Perfil de usuario");
		btnDatosUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		panel_1.add(btnDatosUsuario);
		
		JButton btnAparcamientos = new JButton("Aparcamientos");
		btnAparcamientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		panel_1.add(btnAparcamientos);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu MenuSesion = new JMenu("Sesión");
		menuBar.add(MenuSesion);
		
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
		itemCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		MenuSesion.add(itemCerrarSesion);
		
		JMenu MenuAyuda = new JMenu("Ayuda");
		menuBar.add(MenuAyuda);
		
		JMenuItem itemAyuda = new JMenuItem("Ayuda");
		itemAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		MenuAyuda.add(itemAyuda);
	}

}
