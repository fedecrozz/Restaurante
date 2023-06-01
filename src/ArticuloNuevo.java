import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	private JTextField articulo;
	private JTextField descripcion;
	private JTextField precio_venta;
	private JTextField precio_costo;
	private JTextField stock;
	private Conector con = new Conector();
	private Articulos a;
	private JComboBox grupo;
	private JTextField textField;

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
		setBounds(100, 100, 373, 491);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		centrarPantalla();
		
		
		JLabel lblArticulo = new JLabel("Codigo");
		lblArticulo.setForeground(Color.WHITE);
		lblArticulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblArticulo.setBounds(60, 11, 237, 26);
		contentPane.add(lblArticulo);
		
		articulo = new JTextField();
		articulo.setColumns(10);
		articulo.setBounds(60, 35, 237, 20);
		contentPane.add(articulo);
		
		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(60, 90, 237, 20);
		contentPane.add(descripcion);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblDescripcion.setBounds(60, 66, 237, 26);
		contentPane.add(lblDescripcion);
		
		precio_venta = new JTextField();
		precio_venta.setText("0");
		precio_venta.setColumns(10);
		precio_venta.setBounds(60, 145, 237, 20);
		contentPane.add(precio_venta);
		
		JLabel lblPrecioDeVenta = new JLabel("Precio de Venta");
		lblPrecioDeVenta.setForeground(Color.WHITE);
		lblPrecioDeVenta.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblPrecioDeVenta.setBounds(60, 121, 237, 26);
		contentPane.add(lblPrecioDeVenta);
		
		precio_costo = new JTextField();
		precio_costo.setText("0");
		precio_costo.setColumns(10);
		precio_costo.setBounds(60, 255, 237, 20);
		contentPane.add(precio_costo);
		
		JLabel lblPrecioDeCosto = new JLabel("Precio de Costo");
		lblPrecioDeCosto.setForeground(Color.WHITE);
		lblPrecioDeCosto.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblPrecioDeCosto.setBounds(60, 231, 237, 26);
		contentPane.add(lblPrecioDeCosto);
		
		JLabel lblGrupoDeArticulo = new JLabel("Categoria");
		lblGrupoDeArticulo.setForeground(Color.WHITE);
		lblGrupoDeArticulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblGrupoDeArticulo.setBounds(60, 286, 237, 26);
		contentPane.add(lblGrupoDeArticulo);
		
		stock = new JTextField();
		stock.setText("0");
		stock.setColumns(10);
		stock.setBounds(60, 367, 237, 20);
		contentPane.add(stock);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblStock.setBounds(60, 343, 237, 26);
		contentPane.add(lblStock);
		
		grupo = new JComboBox();
		grupo.setBounds(60, 312, 237, 20);
		contentPane.add(grupo);
		
		
		Icon icon = new ImageIcon(Principal.class.getResource("diskette.png"));
	
		JButton btnNewButton = new JButton(icon);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarArticulo();
			}
		});
		btnNewButton.setText("Guardar Articulo");
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnNewButton.setBounds(60, 398, 237, 41);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setColumns(10);
		textField.setBounds(60, 200, 237, 20);
		contentPane.add(textField);
		
		JLabel lblPrecioDeVenta_1 = new JLabel("Precio de Venta");
		lblPrecioDeVenta_1.setForeground(Color.WHITE);
		lblPrecioDeVenta_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblPrecioDeVenta_1.setBounds(60, 176, 237, 26);
		contentPane.add(lblPrecioDeVenta_1);
	}
	
	public void centrarPantalla() {
		Toolkit toolkit =  getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}

	public void guardarArticulo() {
		String Articulo = articulo.getText().toUpperCase();		
		String Descripcion = descripcion.getText();		
		String Grupo= grupo.getSelectedItem().toString();
		
		con.conectar();
		boolean existe = con.existeArticulo(Articulo);
		con.cerrarConexion();
		
		
		if(articulo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese el articulo");
		}else {
			if(existe) {
				JOptionPane.showMessageDialog(null, "Ya existe un articulo con ese nombre");
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
							
							c.setArticulo(Articulo);
							c.setDescripcion(Descripcion);
							c.setPrecio_venta(Precio_venta);
							c.setPrecio_costo(Precio_costo);
							c.setStock(Stock);
							c.setGrupo(Grupo);
							
							con.conectar();
							con.guardarArticulo(c);
							con.cerrarConexion();
							
							JOptionPane.showMessageDialog(null, "Se guardó exitosamente el articulo "+Articulo);
							articulo.setText("");
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
}
