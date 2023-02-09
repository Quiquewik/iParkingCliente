package cliente.view;

import cliente.logica.LogicaReserva;
import cliente.model.Parking;
import cliente.model.Usuario;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class FrmListaReservas extends JDialog {

	private JTable table;
	private final Usuario user;
	private final Parking parking;

	/**
	 * Launch the application.
	 */
	public FrmListaReservas(Usuario user, Parking parking) throws IOException {
		this.user = user;
		this.parking = parking;
		createForm();
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public void createForm() throws IOException {
		setModal(true);
		setTitle("iParking - Lista de reservas");
		setIconImage(new ImageIcon(ImageIO.read(new File("src/main/resources/aparcamiento.png"))).getImage());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
					dispose();
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu MenuSesion = new JMenu("Sesión");
		menuBar.add(MenuSesion);
		
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión y salir");
		itemCerrarSesion.addActionListener(e -> {
			if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Cuidado",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		MenuSesion.add(itemCerrarSesion);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(LogicaReserva.getDefaultTableModel(user.getDni()));
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton bntEliminar = new JButton("Eliminar reserva");
		bntEliminar.addActionListener(e -> {
			LogicaReserva.eliminarReserva(table.getSelectedRow(), user, parking);
			table.setModel(LogicaReserva.getDefaultTableModel(user.getDni()));
		});
		bntEliminar.setForeground(new Color(255, 0, 0));
		panel_1.add(bntEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(e -> dispose());
		panel_1.add(btnVolver);
	}

}
