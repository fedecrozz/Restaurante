import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ArticuloDeliveryModificar extends JFrame {

	private JPanel contentPane;
	private Conector con = new Conector();
	private Principal p;
	private ArticulosDelivery a;
	private Articulo Articulo;
	private JTextField codigo;
	private JTextField descripcion;
	private JTextField precio;
	private JTextField costo;
	private JTextField stock;
	private JTextField observacion;
	private JComboBox categoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArticuloDeliveryModificar frame = new ArticuloDeliveryModificar(null,null);
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
	public ArticuloDeliveryModificar(ArticulosDelivery ar,Articulo art) {
		a = ar;
		Articulo = art;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Modificar Articulo");
		setBounds(100, 100, 352, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		centrarPantalla();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.DARK_GRAY);
		contentPane_1.setBounds(0, 0, 336, 470);
		contentPane.add(contentPane_1);
		
		JLabel lblArticulo = new JLabel("Codigo");
		lblArticulo.setForeground(Color.WHITE);
		lblArticulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblArticulo.setBounds(47, 0, 237, 26);
		contentPane_1.add(lblArticulo);
		
		codigo = new JTextField();
		codigo.setEditable(false);
		codigo.setText("0");
		codigo.setColumns(10);
		codigo.setBounds(47, 33, 237, 20);
		contentPane_1.add(codigo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblDescripcion.setBounds(47, 60, 237, 26);
		contentPane_1.add(lblDescripcion);
		
		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(47, 93, 237, 20);
		contentPane_1.add(descripcion);
		
		JLabel lblPrecioDeVenta = new JLabel("Precio de Venta");
		lblPrecioDeVenta.setForeground(Color.WHITE);
		lblPrecioDeVenta.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblPrecioDeVenta.setBounds(47, 120, 237, 26);
		contentPane_1.add(lblPrecioDeVenta);
		
		precio = new JTextField();
		precio.setText("0");
		precio.setColumns(10);
		precio.setBounds(47, 153, 237, 20);
		contentPane_1.add(precio);
		
		JLabel lblPrecioDeCosto = new JLabel("Precio de Costo");
		lblPrecioDeCosto.setForeground(Color.WHITE);
		lblPrecioDeCosto.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblPrecioDeCosto.setBounds(47, 180, 237, 26);
		contentPane_1.add(lblPrecioDeCosto);
		
		costo = new JTextField();
		costo.setText("0");
		costo.setColumns(10);
		costo.setBounds(47, 213, 237, 20);
		contentPane_1.add(costo);
		
		JLabel lblGrupoDeArticulo = new JLabel("Categoria");
		lblGrupoDeArticulo.setForeground(Color.WHITE);
		lblGrupoDeArticulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblGrupoDeArticulo.setBounds(47, 240, 237, 26);
		contentPane_1.add(lblGrupoDeArticulo);
		
		categoria = new JComboBox();
		categoria.setBounds(47, 273, 237, 20);
		contentPane_1.add(categoria);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblStock.setBounds(47, 300, 237, 26);
		contentPane_1.add(lblStock);
		
		stock = new JTextField();
		stock.setText("0");
		stock.setColumns(10);
		stock.setBounds(47, 333, 237, 20);
		contentPane_1.add(stock);
		
		JLabel lblObservacion = new JLabel("Observacion");
		lblObservacion.setForeground(Color.WHITE);
		lblObservacion.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblObservacion.setBounds(47, 360, 237, 26);
		contentPane_1.add(lblObservacion);
		
		observacion = new JTextField();
		observacion.setColumns(10);
		observacion.setBounds(47, 393, 237, 20);
		contentPane_1.add(observacion);
		
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarArticulo();
			}
		});
		btnNewButton.setText("Guardar Articulo");
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnNewButton.setBounds(47, 420, 237, 41);
		contentPane_1.add(btnNewButton);
		
		iniciarCategorias();
		iniciarArticulo();
	}
	
	public void iniciarCategorias() {
		con.conectar();
		ArrayList<Categoria> categorias = con.getCategoriasDelivery();
		con.cerrarConexion();
		
		DefaultComboBoxModel<String> combo = new DefaultComboBoxModel<>();
		combo.removeAllElements();
		
		
		
		for(int i = 0; i < categorias.size(); i++) {
			combo.addElement(categorias.get(i).getDescripcion());
		}
		categoria.setModel(combo);
		
		
	}
	
	public void modificarArticulo() {
		String Articulo = codigo.getText().toUpperCase();		
		String Descripcion = descripcion.getText();		
		String Categoria= categoria.getSelectedItem().toString();
		
		con.conectar();
		boolean existe = con.existeArticuloDelivery(Articulo);
		con.cerrarConexion();
		
		
		if(codigo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese el codigo");
		}else {
			if(existe) {
				JOptionPane.showMessageDialog(null, "Ya existe un articulo con ese codigo");
			}else {				
				if(!precio.getText().matches("^\\d{1,3}(,?\\d{3})*(\\.\\d{1,2})?$")) {
					JOptionPane.showMessageDialog(null, "Ingrese un precio de venta valido");
				}else {
					if(!costo.getText().matches("^\\d{1,3}(,?\\d{3})*(\\.\\d{1,2})?$")) {
						JOptionPane.showMessageDialog(null, "Ingrese un precio de costo valido");
					}else {
						if(!stock.getText().matches("^\\d{1,3}(,?\\d{3})*(\\.\\d{1,2})?$")) {
							JOptionPane.showMessageDialog(null, "Ingrese un stock valido");
						}else {
							Articulo c = new Articulo();
							
							double Precio = Double.valueOf(precio.getText());							
							double Costo= Double.valueOf(costo.getText());
							double Stock= Double.valueOf(stock.getText());
							String Observacion = observacion.getText();
							
							c.setCodigo(Integer.valueOf(Articulo));
							c.setDescripcion(Descripcion);
							c.setCategoria(Categoria);
							c.setCosto(Costo);
							c.setStock(Stock);
							c.setPrecio(Precio);
							c.setObservacion(Observacion);
							
							con.conectar();
							con.modificarArticuloDelivery(c);
							con.cerrarConexion();
							
							JOptionPane.showMessageDialog(null, "Se modific� exitosamente el articulo "+Descripcion);
							a.iniciarArticulos();
							this.dispose();
							
							
						}
					}
				}
				
			}
			
		}
	}
	
	public String quitarDecimal(String valor) {
        if (valor.endsWith(".0")) {
            return valor.substring(0, valor.length() - 2);
        } else {
            return valor;
        }
    }
	
	public void iniciarArticulo() {
		codigo.setText(String.valueOf(Articulo.getCodigo()));
		descripcion.setText(Articulo.getDescripcion());
		categoria.setSelectedItem(Articulo.getCategoria());
		precio.setText(quitarDecimal(String.valueOf(Articulo.getPrecio())));
		costo.setText(quitarDecimal(String.valueOf(Articulo.getCosto())));
		stock.setText(quitarDecimal(String.valueOf(Articulo.getStock())));
		observacion.setText(Articulo.getObservacion());
	}

	public void centrarPantalla() {
		Toolkit toolkit =  getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
}
