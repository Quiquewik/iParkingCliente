package cliente.view;

import cliente.logica.LogicaVehiculo;
import cliente.model.Usuario;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmListaVehiculos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario user;

	public FrmListaVehiculos(Usuario user) {
		setTitle("Tus vehículos");
		this.user = user;
		createForm();
		
	}
	
	public void createForm() {
		setModal(true);
		setTitle("iParking - Lista de vahículos");
		//setIconImage(new ImageIcon(ImageIO.read(new File("src/main/resources/aparcamiento.png"))).getImage());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setBounds(100, 100, 550, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelVehiculos = new JPanel();
		contentPane.add(panelVehiculos);

		DefaultTableModel model = LogicaVehiculo.getDefaultTableModel(user);
		panelVehiculos.setLayout(new BorderLayout(0, 0));
		JTable table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setBounds(new Rectangle(10, 11, 404, 176));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setModel(model);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table);
		panelVehiculos.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.WEST);
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu mnSesin = new JMenu("Sesión");
		menuBar.add(mnSesin);
		
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
		itemCerrarSesion.addActionListener(e -> {
			if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		mnSesin.add(itemCerrarSesion);
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Añadir");
		btnAdd.addActionListener(e -> {
			new FrmVehiculo(user, table.getSelectedRow(),true);
			table.setModel(LogicaVehiculo.getDefaultTableModel(cliente.logica.LogicaUsuario.updateUsuario(user)));
		});
		panelBotones.add(btnAdd);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(e -> {
			new FrmVehiculo(user, table.getSelectedRow(),false);
			table.setModel(LogicaVehiculo.getDefaultTableModel(cliente.logica.LogicaUsuario.updateUsuario(user)));

		});
		panelBotones.add(btnEditar);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(e -> {
			if (table.getSelectedRow() > -1){
				user = LogicaVehiculo.eliminarVehiculo(user, table.getSelectedRow());
				table.setModel(LogicaVehiculo.getDefaultTableModel(cliente.logica.LogicaUsuario.updateUsuario(user)));
				JOptionPane.showMessageDialog(null,"Vehiculo eliminado.");
			}else {
				JOptionPane.showMessageDialog(null,"Selecciona un vehiculo a eliminar.");
			}

		});
		btnNewButton.setForeground(new Color(255, 0, 0));
		panelBotones.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(0, 0, 0));
		panelBotones.add(separator);
		
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addActionListener(e -> table.setModel(LogicaVehiculo.getDefaultTableModel(cliente.logica.LogicaUsuario.getUsuarioById(user.getId()))));
		panelBotones.add(btnRefrescar);
		setVisible(true);
	}

}
