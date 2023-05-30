

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
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 725);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("MESA 1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa();
				m.setVisible(true);
			}
		});
		btnNewButton.setBounds(193, 107, 90, 90);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("MESA 1");
		btnNewButton_1.setBounds(493, 107, 90, 90);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("MESA 1");
		btnNewButton_2.setBounds(593, 107, 90, 90);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("MESA 1");
		btnNewButton_3.setBounds(393, 208, 90, 90);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("MESA 1");
		btnNewButton_4.setBounds(493, 208, 90, 90);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("MESA 1");
		btnNewButton_5.setBounds(593, 208, 90, 90);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("MESA 1");
		btnNewButton_6.setBounds(393, 309, 90, 90);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("MESA 1");
		btnNewButton_7.setBounds(493, 309, 90, 90);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("MESA 1");
		btnNewButton_8.setBounds(593, 309, 90, 90);
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("MESA 1");
		btnNewButton_9.setBounds(393, 410, 90, 90);
		contentPane.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("MESA 1");
		btnNewButton_10.setBounds(493, 410, 90, 90);
		contentPane.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("MESA 1");
		btnNewButton_11.setBounds(593, 410, 90, 90);
		contentPane.add(btnNewButton_11);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(693, 107, 481, 492);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "Ocupada", "Abierta", "$6.700,00", "-", "Rodri"},
				{"2", "Ocupada", "Cerrada", "$3.400,00", "No", "RODRI"},
				{"3", "Ocupada", "Cerrada", "$2.000,00", "Si", "Paisa"},
				{"4", "Libre", "Cerrada", "$0,00", "No", "-"},
				{"5", "Libre", "Cerrada", "$0,00", "Si", null},
			},
			new String[] {
				"Mesa", "Estado", "Cuenta", "Precio de cuenta", "Mesa limpia", "Mesero"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1164, 75);
		contentPane.add(panel);
		
		JButton btnNewButton_3_1 = new JButton("MESA 1");
		btnNewButton_3_1.setBounds(293, 208, 90, 90);
		contentPane.add(btnNewButton_3_1);
		
		JButton btnNewButton_6_1 = new JButton("MESA 1");
		btnNewButton_6_1.setBounds(293, 309, 90, 90);
		contentPane.add(btnNewButton_6_1);
		
		JButton btnNewButton_9_1 = new JButton("MESA 1");
		btnNewButton_9_1.setBounds(293, 410, 90, 90);
		contentPane.add(btnNewButton_9_1);
		
		JButton btnNewButton_3_2 = new JButton("MESA 1");
		btnNewButton_3_2.setBounds(193, 208, 90, 90);
		contentPane.add(btnNewButton_3_2);
		
		JButton btnNewButton_6_2 = new JButton("MESA 1");
		btnNewButton_6_2.setBounds(193, 309, 90, 90);
		contentPane.add(btnNewButton_6_2);
		
		JButton btnNewButton_9_2 = new JButton("MESA 1");
		btnNewButton_9_2.setBounds(193, 410, 90, 90);
		contentPane.add(btnNewButton_9_2);
		
		JButton btnNewButton_12 = new JButton("MESA 1");
		btnNewButton_12.setBounds(293, 107, 90, 90);
		contentPane.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("MESA 1");
		btnNewButton_13.setBounds(393, 107, 90, 90);
		contentPane.add(btnNewButton_13);
		
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
