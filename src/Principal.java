

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;


import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

public class Principal extends JFrame {

	private JPanel contentPane;
	private ArrayList<MesaClase> mesas = new ArrayList<>();
	private ArrayList<JButton> botones = new ArrayList<>();
	private Conector con = new Conector();
	private Principal p;
	private JButton btnNewButton_11_1_2;
	private JButton btnNewButton_11_1_2_5;
	private static final int TOTAL_MESAS = 100;
	private static final int MESAS_POR_FILA = 10;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		p = this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 568, 1244, 102);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_11_1_2_1 = new JButton("Meseros");
		btnNewButton_11_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meseros m = new Meseros();
				m.setVisible(true);
			}
		});
		btnNewButton_11_1_2_1.setBounds(712, 6, 163, 90);
		panel.add(btnNewButton_11_1_2_1);
		
		btnNewButton_11_1_2 = new JButton("Ver Ventas");
		btnNewButton_11_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerVentas v = new VerVentas();
				v.setVisible(true);
			}
		});
		btnNewButton_11_1_2.setBounds(1062, 6, 163, 90);
		panel.add(btnNewButton_11_1_2);
		
		JButton btnNewButton_11_1_2_2 = new JButton("Ingresar dinero");
		btnNewButton_11_1_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double ingresoManual = Double.parseDouble(JOptionPane.showInputDialog("Cuanto dinero desea ingresar?"));
				
				con.conectar();
				con.ejecutarQuery("insert into INGRESOS (fecha,hora,monto) values('"+getFechaByDate(new Date())+"', '"+getHora()+"',"+ingresoManual+") ");
				con.cerrarConexion();
				
			}
		});
		btnNewButton_11_1_2_2.setBounds(887, 6, 163, 90);
		panel.add(btnNewButton_11_1_2_2);
		
		JButton btnNewButton_11_1_2_3 = new JButton("Categorias");
		btnNewButton_11_1_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Categorias c = new Categorias();
				c.setVisible(true);
			}
		});
		btnNewButton_11_1_2_3.setBounds(539, 6, 163, 90);
		panel.add(btnNewButton_11_1_2_3);
		
		btnNewButton_11_1_2_5 = new JButton("Categorias DELIVERY");
		btnNewButton_11_1_2_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoriasDelivery c = new CategoriasDelivery();
				c.setVisible(true);
			}
		});
		btnNewButton_11_1_2_5.setBounds(183, 6, 163, 90);
		panel.add(btnNewButton_11_1_2_5);
		
		JButton btnNewButton_11_1_2_4_1 = new JButton("Articulos DELIVERY");
		btnNewButton_11_1_2_4_1.setBounds(10, 6, 163, 90);
		panel.add(btnNewButton_11_1_2_4_1);
		
		JButton btnNewButton_11_1_2_4 = new JButton("Articulos");
		btnNewButton_11_1_2_4.setBounds(366, 6, 163, 90);
		panel.add(btnNewButton_11_1_2_4);
		btnNewButton_11_1_2_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Articulos a = new Articulos();
				a.setVisible(true);
			}
		});
		btnNewButton_11_1_2_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArticulosDelivery a = new ArticulosDelivery();
				a.setVisible(true);
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 1244, 546);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
				
		
		JButton btnNewButton_11_1 = new JButton("SIN MESA");
		btnNewButton_11_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				SinMesa m = new SinMesa(p,0);
				m.setVisible(true);
			}
		});
		btnNewButton_11_1.setBounds(775, 465, 159, 70);
		panel_1.add(btnNewButton_11_1);
		
		// Panel con scroll para las mesas
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new java.awt.GridLayout(0, MESAS_POR_FILA, 10, 10));
		
		JScrollPane scrollPane = new JScrollPane(panel_2);
		scrollPane.setBounds(207, 29, 830, 425);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);
		
		// Crear 100 mesas dinámicamente
		for (int i = 1; i <= TOTAL_MESAS; i++) {
			final int numeroMesa = i;
			JButton btnMesa = new JButton(String.valueOf(numeroMesa));
			btnMesa.setPreferredSize(new Dimension(70, 70));
			
			btnMesa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Mesa m = new Mesa(p, numeroMesa);
					m.setVisible(true);
				}
			});
			
			botones.add(btnMesa);
			panel_2.add(btnMesa);
		}
		
		JLabel lblNewLabel = new JLabel("Mesas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(591, 11, 46, 14);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton_11_1_1 = new JButton("DELIVERY");
		btnNewButton_11_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Delivery d = new Delivery(p, -1);
				d.setVisible(true);
			}
		});
		btnNewButton_11_1_1.setBounds(308, 465, 159, 70);
		panel_1.add(btnNewButton_11_1_1);
		
		iniciarTodo();
	}

	public void iniciarTodo() {		
		centrar();
		//setearApariencia();
		iniciarMesas();
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

	public void iniciarMesas() {

		con.conectar();
		ArrayList<MesaClase> Mesas = con.getMesaClase();
		
		// Crear las mesas faltantes en la base de datos (de la 31 a la 100)
		int mesasExistentes = Mesas.size();
		if(mesasExistentes < TOTAL_MESAS) {
			for(int i = mesasExistentes + 1; i <= TOTAL_MESAS; i++) {
				try {
					con.ejecutarQuery("INSERT INTO MESAS (numero, cuenta, estado, mesero_nombre, total, descuento, recargo, nota) " +
							"VALUES (" + i + ", '', 'disponible', '', 0.0, 0.0, 0.0, '')");
				} catch (Exception e) {
					System.out.println("Error creando mesa " + i + ": " + e.getMessage());
				}
			}
			// Recargar las mesas después de crear las nuevas
			Mesas = con.getMesaClase();
		}
		
		con.cerrarConexion();
		
		// Actualizar el estado visual de los botones
		for(int i = 0; i < Mesas.size() && i < botones.size(); i++) {
			MesaClase m = Mesas.get(i);
			mesas.add(m);
			
			if(m.getEstado().equals("disponible")) {
				botones.get(i).setBackground(Color.WHITE);
			} else {
				botones.get(i).setBackground(Color.RED);
			}
		}
		
		// Inicializar las mesas restantes como disponibles (blancas)
		for(int i = Mesas.size(); i < botones.size(); i++) {
			botones.get(i).setBackground(Color.WHITE);
		}
	}
	
	public String getFecha() {
	  		LocalDate fechaHoy = LocalDate.now();
	  		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
	  		String fechaFinal=fechaHoy.format(formato).toString();
	  		return fechaFinal;	
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
	    
	    public static String getHora() {
			DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
			return formateador.format(LocalDateTime.now());
		}
}
