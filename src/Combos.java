import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Combos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Combos frame = new Combos();
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
	public Combos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("<---");
		btnNewButton.setBounds(587, 200, 89, 88);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("--->");
		btnNewButton_1.setBounds(587, 299, 89, 88);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 559, 391);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Codigo", "Descripcion"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(91);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(695, 200, 559, 391);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Codigo", "Descripcion"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 1244, 160);
		contentPane.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Codigo", "Descripcion", "Precio", "Costo", "Ganancia"
			}
		));
		scrollPane_2.setViewportView(table_2);
		
		JLabel lblNewLabel = new JLabel("Articulos dentro de este combo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 175, 559, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblArticulos = new JLabel("Articulos");
		lblArticulos.setHorizontalAlignment(SwingConstants.CENTER);
		lblArticulos.setForeground(Color.WHITE);
		lblArticulos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblArticulos.setBounds(695, 175, 559, 20);
		contentPane.add(lblArticulos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 602, 1244, 68);
		contentPane.add(panel_1);
		
		JButton btnAgregarCategoria = new JButton("Agregar Combo");
		btnAgregarCategoria.setBounds(147, 11, 218, 46);
		panel_1.add(btnAgregarCategoria);
		
		JButton btnModificarArticulo = new JButton("Modificar Combo");
		btnModificarArticulo.setBounds(512, 11, 218, 46);
		panel_1.add(btnModificarArticulo);
		
		JButton btnEliminarCategoria = new JButton("Eliminar Combo");
		btnEliminarCategoria.setBounds(877, 11, 218, 46);
		panel_1.add(btnEliminarCategoria);
	}
}
