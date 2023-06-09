import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class ArticulosDelivery extends JFrame {

	private JPanel contentPane;
	private JTable tabla_articulos;
	private JTextField txtBuscarArticulo;
	private Conector con = new Conector();
	private DefaultTableModel modelo_articulos = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArticulosDelivery frame = new ArticulosDelivery();
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
	public ArticulosDelivery() {
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
		
		txtBuscarArticulo = new JTextField();
		txtBuscarArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				iniciarArticulos();
			}
		});
		txtBuscarArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBuscarArticulo.setBounds(10, 10, 1224, 47);
		panel.add(txtBuscarArticulo);
		txtBuscarArticulo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 1244, 479);
		contentPane.add(scrollPane);
		
		tabla_articulos = new JTable();
		tabla_articulos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Codigo", "Descripcion", "Categoria", "Precio", "Precio 2", "Costo", "Ganancia", "Stock", "Observacion"
			}
		));
		scrollPane.setViewportView(tabla_articulos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 580, 1244, 68);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Agregar Articulo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				articuloNuevo();
			}
		});
		btnNewButton.setBounds(147, 11, 218, 46);
		panel_1.add(btnNewButton);
		
		JButton btnModificarArticulo = new JButton("Modificar Articulo");
		btnModificarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarArticulo();
			}
		});
		
		btnModificarArticulo.setBounds(512, 11, 218, 46);
		panel_1.add(btnModificarArticulo);
		
		JButton btnEliminarArticulo = new JButton("Eliminar Articulo");
		btnEliminarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarArticulo();
			}
		});
		btnEliminarArticulo.setBounds(877, 11, 218, 46);
		panel_1.add(btnEliminarArticulo);
		
		iniciarTodo();
		
		}
		
	public void modificarArticulo() {
			if(tabla_articulos.getSelectedRow()<0) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un articulo antes");
			}else {
				
				String codigo = tabla_articulos.getValueAt(tabla_articulos.getSelectedRow(), 0).toString();
				
				con.conectar();
				Articulo art = con.getArticuloDelivery(codigo);
				con.cerrarConexion();
				
				ArticuloDeliveryModificar a = new ArticuloDeliveryModificar(this, art);
				a.setVisible(true);
			}
		}
	
	public void iniciarTodo() {
		iniciarArticulos();
		centrarPantalla();
	}
	
	public void articuloNuevo() {
		ArticuloDeliveryNuevo a = new ArticuloDeliveryNuevo(this,con);
		a.setVisible(true);
	}
	
	public String quitarDecimal(String valor) {
        if (valor.endsWith(".0")) {
            return valor.substring(0, valor.length() - 2);
        } else {
            return valor;
        }
    }
	
	public void eliminarArticulo() {
		if(tabla_articulos.getSelectedRow()<0) {
			JOptionPane.showMessageDialog(null, "Tiene que seleccionar un articulo primero.");
		}else {
			int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar este articulo?");
			if(seleccion==0) {
				eliminarArticuloSeleccionado();
			}
		}
	}
	
	public void eliminarArticuloSeleccionado() {
		String articulo = tabla_articulos.getValueAt(tabla_articulos.getSelectedRow(), 0).toString();
		
		con.conectar();
		con.eliminarArticulo(articulo);
		con.cerrarConexion();
		
		JOptionPane.showMessageDialog(null, "Articulo eliminado exitosamente");
		iniciarArticulos();		
	}
	
	public void iniciarArticulos() {
		modelo_articulos = new DefaultTableModel();		
		String articulo = txtBuscarArticulo.getText();
		
		con.conectar();
		ArrayList<Articulo> articulos = con.getArticulosDeliveryBusqueda(articulo);
		con.cerrarConexion();
		
		modelo_articulos.addColumn("Codigo");
		modelo_articulos.addColumn("Descripcion");
		modelo_articulos.addColumn("Categoria");
		modelo_articulos.addColumn("Precio");
		modelo_articulos.addColumn("Costo");
		modelo_articulos.addColumn("Stock");
		modelo_articulos.addColumn("Observacion");
		
		
		for(int i = 0 ; i< articulos.size();i++) {
			Articulo m = articulos.get(i);
			modelo_articulos.addRow(new Object[] {m.getCodigo(),m.getDescripcion(),m.getCategoria(),"$"+quitarDecimal(String.valueOf(m.getPrecio())),"$"+quitarDecimal(String.valueOf(m.getCosto())),quitarDecimal(String.valueOf(m.getStock())),m.getObservacion()});
			}
		tabla_articulos.setModel(modelo_articulos);
	}
	
	public void centrarPantalla() {
		Toolkit toolkit =  getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
	
}
