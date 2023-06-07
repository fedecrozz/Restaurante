import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerVentas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JDateChooser fecha_desde;
	private JDateChooser fecha_hasta;
	private double totalMesas = 0;
	private double totalIngresos = 0;
	private JLabel TotalMesas;
	private JLabel TotalIngresos;
	private Conector con = new Conector();
	private DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerVentas frame = new VerVentas();
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
	public VerVentas() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 596, 1244, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TotalMesas = new JLabel("Total Mesas:");
		TotalMesas.setHorizontalAlignment(SwingConstants.LEFT);
		TotalMesas.setFont(new Font("Tahoma", Font.BOLD, 13));
		TotalMesas.setBounds(10, 11, 266, 20);
		panel.add(TotalMesas);
		
		TotalIngresos = new JLabel("Total Ingresos:");
		TotalIngresos.setHorizontalAlignment(SwingConstants.LEFT);
		TotalIngresos.setFont(new Font("Tahoma", Font.BOLD, 13));
		TotalIngresos.setBounds(10, 43, 266, 20);
		panel.add(TotalIngresos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 96, 1244, 489);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 11, 1244, 74);
		contentPane.add(panel_1);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDesde.setBounds(10, 31, 62, 20);
		panel_1.add(lblDesde);
		
		fecha_desde = new JDateChooser();
		fecha_desde.setBounds(64, 32, 130, 20);
		panel_1.add(fecha_desde);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHasta.setBounds(204, 31, 62, 20);
		panel_1.add(lblHasta);
		
		fecha_hasta = new JDateChooser();
		fecha_hasta.setBounds(256, 32, 130, 20);
		panel_1.add(fecha_hasta);
		
		JButton actualizar = new JButton("Actualizar");
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarMontos();
				iniciarVentas();
			}
		});
		actualizar.setBounds(396, 32, 130, 20);
		panel_1.add(actualizar);
		
		JLabel lblFechaDeVentas = new JLabel("Fecha de ventas:");
		lblFechaDeVentas.setForeground(Color.LIGHT_GRAY);
		lblFechaDeVentas.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDeVentas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFechaDeVentas.setBounds(10, 0, 158, 20);
		panel_1.add(lblFechaDeVentas);
		
		iniciarTodo();
	}
	
	public void iniciarTodo() {
		iniciarFecha();
		centrar();
		setearApariencia();
		iniciarMontos();
		iniciarVentas();
	}

	public String getFechaByDate(java.util.Date date) {
		DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
		String finall = df.format(date);	
		
		return getFechaYYYYMMDD(finall);
	}
	
	public String getFechaYYYYMMDD(String fecha){		
		Date date1;
		String finall="";
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
			DateFormat df= new SimpleDateFormat("yyyy/MM/dd");
			finall = df.format(date1);	
		} catch (ParseException e) {			
			e.printStackTrace();
		}					
		return finall;
	}
	
	public String getFechaDDMMYYYY(String fecha){		
		Date date1;
		String finall="";
		try {
			date1 = new SimpleDateFormat("yyyy/MM/dd").parse(fecha);
			DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
			finall = df.format(date1);	
		} catch (ParseException e) {			
			e.printStackTrace();
		}					
		return finall;
	}
	
	public void iniciarVentas() {
		String FechaDesde = getFechaByDate(fecha_desde.getDate());
		String FechaHasta = getFechaByDate(fecha_hasta.getDate());
		
		modelo = new DefaultTableModel();
		
		con.conectar();
		ArrayList<Venta> ventas = con.getVentas(FechaDesde,FechaHasta);
		con.cerrarConexion();
		
		
		modelo.addColumn("Venta Numero");
		modelo.addColumn("Mesa Numero");
		modelo.addColumn("Fecha");
		modelo.addColumn("Mesero");
		modelo.addColumn("Descuento");
		modelo.addColumn("Recargo");
		modelo.addColumn("Subtotal");
		modelo.addColumn("Total");
		modelo.addColumn("Observacion");
		
		
		for(int i = 0; i< ventas.size();i++) {
			Venta v = ventas.get(i);
			modelo.addRow(new Object[] {v.getNumero(),v.getMesa_numero(),v.getFecha()+" "+v.getHora(),v.getMesero(),v.getDescuento(),v.getRecargo(),v.getPrecio(),(v.getPrecio()-v.getDescuento()+v.getRecargo()),v.getObservacion()});
		}
		table.setModel(modelo);
		
	}
	
	public void iniciarMontos() {
		String FechaDesde = getFechaByDate(fecha_desde.getDate());
		String FechaHasta = getFechaByDate(fecha_hasta.getDate());
		
		con.conectar();
		double tMesas = con.getMontoVenta(FechaDesde, FechaHasta);
		con.cerrarConexion();
		
		con.conectar();
		double tIngresos = con.getMontoVentaIngresos(FechaDesde, FechaHasta);
		con.cerrarConexion();
		
		
		
		TotalMesas.setText("Total Mesas: $"+tMesas);
		TotalIngresos.setText("Total Ingresos: $"+tIngresos);
	}
	
	public void iniciarFecha() {		
		Date date = new Date();
		fecha_desde.setDate(date);
		fecha_hasta.setDate(date);
	}
	
	public void setearApariencia() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}
	}
	
	public void centrar() {
		Toolkit toolkit =  getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
}
