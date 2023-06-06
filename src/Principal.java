

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Principal extends JFrame {

	private JPanel contentPane;
	private ArrayList<MesaClase> mesas = new ArrayList<>();
	private ArrayList<JButton> botones = new ArrayList<>();
	private Conector con = new Conector();
	private JButton b1;
	private JButton b7;
	private JButton b13;
	private JButton b19;
	private JButton b25;
	private JButton b26;
	private JButton b20;
	private JButton b14;
	private JButton b8;
	private JButton b2;
	private JButton b27;
	private JButton b21;
	private JButton b15;
	private JButton b9;
	private JButton b3;
	private JButton b28;
	private JButton b22;
	private JButton b16;
	private JButton b10;
	private JButton b4;
	private JButton b29;
	private JButton b23;
	private JButton b17;
	private JButton b11;
	private JButton b5;
	private JButton b30;
	private JButton b24;
	private JButton b18;
	private JButton b12;
	private JButton b6;
	private Principal p ;
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
		
		JButton btnNewButton_11_1_1 = new JButton("DELIVERY");
		btnNewButton_11_1_1.setBounds(795, 465, 99, 70);
		panel_1.add(btnNewButton_11_1_1);
				
		
		JButton btnNewButton_11_1 = new JButton("PARA LLEVAR");
		btnNewButton_11_1.setBounds(348, 465, 99, 70);
		panel_1.add(btnNewButton_11_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(207, 29, 830, 425);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		b1 = new JButton("1");
		b1.setBounds(58, 12, 70, 70);
		panel_2.add(b1);
		
		b2 = new JButton("2");
		b2.setBounds(186, 12, 70, 70);
		panel_2.add(b2);
		
		
		b3 = new JButton("3");
		b3.setBounds(314, 12, 70, 70);
		panel_2.add(b3);
		
		b4 = new JButton("4");
		b4.setBounds(442, 12, 70, 70);
		panel_2.add(b4);
		
		b5 = new JButton("5");
		b5.setBounds(570, 12, 70, 70);
		panel_2.add(b5);
		
		b6 = new JButton("6");
		b6.setBounds(698, 12, 70, 70);
		panel_2.add(b6);
		
		b20 = new JButton("20");
		b20.setBounds(186, 258, 70, 70);
		panel_2.add(b20);
		
		b15 = new JButton("15");
		b15.setBounds(314, 176, 70, 70);
		panel_2.add(b15);
		
		b10 = new JButton("10");
		b10.setBounds(442, 94, 70, 70);
		panel_2.add(b10);
		
		b9 = new JButton("9");
		b9.setBounds(314, 94, 70, 70);
		panel_2.add(b9);
		
		b14 = new JButton("14");
		b14.setBounds(186, 176, 70, 70);
		panel_2.add(b14);
		
		b19 = new JButton("19");
		b19.setBounds(58, 258, 70, 70);
		panel_2.add(b19);
		
		b18 = new JButton("18");
		b18.setBounds(698, 176, 70, 70);
		panel_2.add(b18);
		
		b17 = new JButton("17");
		b17.setBounds(570, 176, 70, 70);
		panel_2.add(b17);
		
		b16 = new JButton("16");
		b16.setBounds(442, 176, 70, 70);
		panel_2.add(b16);
		
		b11 = new JButton("11");
		b11.setBounds(570, 94, 70, 70);
		panel_2.add(b11);
		
		b12 = new JButton("12");
		b12.setBounds(698, 94, 70, 70);
		panel_2.add(b12);
		
		b13 = new JButton("13");
		b13.setBounds(58, 176, 70, 70);
		panel_2.add(b13);
		
		b8 = new JButton("8");
		b8.setBounds(186, 94, 70, 70);
		panel_2.add(b8);
		
		b7 = new JButton("7");
		b7.setBounds(58, 94, 70, 70);
		panel_2.add(b7);
		
		b25 = new JButton("25");
		b25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,25);
				m.setVisible(true);		
				
				
			}
		});
		b25.setBounds(58, 340, 70, 70);
		panel_2.add(b25);
		
		b21 = new JButton("21");
		b21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,21);
				m.setVisible(true);		
				
				
			}
		});
		b21.setBounds(314, 258, 70, 70);
		panel_2.add(b21);
		
		b22 = new JButton("22");
		b22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,22);
				m.setVisible(true);		
				
				
			}
		});
		b22.setBounds(442, 258, 70, 70);
		panel_2.add(b22);
		
		b23 = new JButton("23");
		b23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,23);
				m.setVisible(true);		
				
				
			}
		});
		b23.setBounds(570, 258, 70, 70);
		panel_2.add(b23);
		
		b24 = new JButton("24");
		b24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,24);
				m.setVisible(true);		
				
				
			}
		});
		b24.setBounds(698, 258, 70, 70);
		panel_2.add(b24);
		
		b26 = new JButton("26");
		b26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,26);
				m.setVisible(true);		
				
				
			}
		});
		b26.setBounds(186, 340, 70, 70);
		panel_2.add(b26);
		
		b27 = new JButton("27");
		b27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,27);
				m.setVisible(true);		
				
				
			}
		});
		b27.setBounds(314, 340, 70, 70);
		panel_2.add(b27);
		
		b28 = new JButton("28");
		b28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,28);
				m.setVisible(true);		
				
				
			}
		});
		b28.setBounds(442, 340, 70, 70);
		panel_2.add(b28);
		
		b29 = new JButton("29");
		b29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,29);
				m.setVisible(true);		
				
				
			}
		});
		b29.setBounds(570, 340, 70, 70);
		panel_2.add(b29);
		
		b30 = new JButton("30");
		b30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mesa m = new Mesa(p,30);
				m.setVisible(true);		
				
				
			}
		});
		b30.setBounds(698, 340, 70, 70);
		panel_2.add(b30);
		
		JLabel lblNewLabel = new JLabel("Mesas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(591, 11, 46, 14);
		panel_1.add(lblNewLabel);
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,7);
				m.setVisible(true);		
				
				
			}
		});
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,8);
				m.setVisible(true);			
				
				
			}
		});
		b13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,13);
				m.setVisible(true);			
				
				
			}
		});
		b12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,12);
				m.setVisible(true);			
				
				
			}
		});
		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,11);
				m.setVisible(true);			
				
				
			}
		});
		b16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,16);
				m.setVisible(true);			
				
				
			}
		});
		b17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,17);
				m.setVisible(true);			
				
				
			}
		});
		b18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,18);
				m.setVisible(true);	
				
				
			}
		});
		b19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,19);
				m.setVisible(true);			
				
				
			}
		});
		b14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,14);
				m.setVisible(true);
				
				
			}
		});
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,9);
				m.setVisible(true);			
				
				
			}
		});
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,10);
				m.setVisible(true);			
			}
		});
		b15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,15);
				m.setVisible(true);		
				
			}
		});
		b20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,20);
				m.setVisible(true);		
			
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,6);
				m.setVisible(true);		
				
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,5);
				m.setVisible(true);			
				
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,4);
				m.setVisible(true);	
				
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,3);
				m.setVisible(true);			
				
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,2);
				m.setVisible(true);	
				
			}
		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mesa m = new Mesa(p,1);
				m.setVisible(true);
			}
		});
		
		iniciarTodo();
	}

	public void iniciarTodo() {		
		centrar();
		setearApariencia();
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
		con.cerrarConexion();
		
		botones.add(b1);
		botones.add(b2);
		botones.add(b3);
		botones.add(b4);
		botones.add(b5);
		botones.add(b6);
		botones.add(b7);
		botones.add(b8);
		botones.add(b9);
		botones.add(b10);
		botones.add(b11);
		botones.add(b12);
		botones.add(b13);
		botones.add(b14);
		botones.add(b15);
		botones.add(b16);
		botones.add(b17);
		botones.add(b18);
		botones.add(b19);
		botones.add(b20);
		botones.add(b21);
		botones.add(b22);
		botones.add(b23);
		botones.add(b24);
		botones.add(b25);
		botones.add(b26);
		botones.add(b27);
		botones.add(b28);
		botones.add(b29);
		botones.add(b30);

		
		for(int i = 0 ; i < Mesas.size(); i++) {
						
			MesaClase m = Mesas.get(i);
			
			mesas.add(m);
			if(m.getEstado().equals("disponible")) {
				
				botones.get(i).setBackground(Color.WHITE);
			}else {
				botones.get(i).setBackground(Color.RED);
			}
						
		}
	}
	
	
	public void maximizar() {
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}
}
