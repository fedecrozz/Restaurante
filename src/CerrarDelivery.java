import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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

public class CerrarDelivery extends JFrame {

	private JPanel contentPane;
	private MesaClase mesaClase;
	private JTable table;
    private DefaultTableModel tableModel;
    private JTable table_1;
    private JScrollPane scrollPane;
    private JLabel total;
    private Conector con = new Conector();
    private Delivery Mesa;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CerrarDelivery frame = new CerrarDelivery(null,null);
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
	public CerrarDelivery(Delivery sinMesa,MesaClase m) {
		Mesa = sinMesa;
		mesaClase = m ;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 493, 569);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cerrar Mesa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarMesa();
			}
		});
		btnNewButton.setBounds(179, 480, 118, 39);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 457, 418);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		total = new JLabel("Total:");
		total.setHorizontalAlignment(SwingConstants.CENTER);
		total.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		total.setForeground(Color.WHITE);
		total.setBounds(10, 11, 457, 29);
		contentPane.add(total);
		iniciarTodo();
	}

	public void iniciarTodo() {
		centrar();
		setearApariencia();
		iniciarMonto();
		iniciarValores();
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
		
	
	public String quitarDecimal(String valor) {
        if (valor.endsWith(".0")) {
            return valor.substring(0, valor.length() - 2);
        } else {
            return valor;
        }
    }
	
	public void cerrarMesa() {
		table.editCellAt(0, 0);
		Venta v= new Venta();
		v.setMesa_numero(mesaClase.getNumero());
		v.setPrecio(mesaClase.getTotal());
		v.setFecha(getFechaByDate(new Date()));
		v.setHora(getHora());
		v.setDescuento(mesaClase.getDescuento());
		v.setRecargo(mesaClase.getRecargo());
		v.setMesero(mesaClase.getMesero());
		v.setObservacion(mesaClase.getObservacion());
		
		con.conectar();
		con.guardarVenta(v);
		con.cerrarConexion();
		
		con.conectar();		
		int ultimo = con.getNumeroUltimaVenta();
		con.cerrarConexion();
		
		double monto = v.getPrecio() -v.getDescuento() +v.getRecargo();
		
		con.conectar();
		ArrayList<String> valores= con.getValores();
		con.cerrarConexion();
		
		for(int i = 0; i< table.getRowCount();i++) {
			if(!table.getValueAt(i, 1).toString().equals("")) {
				String query = "insert into VALORES_VENTA (venta_numero,valor,monto) values ("+ultimo+",'"+table.getValueAt(i, 0).toString()+"',"+table.getValueAt(i, 1).toString()+")";
				
				con.conectar();
		    	con.ejecutarQuery(query);
		    	con.cerrarConexion();
			
			
			}
		}
		
		
    	guardarArticulosVenta();
		imprimirTicket();
    	Mesa.cancelarMesa();
    	Mesa.principal.iniciarMesas();
    	Mesa.dispose();
    	this.dispose();
	}
	
	public void guardarArticulosVenta() {
		con.conectar();
		ArrayList<ArticuloMesa> articulos = con.getArticulosMesa(mesaClase.getNumero());	
		con.cerrarConexion();
		
		con.conectar();		
		int ultimo = con.getNumeroUltimaVenta();
		con.cerrarConexion();
		
		for(int i = 0 ; i< articulos.size(); i++) {
			ArticuloMesa a = articulos.get(i);
			String query = "insert into VENTAS_ARTICULOS (venta_numero,articulo_codigo,articulo_descripcion,precio,total,cantidad,hora) values ("+ultimo+","+
																																					   "'"+a.getArticulo_codigo()+"',"+
																																					   "'"+a.getArticulo_descripcion()+"',"+
																																					   a.getPrecio()+","+
																																					   a.getTotal()+","+
																																					   a.getCantidad()+","+
																																					   "'"+a.getHora()+"')";
			
			con.conectar();
	    	con.ejecutarQuery(query);
	    	con.cerrarConexion();
		}
		
		
	}
	
	public void iniciarMonto() {
		double Total = mesaClase.getTotal() - mesaClase.getDescuento() + mesaClase.getRecargo();
		total.setText("Total: $"+quitarDecimal(""+Total));
	}
	
	public void iniciarValores() {
		
		con.conectar();
		ArrayList<String> valores = con.getValores();
		con.cerrarConexion();
		
		String[] columnNames = {"Valor", "Monto", "Acción"};
		
		Object[][] data = new Object[valores.size()][columnNames.length];
		
		for(int i = 0; i<valores.size();i++) {
			data[i][0] = valores.get(i);
			data[i][1] = "";
		}
		
        

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        int rowHeight = 50; // Altura en píxeles
        table.setRowHeight(rowHeight);
        
        table.setModel(tableModel);

        TableColumn buttonColumn = table.getColumnModel().getColumn(2);
        buttonColumn.setCellRenderer(new ButtonRenderer());
        buttonColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

	}
	
	// Clase para renderizar los botones en la columna
   class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String nombre = (String) table.getValueAt(row, 0); // Obtener el valor de la primera columna
        setText("Todo " + nombre); // Concatenar el nombre en el texto del botón
        
        // Verificar si hay texto en la segunda columna
        String monto = (String) table.getValueAt(row, 1);
        if (monto != null && !monto.isEmpty()) {
            setEnabled(false); // Deshabilitar el botón si hay texto en la segunda columna
        } else {
            setEnabled(true); // Habilitar el botón si no hay texto en la segunda columna
        }
        
        return this;
    }
}

    // Clase para editar los botones en la columna
    class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private String label;
        private boolean clicked;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);

            // Agregar ActionListener para responder a los clics en el botón
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    // Ejecutar el método correspondiente al botón clickeado
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Obtener los datos de la fila seleccionada
                        String valor = (String) table.getValueAt(selectedRow, 0);
                        String monto = (String) table.getValueAt(selectedRow, 1);
                        
                        int input = JOptionPane.showConfirmDialog(null,"Desea cerrar la mesa unicamente abonando con "+valor);
                        if(input ==0) {
                        	guardarVenta(valor);
                        }
                        
                    }
                }
            });
        }

        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            // ...

            // Verificar si hay texto en la segunda columna
            String monto = (String) table.getValueAt(row, 1);
            if (monto != null && !monto.isEmpty()) {
                button.setEnabled(false); // Deshabilitar el botón si hay texto en la segunda columna
            } else {
                button.setEnabled(true); // Habilitar el botón si no hay texto en la segunda columna
            }
            
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                // Si se hizo clic en el botón
                return label;
            } else {
                // Si no se hizo clic en el botón
                return null;
            }
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
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
       
    public void guardarVenta(String valor) {
    	//guarda la venta con un unico valor 
    	
    	Venta v= new Venta();
		v.setMesa_numero(mesaClase.getNumero());
		v.setPrecio(mesaClase.getTotal());
		v.setFecha(getFechaByDate(new Date()));
		v.setHora(getHora());
		v.setDescuento(mesaClase.getDescuento());
		v.setRecargo(mesaClase.getRecargo());
		v.setMesero(mesaClase.getMesero());
		v.setObservacion(mesaClase.getObservacion());
		
		con.conectar();
		con.guardarVenta(v);
		con.cerrarConexion();
		
		con.conectar();		
		int ultimo = con.getNumeroUltimaVenta();
		con.cerrarConexion();
		
		double monto = v.getPrecio() -v.getDescuento() +v.getRecargo();
		
		String query = "insert into VALORES_VENTA (venta_numero,valor,monto) values ("+ultimo+",'"+valor+"',"+monto+")";
		
    	con.conectar();
    	con.ejecutarQuery(query);
    	con.cerrarConexion();
    	
    	guardarArticulosVenta();
    	imprimirTicket();
    	Mesa.cancelarMesa();
    	Mesa.principal.iniciarMesas();
    	Mesa.dispose();
    	this.dispose();
    	
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
    
    public void imprimirTicket() {
    	JTextArea ticket = new javax.swing.JTextArea();
    	String mesero = mesaClase.getMesero();
    	
    	con.conectar();		
		int ultimo = con.getNumeroUltimaVenta();
		con.cerrarConexion();
    	
    	try {
    		ticket.setFont(new Font("Arial",Font.PLAIN,7));
    		ticket.setText("\t\tParrilla el Pa \n");
    		ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
    		ticket.setText(ticket.getText() + "Fecha: "+getFecha()+" "+getHora()+"\n");
    		ticket.setText(ticket.getText() + "DELIVERY \n");
    		ticket.setText(ticket.getText() + "Venta N°: "+ultimo+"\n");
            ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
            
            con.conectar();
            ArrayList<ArticuloMesa> articulos = con.getArticulosMesa(mesaClase.getNumero());
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
            
            double Total = mesaClase.getTotal() - mesaClase.getDescuento() + mesaClase.getRecargo();
            
            
            if(mesaClase.getDescuento()>0) {
            	ticket.setText(ticket.getText() + "Descuento : $"+formatearDouble(mesaClase.getDescuento())+"\n");            	
            }
            
            if(mesaClase.getRecargo()>0) {
            	ticket.setText(ticket.getText() + "Recargo : $"+formatearDouble(mesaClase.getRecargo())+"\n");          	
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
    
}
