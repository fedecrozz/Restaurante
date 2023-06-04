import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class ArticuloNuevo extends JFrame {

	private JPanel contentPane;
	private JTextField codigo;
	private JTextField descripcion;
	private JTextField precio_venta;
	private JTextField precio_costo;
	private JTextField stock;
	private Conector con = new Conector();
	private Articulos a;
	private JComboBox categoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArticuloNuevo frame = new ArticuloNuevo(null, null);
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
	public ArticuloNuevo(Articulos articulos, Conector con) {
		a = articulos;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Articulo Nuevo");
		setBounds(100, 100, 373, 432);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		centrarPantalla();
		
		
		JLabel lblArticulo = new JLabel("Codigo");
		lblArticulo.setForeground(Color.WHITE);
		lblArticulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblArticulo.setBounds(60, 5, 237, 26);
		contentPane.add(lblArticulo);
		
		codigo = new JTextField();
		codigo.setColumns(10);
		codigo.setBounds(60, 36, 237, 20);
		contentPane.add(codigo);
		
		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(60, 92, 237, 20);
		contentPane.add(descripcion);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblDescripcion.setBounds(60, 61, 237, 26);
		contentPane.add(lblDescripcion);
		
		precio_venta = new JTextField();
		precio_venta.setText("0");
		precio_venta.setColumns(10);
		precio_venta.setBounds(60, 148, 237, 20);
		contentPane.add(precio_venta);
		
		JLabel lblPrecioDeVenta = new JLabel("Precio de Venta");
		lblPrecioDeVenta.setForeground(Color.WHITE);
		lblPrecioDeVenta.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblPrecioDeVenta.setBounds(60, 117, 237, 26);
		contentPane.add(lblPrecioDeVenta);
		
		precio_costo = new JTextField();
		precio_costo.setText("0");
		precio_costo.setColumns(10);
		precio_costo.setBounds(60, 204, 237, 20);
		contentPane.add(precio_costo);
		
		JLabel lblPrecioDeCosto = new JLabel("Precio de Costo");
		lblPrecioDeCosto.setForeground(Color.WHITE);
		lblPrecioDeCosto.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblPrecioDeCosto.setBounds(60, 173, 237, 26);
		contentPane.add(lblPrecioDeCosto);
		
		JLabel lblGrupoDeArticulo = new JLabel("Categoria");
		lblGrupoDeArticulo.setForeground(Color.WHITE);
		lblGrupoDeArticulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblGrupoDeArticulo.setBounds(60, 229, 237, 26);
		contentPane.add(lblGrupoDeArticulo);
		
		stock = new JTextField();
		stock.setText("0");
		stock.setColumns(10);
		stock.setBounds(60, 316, 237, 20);
		contentPane.add(stock);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblStock.setBounds(60, 285, 237, 26);
		contentPane.add(lblStock);
		
		categoria = new JComboBox();
		categoria.setBounds(60, 260, 237, 20);
		contentPane.add(categoria);
		
		
		//Icon icon = new ImageIcon(Principal.class.getResource("diskette.png"));
	
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarArticulo();
			}
		});
		btnNewButton.setText("Guardar Articulo");
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnNewButton.setBounds(60, 341, 237, 41);
		contentPane.add(btnNewButton);
		
		iniciarTodo();
	}
	
	public void centrarPantalla() {
		Toolkit toolkit =  getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
	
	public void iniciarTodo() {
		centrarPantalla();
		iniciarCategorias();
		iniciarCodigo();
	}

	public void iniciarCodigo() {
		con.conectar();
		String ultimo = con.getCodigoUltimoArticulo();
		con.cerrarConexion();
		
		
		codigo.setText(String.valueOf(Integer.valueOf(ultimo)+1));
	}
	
	public void guardarArticulo() {
		String Articulo = codigo.getText().toUpperCase();		
		String Descripcion = descripcion.getText();		
		String Grupo= categoria.getSelectedItem().toString();
		
		con.conectar();
		boolean existe = con.existeArticulo(Articulo);
		con.cerrarConexion();
		
		
		if(codigo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese el codigo");
		}else {
			if(existe) {
				JOptionPane.showMessageDialog(null, "Ya existe un articulo con ese codigo");
			}else {				
				if(!precio_venta.getText().matches("^\\d{1,3}(,?\\d{3})*(\\.\\d{1,2})?$")) {
					JOptionPane.showMessageDialog(null, "Ingrese un precio de venta valido");
				}else {
					if(!precio_costo.getText().matches("^\\d{1,3}(,?\\d{3})*(\\.\\d{1,2})?$")) {
						JOptionPane.showMessageDialog(null, "Ingrese un precio de costo valido");
					}else {
						if(!stock.getText().matches("^\\d{1,3}(,?\\d{3})*(\\.\\d{1,2})?$")) {
							JOptionPane.showMessageDialog(null, "Ingrese un stock valido");
						}else {
							Articulo c = new Articulo();
							double Precio_venta = Double.valueOf(precio_venta.getText());							
							double Precio_costo= Double.valueOf(precio_costo.getText());
							double Stock= Double.valueOf(stock.getText());
							
							c.setCodigo(Articulo);
							c.setDescripcion(Descripcion);
							c.setCategoria(Grupo);
							c.setCosto(Precio_costo);
							c.setStock(Stock);
							c.setPrecio(Precio_venta);
							
							con.conectar();
							con.guardarArticulo(c);
							con.cerrarConexion();
							
							JOptionPane.showMessageDialog(null, "Se guardó exitosamente el articulo "+Articulo);
							codigo.setText("");
							descripcion.setText("");
							precio_venta.setText("");
							precio_costo.setText("");
							stock.setText("");
							
							p.iniciarArticulos();
							
						}
					}
				}
				
			}
			
		}
	}

	public void iniciarCategorias() {
		con.conectar();
		ArrayList<Categoria> categorias = con.getCategorias();
		con.cerrarConexion();
		
		DefaultComboBoxModel<String> combo = new DefaultComboBoxModel<>();
		combo.removeAllElements();
		
		
		
		for(int i = 0; i < categorias.size(); i++) {
			combo.addElement(categorias.get(i).getDescripcion());
		}
		categoria.setModel(combo);
		
		
	}

}
