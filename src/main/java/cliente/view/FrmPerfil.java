package cliente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

public class FrmPerfil {

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtApellidos;
	private JTextField txtCorreo;
	private JTextField txtDireccion;
	private JTextField textField_4;
	private JTextField textField;
	private JTextField txtID;
	private JTextField txtUsuario;
	private JTextField txtDNI;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPerfil window = new FrmPerfil();
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
	public FrmPerfil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
	
	
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBorrar = new JButton("Borrar Cuenta");
		panelBotones.add(btnBorrar);
		
		JSeparator separator = new JSeparator();
		panelBotones.add(separator);
		
		JButton btnGuardar = new JButton("Guardar");
		panelBotones.add(btnGuardar);
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDatosPerfil = new JLabel("Datos del Perfil");
		lblDatosPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosPerfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblDatosPerfil, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblId);
		
		txtID = new JTextField();
		txtID.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblUsuario);
		
		txtUsuario = new JTextField();
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblPassword);
		
		txtPass = new JPasswordField();
		txtPass.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtPass);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);

	}

}
