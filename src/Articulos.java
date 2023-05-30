import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Articulos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Articulos frame = new Articulos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Articulos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Acciones");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Importar Precios (Codigo;Precio)");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Importar Costos (Codigo;Costo)");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Importar Articulos");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Modificar Precios Masivamente");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Modificar Costos Masivamente");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Modificar Stock Masivamente");
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Exportar Precios");
		mnNewMenu.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Exportar Costos");
		mnNewMenu.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Exportar Articulos");
		mnNewMenu.add(mntmNewMenuItem_8);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1244, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 10, 1224, 47);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 1244, 479);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Codigo", "Descripcion", "Categoria", "Precio", "Costo", "Ganancia", "Stock", "Observacion"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 580, 1244, 68);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Agregar Articulo");
		btnNewButton.setBounds(147, 11, 218, 46);
		panel_1.add(btnNewButton);
		
		JButton btnModificarArticulo = new JButton("Modificar Articulo");
		btnModificarArticulo.setBounds(512, 11, 218, 46);
		panel_1.add(btnModificarArticulo);
		
		JButton btnEliminarArticulo = new JButton("Eliminar Articulo");
		btnEliminarArticulo.setBounds(877, 11, 218, 46);
		panel_1.add(btnEliminarArticulo);
	}
}
