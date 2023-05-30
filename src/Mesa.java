

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import javax.swing.JTextField;

public class Mesa extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mesa frame = new Mesa();
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
	public Mesa() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(357, 95, 820, 518);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(10, 95, 337, 518);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("$0.00");
		lblNewLabel_1.setForeground(new Color(0, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(1066, 639, 188, 31);
		contentPane.add(lblNewLabel_1);
		
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
		textField.setBounds(10, 624, 337, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		iniciarTodo();
	}

	public void iniciarTodo() {		
		centrar();
		setearApariencia();
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
