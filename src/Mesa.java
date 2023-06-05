import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


import java.awt.SystemColor;
import javax.swing.JTextField;

public class Mesa extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JTextField textField;
	private static JScrollPane scrollPane_1;
	private static JTable table_1;
	private static Conector con = new Conector();
	private static JScrollPane scrollPane_2;
	private static JTable table_2;
	private static ArrayList<ArticuloMesa> articulosMesa= new ArrayList();
	private static DefaultTableModel modeloArticulosMesa = new DefaultTableModel();
	private static int numeroMesa=0;
	private JScrollPane scrollPane;
//	private static GUI gui = new GUI();
	private static JLabel saldo_final;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mesa frame = new Mesa(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param NumeroMesa 
	 */
	public Mesa(int NumeroMesa) {
		numeroMesa = NumeroMesa;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(446, 95, 731, 518);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Hora", "Articulo", "Cant", "Descripcion", "Precio", "Total", "Observacion"
			}
		));
		scrollPane.setViewportView(table);
		
		saldo_final = new JLabel("$0.00");
		saldo_final.setForeground(new Color(0, 255, 255));
		saldo_final.setFont(new Font("Arial", Font.BOLD, 24));
		saldo_final.setHorizontalAlignment(SwingConstants.RIGHT);
		saldo_final.setBounds(1066, 639, 188, 31);
		contentPane.add(saldo_final);
		
		JLabel lblNewLabel_2 = new JLabel("Total:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(1066, 624, 188, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Descuento:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(836, 621, 105, 22);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Subtotal:");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_2_2.setBounds(721, 621, 105, 22);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("$0.00");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(606, 639, 105, 31);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_2_3 = new JLabel("Seleccionado:");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_2_3.setBounds(606, 621, 105, 22);
		contentPane.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("$0.00");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_3_1.setBounds(836, 639, 105, 31);
		contentPane.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("$0.00");
		lblNewLabel_1_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_2.setForeground(Color.WHITE);
		lblNewLabel_1_3_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_3_2.setBounds(721, 639, 105, 31);
		contentPane.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Recargo:");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_2_1_1.setBounds(951, 621, 105, 22);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("$0.00");
		lblNewLabel_1_3_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_3_1_1.setBounds(951, 639, 105, 31);
		contentPane.add(lblNewLabel_1_3_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 1244, 55);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnDescuento = new JButton("Descuento");
		btnDescuento.setBounds(10, 7, 113, 41);
		panel_2.add(btnDescuento);
		
		JButton btnRecargo = new JButton("Recargo");
		btnRecargo.setBounds(133, 7, 113, 41);
		panel_2.add(btnRecargo);
		
		JButton btnM = new JButton("Reservar");
		btnM.setBounds(256, 7, 113, 41);
		panel_2.add(btnM);
		
		JButton btnCambiarMesa = new JButton("Cambiar Mesa");
		btnCambiarMesa.setBounds(379, 7, 127, 41);
		panel_2.add(btnCambiarMesa);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(944, 7, 140, 41);
		panel_2.add(btnCancelar);
		
		JButton btnNewButton = new JButton("Confirmar pedido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				imprimirTicket();
				
				
			}
		});
		btnNewButton.setBounds(1094, 7, 140, 41);
		panel_2.add(btnNewButton);
		
		JButton btnCerrarCuenta = new JButton("Cerrar Cuenta");
		btnCerrarCuenta.setBounds(516, 7, 127, 41);
		panel_2.add(btnCerrarCuenta);
		
		JLabel lblMesa = new JLabel("Mesa: 1");
		lblMesa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMesa.setForeground(Color.WHITE);
		lblMesa.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblMesa.setBounds(154, 62, 115, 31);
		contentPane.add(lblMesa);
		
		JLabel lblEstadoDisponible = new JLabel("Estado: Disponible");
		lblEstadoDisponible.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstadoDisponible.setForeground(Color.WHITE);
		lblEstadoDisponible.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblEstadoDisponible.setBounds(692, 62, 131, 31);
		contentPane.add(lblEstadoDisponible);
		
		JLabel lblMeseroRodrigo = new JLabel("Mesero: Rodrigo");
		lblMeseroRodrigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMeseroRodrigo.setForeground(Color.WHITE);
		lblMeseroRodrigo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblMeseroRodrigo.setBounds(977, 62, 131, 31);
		contentPane.add(lblMeseroRodrigo);
		
		JLabel lblCuentaAbierta = new JLabel("Cuenta: Abierta");
		lblCuentaAbierta.setHorizontalAlignment(SwingConstants.LEFT);
		lblCuentaAbierta.setForeground(Color.WHITE);
		lblCuentaAbierta.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblCuentaAbierta.setBounds(423, 62, 115, 31);
		contentPane.add(lblCuentaAbierta);
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.setBounds(1187, 98, 67, 55);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("$");
		btnNewButton_1_1.setBounds(1187, 164, 67, 55);
		contentPane.add(btnNewButton_1_1);
		
		textField = new JTextField();
		textField.setBorder(new TitledBorder(null, "Nota", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField.setBounds(10, 624, 337, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 95, 208, 518);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(228, 95, 208, 518);
		contentPane.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		iniciarTodo();
		
		
	}

	public void iniciarTodo() {		
		centrar();
		setearApariencia();
		iniciarBotonesCategorias();
		iniciarArticulosMesa(numeroMesa);
	}
	
	public void iniciarBotonesCategorias() {
		
		ArrayList<JButton> buttonList = new ArrayList<>();
		
		con.conectar();
		ArrayList<Categoria> categorias = con.getCategorias();
		con.cerrarConexion();
		
		for (int i = 0; i < categorias.size(); i++) {
		    final int index = i;
		    final String descripcion = categorias.get(index).getDescripcion();
		    buttonList.add(createButton("<html><center>"+descripcion, e -> iniciarArticulos(categorias.get(index).getDescripcion())));            
		}
		
        for (JButton button : buttonList) {
        	scrollPane_1.add(button);
        }
        
		MyTableModel model = new MyTableModel(buttonList);
        
        table_1.setModel(model);
       
        ButtonRenderer buttonRenderer = new ButtonRenderer();
        buttonRenderer.setFont(new Font("Console", Font.BOLD, 15));
        table_1.setDefaultRenderer(Object.class, buttonRenderer);

        table_1.setDefaultEditor(Object.class, new ButtonEditor());        
        table_1.setRowHeight(80);
        
        
	}
	
	private static JButton createButton(String text, ActionListener actionListener) {
		// M�todo para crear un bot�n con un texto y acci�n espec�ficos
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }
        
    @FunctionalInterface
    private interface MyActionListener extends ActionListener {
    	// Interfaz funcional gen�rica para representar ActionListener
        @Override
        void actionPerformed(ActionEvent e);
    }
	
    private static void iniciarArticulos(String categoria) {
    	
    	con.conectar();
    	ArrayList<Articulo> articulos = con.getArticulos(categoria);
    	con.cerrarConexion();
    	
    	ArrayList<JButton> buttonList = new ArrayList<>();
    	
    	for (int i = 0; i < articulos.size(); i++) {
		    final int index = i;
		    final String descripcion = articulos.get(index).getDescripcion();
		    final String precio = String.valueOf(articulos.get(index).getPrecio());
		    buttonList.add(createButton("<html><center>"+descripcion+"<br>"+precio+"<html><center>", e -> sumarArticulo(articulos.get(index).getCodigo())));            
		}
    	
    	for (JButton button : buttonList) {
        	scrollPane_2.add(button);
        }
    	
    	MyTableModel model = new MyTableModel(buttonList);
        table_2.setModel(model);
       
        
        ButtonRenderer buttonRenderer = new ButtonRenderer();
        buttonRenderer.setFont(new Font("Console", Font.BOLD, 15));
        table_2.setDefaultRenderer(Object.class, buttonRenderer);

        table_2.setDefaultEditor(Object.class, new ButtonEditor());        
        table_2.setRowHeight(90);
        
    	
    }
    
    public static void sumarArticulo(int codigo){
    	
    	con.conectar();
    	ArrayList<ArticuloMesa> articulosMesas = con.getArticulosMesa(numeroMesa);
    	con.cerrarConexion();
    	
    	boolean existe =false;
    	int indice = 0;
    	
    	for(int i = 0; i<articulosMesas.size();i++) {
    		ArticuloMesa a = articulosMesas.get(i);    		
    		existe = existe || a.getArticulo_codigo().equals(codigo);
    		if(existe) {
    			indice = i;
    		}
    	}
    	
    	ArticuloMesa a = new ArticuloMesa();
    	a.setMesa_numero(numeroMesa);
    	a.setArticulo_codigo(""+codigo);
    	
    	a.setHora(getHora());
    	
    	con.conectar();
    	a.setArticulo_descripcion(con.getArticulo(""+codigo).getDescripcion());
    	con.cerrarConexion();
    	
    	double cantidad = 0;
    	
    	if(existe) {
    		System.out.println("YA EXISTE");
    		cantidad = articulosMesas.get(indice).getCantidad();
    		cantidad+=1;
    		a.setCantidad(cantidad);
    	}else {
    		cantidad=1;
    		a.setCantidad(1);
    	}
    	
    	con.conectar();
    	a.setPrecio(con.getArticulo(""+codigo).getPrecio());
    	con.cerrarConexion();
    	
    	con.conectar();
    	a.setTotal(con.getArticulo(""+codigo).getPrecio()*cantidad);
    	con.cerrarConexion();
    	
    	con.conectar();
    	con.guardarMesa(numeroMesa,a);
    	con.cerrarConexion();
    	
    	iniciarArticulosMesa(numeroMesa);    	
    	
    }
    
    public static void iniciarArticulosMesa(int numeroMesa) {
    	modeloArticulosMesa = new DefaultTableModel();
    	//table.setDefaultRenderer(Object.class,gui);
    	
    	
    	con.conectar();
    	ArrayList<ArticuloMesa> articulos = con.getArticulosMesa(numeroMesa);
    	con.cerrarConexion();
    	
    	
    	modeloArticulosMesa.addColumn("Hora");
    	modeloArticulosMesa.addColumn("Articulo");
    	modeloArticulosMesa.addColumn("Descripcion");
    	modeloArticulosMesa.addColumn("Cant");
    	modeloArticulosMesa.addColumn("Precio");
		modeloArticulosMesa.addColumn("Total");
		modeloArticulosMesa.addColumn("Observacion");
    	
		double saldo = 0;
		
		for(int i = 0 ; i< articulos.size();i++) {
			ArticuloMesa a = articulos.get(i);
			saldo=saldo + a.getTotal();
			actualizarSaldo(saldo);
			modeloArticulosMesa.addRow(new Object[] {a.getHora(),a.getArticulo_codigo(),a.getArticulo_descripcion(),a.getCantidad(),"$"+a.getPrecio(),a.getTotal(),a.getObservacion()});
			
		}
		actualizarSaldo(saldo);
		table.setModel(modeloArticulosMesa);
		//table.setDefaultRenderer(Object.class,gui);
		
    }
    
    public static void actualizarSaldo(double saldo) {
    	saldo_final.setText(String.valueOf(saldo));
    }
    
    public static void imprimir() throws PrintException {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

        if (services.length > 0) {
            // Obtener la impresora por defecto (puedes seleccionar una impresora espec�fica si lo deseas)
            PrintService printService = services[0];
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            
            try {
				printerJob.setPrintService(printService);
			} catch (PrinterException e) {
				e.printStackTrace();
			}

            Printable printable = new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex != 0) {
                        return NO_SUCH_PAGE;
                    }

                    // Configurar los estilos de fuente y tama�o
                    Font font = new Font("Courier New", Font.PLAIN, 10);
                    graphics.setFont(font);

                    // Establecer la posici�n inicial del contenido del ticket
                    int x = 10;
                    int y = 10;

                    // Imprimir los detalles de los art�culos
                    for (int i = 0; i < articulosMesa.size(); i++) {
                        ArticuloMesa a= articulosMesa.get(i);
                        String linea = String.format("%-20s %6.2f %4d", a.getArticulo_descripcion(),"x", a.getCantidad(), a.getTotal());
                        graphics.drawString(linea, x, y + i * 15);
                    }

                    // Imprimir el total de la operaci�n
                    graphics.drawString("-------------------------------", x, y + articulosMesa.size() * 15);
                    graphics.drawString(String.format("Total: %.2f", totalMesa(numeroMesa)), x, y + (articulosMesa.size() + 1) * 15);

                    return PAGE_EXISTS;
                }
            };

            // Asignar el objeto Printable al PrinterJob
            printerJob.setPrintable(printable);

            // Mostrar el di�logo de impresi�n y enviar el trabajo de impresi�n
            if (printerJob.printDialog()) {
                try {
					printerJob.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }
    
    public static double totalMesa(int numeroMesa) {
    	double total = 0;
    	
    	con.conectar();
    	total = con.getTotalMesa(numeroMesa);
    	con.cerrarConexion();
    	
    	return total;
    }
    
    public void imprimirTicket() {
    	JTextArea ticket = new javax.swing.JTextArea();
    	String mesero = "Rodrigo";
    	
    	try {
    		ticket.setFont(new Font("Arial",Font.PLAIN,7));
    		ticket.setText("             Parrilla el Pa \n");
    		ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
    		ticket.setText(ticket.getText() + "Fecha: "+getFecha()+" "+getHora()+"\n");
    		ticket.setText(ticket.getText() + "Mesa: "+numeroMesa+"\n");
    		ticket.setText(ticket.getText() + "Mesero: "+mesero+"\n");
            ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
            
            con.conectar();
            ArrayList<ArticuloMesa> articulos = con.getArticulosMesa(0);
            con.cerrarConexion();
            
            ticket.setText(ticket.getText() + "Cant.\t Descripcion\n");
            ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
            for (int i = 0; i < articulos.size(); i++) {
                ArticuloMesa a = articulos.get(i);
            	
                String nombre = a.getArticulo_descripcion();
                String cant = String.valueOf(a.getCantidad());
                String precio = String.valueOf(a.getPrecio());
                String total = String.valueOf(a.getTotal());
                
                ticket.setText(ticket.getText() +quitarDecimal(cant)+"\t"+ nombre+"\t"+"\n");
                ticket.setText(ticket.getText() +"$"+quitarDecimal(total)+" \n");
            }
            ticket.setText(ticket.getText() + "----------------------------------------------------------------\n");
            con.conectar();
            double total = con.getTotalMesa(0);
            con.cerrarConexion();
            
            ticket.setText(ticket.getText() + "Total : $"+formatearDouble(total)+"\n");
            ticket.setText(ticket.getText() + "====================================\n");
            ticket.setText(ticket.getText() + "Muchas gracias por elegirnos! "+"\n");
            
            imprimirTextArea(ticket);
            //ticket.print();
            
        //} catch (PrinterException ex) {
        } catch (Exception ex) {
        	System.out.println("Error al imprimir "+ex);
        	
		}
        
    }
    
    public static void imprimirTextArea(JTextArea textArea) {
    	PrinterJob printerJob = PrinterJob.getPrinterJob();

        // Crear un objeto PageFormat con los m�rgenes personalizados
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        double margin = 10; // Tama�o de los m�rgenes en puntos
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
        pageFormat.setPaper(paper);

        // Crear un Printable personalizado para imprimir el JTextArea
        Printable printable = textArea.getPrintable(null, null);

        try {
            // Establecer el Printable y PageFormat en el PrinterJob
            printerJob.setPrintable(printable, pageFormat);

            // Mostrar el cuadro de di�logo de impresi�n
            if (printerJob.printDialog()) {
                // Imprimir el JTextArea con los m�rgenes personalizados
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
    
    static class MyTableModel extends AbstractTableModel {
    	// Clase de modelo de datos personalizado
        private ArrayList<JButton> buttonList;
        private String[] columnNames = {""};

        public MyTableModel(ArrayList<JButton> buttonList) {
            this.buttonList = buttonList;
        }

        @Override
        public int getRowCount() {
            return buttonList.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            return buttonList.get(row);
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return true;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            // No es necesario implementar este m�todo para botones
        }
    }

    static class ButtonRenderer extends DefaultTableCellRenderer {
    	// Clase de renderizado personalizado para mostrar los botones en las celdas
        private Font font;

        public ButtonRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            if (value instanceof Component) {
                Component component = (Component) value;
                if (font != null) {
                    component.setFont(font);
                }
                return component;
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }

        public void setFont(Font font) {
            this.font = font;
        }
    }

    static class ButtonEditor extends DefaultCellEditor {
    	// Clase de editor personalizado para permitir la interacci�n con los botones en las celdas
        private JButton button;

        public ButtonEditor() {
            super(new JTextField());
            setClickCountToStart(1);

            button = new JButton();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                     int row, int column) {
            if (value instanceof Component) {
                button = (JButton) value;
            }

            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button;
        }
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
	
	public void maximizar() {
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}
}
