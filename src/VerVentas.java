import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.record.chart.DefaultDataLabelTextPropertiesRecord;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class VerVentas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JDateChooser fecha_desde;
	private JDateChooser fecha_hasta;
	private JLabel TotalMesas;
	private JLabel TotalIngresos;
	private Conector con = new Conector();
	private DefaultTableModel modelo = new DefaultTableModel();
	private DefaultTableModel modeloValores = new DefaultTableModel();
	private JTable table_1;

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
		panel.setBounds(1015, 596, 239, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TotalMesas = new JLabel("Total Mesas:");
		TotalMesas.setHorizontalAlignment(SwingConstants.LEFT);
		TotalMesas.setFont(new Font("Tahoma", Font.BOLD, 13));
		TotalMesas.setBounds(10, 11, 219, 20);
		panel.add(TotalMesas);
		
		TotalIngresos = new JLabel("Total Ingresos:");
		TotalIngresos.setHorizontalAlignment(SwingConstants.LEFT);
		TotalIngresos.setFont(new Font("Tahoma", Font.BOLD, 13));
		TotalIngresos.setBounds(10, 43, 219, 20);
		panel.add(TotalIngresos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 995, 588);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 11, 1244, 60);
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
		
		JButton btnNewButton = new JButton("Imprimir Ticket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Primero seleccione una venta");
				}else {
					imprimirTicket();					
				}
			}
		});
		btnNewButton.setBounds(1023, 31, 211, 23);
		panel_1.add(btnNewButton);
		
		JButton btnVerFormasDe = new JButton("Ver Formas de Pago");
		btnVerFormasDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Primero seleccione una venta");
				}else {
					int numero = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
					VerValoresVenta v = new VerValoresVenta(numero);
					v.setVisible(true);
				}
			}
		});
		btnVerFormasDe.setBounds(802, 31, 211, 23);
		panel_1.add(btnVerFormasDe);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1015, 82, 239, 503);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		iniciarTodo();
	}
	
	public void iniciarTodo() {
		iniciarFecha();
		centrar();
		setearApariencia();
		iniciarMontos();
		iniciarVentas();
		iniciarVentasValores();
	}
	
	public void iniciarVentasValores() {
		
		modeloValores = new DefaultTableModel();
				
		String[] columnNames = {"Forma de pago", "Monto"};
		
		modeloValores = new DefaultTableModel(columnNames, 0);
		
		String FechaDesde = getFechaByDate(fecha_desde.getDate());
		String FechaHasta = getFechaByDate(fecha_hasta.getDate());
		
		con.conectar();
		con.getValoresVentasFechas(modeloValores, FechaDesde, FechaHasta);
		con.cerrarConexion();
		
		table_1.setModel(modeloValores);
		
		
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

	public void imprimirTicket() {
    	JTextArea ticket = new javax.swing.JTextArea();
    	    	
		int numeroVenta = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
		
		con.conectar();
		Venta v = con.getVenta(numeroVenta);
		con.cerrarConexion();
		
    	
    	try {
    		ticket.setFont(new Font("Arial",Font.PLAIN,7));
    		ticket.setText("\t\tParrilla el Pa \n");
    		ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
    		ticket.setText(ticket.getText() + "Fecha: "+v.getFecha()+" "+v.getHora()+"\n");
    		ticket.setText(ticket.getText() + "Mesa: "+v.getMesa_numero()+"\n");
    		ticket.setText(ticket.getText() + "Mesero: "+v.getMesero()+"\n");
    		ticket.setText(ticket.getText() + "Venta N°: "+numeroVenta+"\n");
            ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
            
            con.conectar();
            ArrayList<ArticuloMesa> articulos = con.getArticulosMesaVenta(numeroVenta);
            con.cerrarConexion();
            
            ticket.setText(ticket.getText() + "Art\t\t    Precio\n");
            ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
            for (int i = 0; i < articulos.size(); i++) {
                ArticuloMesa a = articulos.get(i);
            	
                String nombre = a.getArticulo_descripcion();
                String cant = String.valueOf(a.getCantidad());
                String precio = String.valueOf(a.getPrecio());
                
                ticket.setText(ticket.getText() +"("+quitarDecimal(cant)+")"+limitarLongitud(nombre,28)+"\t    "+"$"+quitarDecimal(precio)+"\n");
                
            }
            ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
            
            double Total = v.getPrecio() - v.getDescuento() + v.getRecargo();
            
            
            if(v.getDescuento()>0) {
            	ticket.setText(ticket.getText() + "Descuento : $"+formatearDouble(v.getDescuento())+"\n");            	
            }
            
            if(v.getRecargo()>0) {
            	ticket.setText(ticket.getText() + "Recargo : $"+formatearDouble(v.getRecargo())+"\n");          	
            }
            ticket.setText(ticket.getText() + "Total : $"+formatearDouble(Total)+"\n");
            ticket.setText(ticket.getText() + "====================================\n");
            ticket.setText(ticket.getText() + "Muchas gracias por elegirnos! "+"\n");
            
            imprimirTextArea(ticket);
            //ticket.print();
            
        //} catch (PrinterException ex) {
        } catch (Exception ex) {
        	System.out.println("Error al imprimir "+ex);
        	
		}
        
    }
	
	public String limitarLongitud(String texto,int longitud) {
        if (texto.length() >= longitud) {
            return texto.substring(0, longitud); // Limitar el tamaño a 30 caracteres
        } else {
            String formato = "%-"+longitud+"s"; // Formato para agregar espacios al final
            return String.format(formato, texto); // Agregar espacios si es necesario
        }
    }

	public static void imprimirTextArea(JTextArea textArea) {
    	PrinterJob printerJob = PrinterJob.getPrinterJob();

        // Crear un objeto PageFormat con los mï¿½rgenes personalizados
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        double margin = 10; // Tamaï¿½o de los mï¿½rgenes en puntos
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
        pageFormat.setPaper(paper);

        // Crear un Printable personalizado para imprimir el JTextArea
        Printable printable = textArea.getPrintable(null, null);

        try {
            // Establecer el Printable y PageFormat en el PrinterJob
            printerJob.setPrintable(printable, pageFormat);

            // Mostrar el cuadro de diï¿½logo de impresiï¿½n
            if (printerJob.printDialog()) {
                // Imprimir el JTextArea con los mï¿½rgenes personalizados
                printerJob.print();
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
        
    public static String formatearDouble(double valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');

        DecimalFormat formato = new DecimalFormat("#,##0.00", simbolos);

        return formato.format(valor);
    }
    
    public String quitarDecimal(String valor) {
        if (valor.endsWith(".0")) {
            return valor.substring(0, valor.length() - 2);
        } else {
            return valor;
        }
    }

    public static String getHora() {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
		return formateador.format(LocalDateTime.now());
	}
		
    public String getFecha() {
		LocalDate fechaHoy = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
		String fechaFinal=fechaHoy.format(formato).toString();
		return fechaFinal;	
	}
}
