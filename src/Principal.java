

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
		
		JButton btnNewButton_11_1_2 = new JButton("Cerrar Caja");
		btnNewButton_11_1_2.setBounds(1077, 6, 157, 90);
		panel.add(btnNewButton_11_1_2);
		
		JButton btnNewButton_11_1_2_1 = new JButton("Ver Ventas del Dia");
		btnNewButton_11_1_2_1.setBounds(10, 6, 157, 90);
		panel.add(btnNewButton_11_1_2_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 1244, 546);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_11 = new JButton("MESA 20");
		btnNewButton_11.setBounds(1020, 298, 90, 90);
		panel_1.add(btnNewButton_11);
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(20);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_8 = new JButton("MESA 15");
		btnNewButton_8.setBounds(1020, 201, 90, 90);
		panel_1.add(btnNewButton_8);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(15);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_5 = new JButton("MESA 10");
		btnNewButton_5.setBounds(1020, 104, 90, 90);
		panel_1.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(10);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_2 = new JButton("MESA 5");
		btnNewButton_2.setBounds(1020, 7, 90, 90);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(5);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_1 = new JButton("MESA 4");
		btnNewButton_1.setBounds(798, 7, 90, 90);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(4);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_4 = new JButton("MESA 9");
		btnNewButton_4.setBounds(798, 104, 90, 90);
		panel_1.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(9);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_7 = new JButton("MESA 14");
		btnNewButton_7.setBounds(798, 201, 90, 90);
		panel_1.add(btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(14);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_10 = new JButton("MESA 19");
		btnNewButton_10.setBounds(798, 298, 90, 90);
		panel_1.add(btnNewButton_10);
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(19);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_9 = new JButton("MESA 18");
		btnNewButton_9.setBounds(576, 298, 90, 90);
		panel_1.add(btnNewButton_9);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(18);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_9_1 = new JButton("MESA 17");
		btnNewButton_9_1.setBounds(354, 298, 90, 90);
		panel_1.add(btnNewButton_9_1);
		btnNewButton_9_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(17);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_9_2 = new JButton("MESA 16");
		btnNewButton_9_2.setBounds(132, 298, 90, 90);
		panel_1.add(btnNewButton_9_2);
		btnNewButton_9_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(16);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_6_2 = new JButton("MESA 11");
		btnNewButton_6_2.setBounds(132, 201, 90, 90);
		panel_1.add(btnNewButton_6_2);
		btnNewButton_6_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(11);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_6_1 = new JButton("MESA 12");
		btnNewButton_6_1.setBounds(354, 201, 90, 90);
		panel_1.add(btnNewButton_6_1);
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(12);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_6 = new JButton("MESA 13");
		btnNewButton_6.setBounds(576, 201, 90, 90);
		panel_1.add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(13);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_3 = new JButton("MESA 8");
		btnNewButton_3.setBounds(576, 104, 90, 90);
		panel_1.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(8);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_3_1 = new JButton("MESA 7");
		btnNewButton_3_1.setBounds(354, 104, 90, 90);
		panel_1.add(btnNewButton_3_1);
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(7);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_3_2 = new JButton("MESA 6");
		btnNewButton_3_2.setBounds(132, 104, 90, 90);
		panel_1.add(btnNewButton_3_2);
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(6);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton_12 = new JButton("MESA 2");
		btnNewButton_12.setBounds(354, 7, 90, 90);
		panel_1.add(btnNewButton_12);
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(2);
				m.setVisible(true);				
			}
		});
		
		
		JButton btnNewButton_13 = new JButton("MESA 3");
		btnNewButton_13.setBounds(576, 7, 90, 90);
		panel_1.add(btnNewButton_13);
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(3);
				m.setVisible(true);				
			}
		});
		
		JButton btnNewButton = new JButton("MESA 1");
		btnNewButton.setBounds(132, 7, 90, 90);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(1);
				m.setVisible(true);
				btnNewButton.setBackground(Color.red);
			}
		});
		
		JButton btnNewButton_11_1_1 = new JButton("DELIVERY");
		btnNewButton_11_1_1.setBounds(788, 445, 122, 90);
		panel_1.add(btnNewButton_11_1_1);
		
		
		
		JButton btnNewButton_11_1 = new JButton("PARA LLEVAR");
		btnNewButton_11_1.setBounds(333, 445, 122, 90);
		panel_1.add(btnNewButton_11_1);
		
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
