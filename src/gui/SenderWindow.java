package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class SenderWindow {

	private JFrame frame;
	private JTextField recieverIP;
	private JTextField message;
	private JLabel lblPortNumber;
	private JComboBox<String> ports;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SenderWindow window = new SenderWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SenderWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 301, 387);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRecieverIp = new JLabel("Reciever IP");
		lblRecieverIp.setFont(new Font("Liberation Sans", Font.BOLD, 12));
		lblRecieverIp.setBounds(49, 40, 95, 31);
		frame.getContentPane().add(lblRecieverIp);
		
		recieverIP = new JTextField();
		recieverIP.setFont(new Font("DialogInput", Font.PLAIN, 12));
		recieverIP.setText("127.0.0.1");
		recieverIP.setBounds(150, 45, 114, 19);
		frame.getContentPane().add(recieverIP);
		recieverIP.setColumns(10);
		
		JLabel lblMessageToBe = new JLabel("Message to be sent");
		lblMessageToBe.setFont(new Font("Liberation Sans", Font.BOLD, 12));
		lblMessageToBe.setBounds(76, 83, 188, 31);
		frame.getContentPane().add(lblMessageToBe);
		
		message = new JTextField();
		message.setBounds(34, 111, 230, 147);
		frame.getContentPane().add(message);
		message.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Main.Sender.work(ports.getSelectedIndex()+1,recieverIP.getText(),message.getText());
					JOptionPane.showMessageDialog(null, "well, the message got sent... at least its gone somewhere!");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Next time try using valid numbers!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Whoopsie, something went wrong there!");
				}
			}
		});
		btnSend.setBounds(99, 292, 117, 25);
		frame.getContentPane().add(btnSend);
		
		lblPortNumber = new JLabel("Ports");
		lblPortNumber.setFont(new Font("Liberation Sans", Font.BOLD, 12));
		lblPortNumber.setBounds(49, 12, 82, 15);
		frame.getContentPane().add(lblPortNumber);
		
		ports = new JComboBox<String>();
		ports.setMaximumRowCount(12);		
		ports.setBounds(150, 12, 114, 19);
		ports.addItem("1");
		ports.addItem("2");
		ports.addItem("3");
		ports.addItem("4");
		ports.addItem("5");
		ports.addItem("6");
		ports.addItem("7");
		ports.addItem("8");
		ports.addItem("9");
		ports.addItem("10");
		ports.addItem("11");
		ports.addItem("12");
		ports.setSelectedIndex(4);
		frame.getContentPane().add(ports);
		
		
	}
}
