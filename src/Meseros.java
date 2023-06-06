import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Meseros extends JFrame {

	private JPanel contentPane;
	private Conector con = new Conector();
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Meseros frame = new Meseros();
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
	public Meseros() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1244, 580);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 602, 1244, 68);
		contentPane.add(panel_1);
		
		JButton btnNewButton = new JButton("Agregar Mesero");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del mesero");
				nombre= nombre.toUpperCase();
				
				con.conectar();
				boolean existe = con.existeMesero(nombre);
				con.cerrarConexion();
				
				if(existe) {
					JOptionPane.showMessageDialog(null, "Ya existe un mesero con ese nombre");
				}else {
					con.conectar();
					con.guardarMesero(nombre);
					con.cerrarConexion();
					
					JOptionPane.showMessageDialog(null, "Mesero agregado correctamente");
					iniciarMeseros();
				}
				
			}
		});
		btnNewButton.setBounds(147, 11, 218, 46);
		panel_1.add(btnNewButton);
		
		JButton btnModificarArticulo = new JButton("Modificar Mesero");
		btnModificarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarMesero();
			}
		});
		btnModificarArticulo.setBounds(512, 11, 218, 46);
		panel_1.add(btnModificarArticulo);
		
		JButton btnEliminarArticulo = new JButton("Eliminar Mesero");
		btnEliminarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarMesero();
			}
		});
		btnEliminarArticulo.setBounds(877, 11, 218, 46);
		panel_1.add(btnEliminarArticulo);
		
		iniciarTodo();
	}
	
	
	public void eliminarMesero() {
		if(table.getSelectedRow()<0) {
			JOptionPane.showMessageDialog(null, "Primero tiene que elegir un mesero");
		}else {

			String nombre = table.getValueAt(table.getSelectedRow(), 1).toString();
			int input = JOptionPane.showConfirmDialog(null,"¿Eliminar el mesero '"+nombre+"'?");
			if(input == 0) {
				con.conectar();
				con.eliminarMesero(nombre);
				con.cerrarConexion();
				iniciarMeseros();
			}
		}
	}
	
	public void centrarPantalla() {
		Toolkit toolkit =  getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
	
	public void iniciarTodo() {
		centrarPantalla();
		iniciarMeseros();
	}
	
	public void modificarMesero() {
		if(table.getSelectedRow()<0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un mesero antes");
		}else {
			
			String codigo = table.getValueAt(table.getSelectedRow(), 0).toString();
			String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del mesero");
			
			con.conectar();
			con.modificarMesero(codigo,nombre.toUpperCase());
			con.cerrarConexion();
			
			JOptionPane.showMessageDialog(null, "Mesero modificado correctamente");
			iniciarMeseros();
		}
	}
	
	public void iniciarMeseros() {
		con.conectar();
		ArrayList<Mesero> meseros = con.getMeseros();
		con.cerrarConexion();
		
		modelo = new DefaultTableModel();

		modelo.addColumn("Codigo");
		modelo.addColumn("Descripcion");
		
		
		for(int i = 0 ; i< meseros.size();i++) {
			Mesero m = meseros.get(i);
			modelo.addRow(new Object[] {m.getCodigo(),m.getNombre()});
			}
		
		table.setModel(modelo);
		
		
	}
}
