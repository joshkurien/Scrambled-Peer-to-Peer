package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecieverWindow implements Runnable{

	private Thread t;
	private JFrame frame;
	private JTextArea InText;
	private JRadioButton rdbtnListen;
	private static RecieverWindow temp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					temp = new RecieverWindow();
					temp.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	
	/**
	 * Create the application.
	 */
	public RecieverWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		InText = new JTextArea();
		InText.setLineWrap(true);
		InText.setWrapStyleWord(true);
		InText.setText("Well... nothing to show here really \njust waiting for something to come from the other end");
		InText.setBounds(49, 81, 348, 102);
		frame.getContentPane().add(InText);
		InText.setColumns(10);
		InText.setEditable(false);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(49, 47, 200, 50);
		frame.getContentPane().add(lblMessage);
		
		rdbtnListen = new JRadioButton("Listen for messages");
		rdbtnListen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				if(rdbtnListen.isSelected()){
	
					t = new Thread(temp);
					t.start();
				}
			}		            
		});
		rdbtnListen.setBounds(141, 23, 200, 50);
		frame.getContentPane().add(rdbtnListen);
	}


	@Override
	public void run() {

		String in_message;
		
		try {
			in_message = Main.Reciever.work();
			InText.setText(in_message);
			System.out.println(in_message);
			/*
			char []t=in_message.toCharArray();
			int len=in_message.length();
			for(int i=0;i<len;i++){
				String a="";
				a=a+t[i];
				InText.append(a);
			}*/
			
			rdbtnListen.setSelected(false);
			frame.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}