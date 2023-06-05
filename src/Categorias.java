import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Categorias extends JFrame {

	private JPanel contentPane;
	private JTable tabla_categorias;
	private DefaultTableModel modelo = new DefaultTableModel();
	private DefaultTableModel modelo1 = new DefaultTableModel();
	private DefaultTableModel modelo2 = new DefaultTableModel();
	private Conector con = new Conector();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Categorias frame = new Categorias();
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
	public Categorias() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 1244, 580);
		contentPane.add(scrollPane_2);
		
		tabla_categorias = new JTable();
		
		tabla_categorias.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Codigo", "Descripcion"
			}
		));
		scrollPane_2.setViewportView(tabla_categorias);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 602, 1244, 68);
		contentPane.add(panel_1);
		
		JButton btnAgregarCategoria = new JButton("Agregar Categoria");
		btnAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descripcion = JOptionPane.showInputDialog("Ingrese el nombre de la categoria");
				descripcion = descripcion.toUpperCase();
				
				con.conectar();
				boolean existe = con.existeCategoria(descripcion);
				con.cerrarConexion();
				
				if(existe) {
					JOptionPane.showMessageDialog(null, "Ya existe una categoria con ese nombre");
				}else {
					con.conectar();
					con.guardarCategoria(descripcion);
					con.cerrarConexion();
					
					JOptionPane.showMessageDialog(null, "Categoria agregada correctamente");
					iniciarCategorias();
				}
				
			}
		});
		btnAgregarCategoria.setBounds(147, 11, 218, 46);
		panel_1.add(btnAgregarCategoria);
		
		JButton btnModificarArticulo = new JButton("Modificar Categoria");
		btnModificarArticulo.setBounds(512, 11, 218, 46);
		panel_1.add(btnModificarArticulo);
		
		JButton btnEliminarCategoria = new JButton("Eliminar Categoria");
		btnEliminarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabla_categorias.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Primero tiene que elegir una categoria");
				}else {

					String nombre = tabla_categorias.getValueAt(tabla_categorias.getSelectedRow(), 1).toString();
					int input = JOptionPane.showConfirmDialog(null,"¿Eliminar la categoria '"+nombre+"'?");
					if(input == 0) {
						con.conectar();
						con.eliminarCategoria(nombre);
						con.cerrarConexion();
						iniciarCategorias();
					}
				}
			}
		});
		btnEliminarCategoria.setBounds(877, 11, 218, 46);
		panel_1.add(btnEliminarCategoria);
		
		iniciarTodo();
	}
	
	public void iniciarTodo() {
		centrarPantalla();
		iniciarCategorias();
	}
	
	public void iniciarCategorias() {
		modelo = new DefaultTableModel();		
		
		con.conectar();
		ArrayList<Categoria> categorias = con.getCategorias();
		con.cerrarConexion();
		
		modelo.addColumn("Codigo");
		modelo.addColumn("Descripcion");
		
		
		for(int i = 0 ; i< categorias.size();i++) {
			Categoria m = categorias.get(i);
			modelo.addRow(new Object[] {m.getCodigo(),m.getDescripcion()});
			}
		tabla_categorias.setModel(modelo);
		
		
		
	}

	public void centrarPantalla() {
		Toolkit toolkit =  getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
	

	public void actualizarTablaCategoriaSeleccionada() {
		String descripcion = tabla_categorias.getValueAt(tabla_categorias.getSelectedRow(), 1).toString();
		modelo1 = new DefaultTableModel();
		
		con.conectar();
		ArrayList<Articulo> articulos = con.getArticulosCategoria(descripcion);
		con.cerrarConexion();
		
		
		modelo1.addColumn("Codigo");
		modelo1.addColumn("Descripcion");
		
		
		for(int i = 0 ; i< articulos.size();i++) {
			Articulo a = articulos.get(i);
			modelo1.addRow(new Object[] {a.getCodigo(),a.getDescripcion()});
			}
		
		//table_1.setModel(modelo1);
		
		
		
	}
}
