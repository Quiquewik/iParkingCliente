package cliente.view;

import cliente.logica.LogicaUsuario;
import cliente.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmLogin extends JFrame {

	private JFrame frame;
	private JTextField txtDni;
	private Usuario usuario;
	private JPasswordField passwordField;

	public FrmLogin() {

		createForm();
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

		frame.setTitle("Logging");
		frame.setBounds(100, 100, 300, 232);
		frame.setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu MenuInicio = new JMenu("Inicio");
		menuBar.add(MenuInicio);
		
		JMenuItem ItemSalir = new JMenuItem("Salir");
		ItemSalir.addActionListener(e -> {
				if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.dispose();
				}
			});
		MenuInicio.add(ItemSalir);
		
		JMenu MenuAyuda = new JMenu("Ayuda");
		menuBar.add(MenuAyuda);
		
		JMenuItem itemAyuda = new JMenuItem("Ayuda");
		MenuAyuda.add(itemAyuda);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		JPanel PanelBotones = new JPanel();
		panel.add(PanelBotones, BorderLayout.SOUTH);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.addActionListener(arg0 -> {
			if (txtDni.getText().trim().length() > 0 && passwordField.getPassword().length > 0) {
				usuario = LogicaUsuario.loggin(txtDni.getText().trim(),new String(passwordField.getPassword()));
				if (usuario != null){
					if (usuario.getPassword().equals(new String(passwordField.getPassword()))){
						switch (usuario.getTipoUsuario()){
							case 1://Admin
								//TODO abrir admin
								break;
							case 2://Normal User
								//TODO abrir user normal
								break;
							default:
								JOptionPane.showMessageDialog(null,"Tipo de usuario desconocido.");
						}
					}else{
						JOptionPane.showMessageDialog(null,"Contraseña introducida es incorrecta.");
					}

				}else{
					JOptionPane.showMessageDialog(null,"Usuario introducido no existe.");
				}

			}else{
				JOptionPane.showMessageDialog(null,"Rellena todos los campos.");
			}
		});
		PanelBotones.add(btnIniciarSesion);
		
		JButton btnNewButton = new JButton("Registrar cuenta");
		PanelBotones.add(btnNewButton);
		
		JPanel PanelDatos = new JPanel();
		panel.add(PanelDatos, BorderLayout.CENTER);
		PanelDatos.setLayout(null);
		
		
		JLabel lblDNI = new JLabel("Introduce tu DNI:");
		lblDNI.setBounds(6, 29, 109, 16);
		lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
		PanelDatos.add(lblDNI);
		
		txtDni = new JTextField();
		txtDni.setBounds(145, 24, 118, 26);
		txtDni.setHorizontalAlignment(SwingConstants.CENTER);
		PanelDatos.add(txtDni);
		txtDni.setColumns(9);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(52, 67, 63, 16);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		PanelDatos.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setLocation(145, 62);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setSize(118, 26);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == (char) 32) {
					e.setKeyChar((char) 0);
				}

			}
		});

		PanelDatos.add(passwordField);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setSize(1, 20);
		panel_2.add(separator_1);
		
		JLabel lblInicioSesion_1 = new JLabel("Inicia sesión o regístrate.");
		lblInicioSesion_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblInicioSesion_1);
	}

}
