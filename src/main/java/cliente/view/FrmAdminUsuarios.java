package cliente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAdminUsuarios {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAdminUsuarios window = new FrmAdminUsuarios();
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
	public FrmAdminUsuarios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu MenuSesion = new JMenu("Sesión");
		menuBar.add(MenuSesion);
		
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
		MenuSesion.add(itemCerrarSesion);
		
		JPanel panelListaUsuarios = new JPanel();
		frame.getContentPane().add(panelListaUsuarios);
		
		

		String[] nombreColumnas = new String[]{"id", "Usuario", "DNI", "Nombre", "Apellidos", "Dirección", "correo", "Tipo usuario"	};
		//TODO metodo para pinta tabla en logica.
		
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(nombreColumnas);
		table = new JTable(model);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelListaUsuarios.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panelListaUsuarios.add(scrollPane);
		JPanel panelBotones = new JPanel();
		frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO boton
			}
		});
		
		JButton btnRefresh = new JButton("Actualizar");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO boton
			}
		});
		panelBotones.add(btnRefresh);
		panelBotones.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO boton
			}
		});
		btnEliminar.setForeground(new Color(255, 0, 4));
		panelBotones.add(btnEliminar);
	}

}
