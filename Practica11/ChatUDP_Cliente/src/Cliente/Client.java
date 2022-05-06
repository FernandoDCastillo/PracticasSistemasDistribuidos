/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.BorderFactory;
/**
 *
 * @author Sarah
 */
public class Client implements Runnable {
	
	public final static int SERVERPORT = 9000;  
	static DatagramSocket udpClientSocket = null;  
	static InetAddress serverIPAddress;   
    String name;  
    
    public void setName(String nm) {   
	    this.name = nm;
	} 
	
    public String getName() {  
	    return name;	
	} 
    
    String text = "Bienvenido al chat UDP.... \n Elija Iniciar sesión en el menú de archivo de arriba e ingrese \n tu nombre en la pantalla.";
    JFrame frame = new JFrame("              Chat UDP");  
    JPanel panel1 = new JPanel(new BorderLayout()); 
    JPanel panel2 = new JPanel();  
    JMenuBar menuBar = new JMenuBar();
    JTextField textField = new JTextField(30); 
    JTextArea messageArea = new JTextArea(text,20,25);  
  
    public JMenu createFileMenu() {  
		JMenu menu = new JMenu("Menu");
		menu.add(createLogOnItem());  
		menu.add(createFileExitItem()); 
		return menu;
		} 
	
	public JMenuItem createLogOnItem() {
		JMenuItem item = new JMenuItem("Iniciar sesión");
		class MenuItemListener implements ActionListener {
			public void actionPerformed (ActionEvent event) {
				
			    String nme = JOptionPane.showInputDialog(null,"Por favor, escriba su nombre: ","Iniciar sesión",JOptionPane.INFORMATION_MESSAGE);
				setName(nme);
				
     try {       
     serverIPAddress = InetAddress.getByName("25.6.164.212");
         }catch (IOException er) {
					System.out.println(er);
		              }   
    
     
      byte[] sendData = new byte[1024]; 

      
      String clientRequest = "HAL"+name; 
      
      
      sendData = clientRequest.getBytes();
      
      
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, SERVERPORT);
      
          
      try {
		  messageArea.append("Paquete enviado al servidor \n"); 	
              udpClientSocket.send(sendPacket);   
           } catch (IOException er) {
					System.out.println(er);
		              } 
      
			
			} 
		} 
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener); 
		return item;
	} 
	
	public JMenuItem createFileExitItem() {     
	    JMenuItem item = new JMenuItem("Salir");
	    class MenuItemListener implements ActionListener {
			public void actionPerformed (ActionEvent event) {
			    System.exit(0);       
			} 
		} 
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
	    return item;
	}	
  
	Client() {
       
		frame.setJMenuBar(menuBar);
        menuBar.add(createFileMenu());
      
		textField.setEditable(true);
		messageArea.setEditable(false);
		frame.setSize(500,700);
        
        panel1.add(messageArea);
        panel1.add(new JScrollPane(messageArea),"Center");
        panel2.add(new JLabel("Message:"));
        panel2.add(textField);   
       
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue, 15));
        panel2.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 10));  
        
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.getContentPane().add(panel2,BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		 textField.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			byte[] data = new byte[1024];
			String info = getName() + ": " + textField.getText(); 
			data = info.getBytes();
			DatagramPacket output = new DatagramPacket(data, data.length,serverIPAddress,SERVERPORT);
			try {
			udpClientSocket.send(output);
				} catch(IOException er) {
				 System.out.println(er);
			 } 
			data = new byte[1024]; 
		    textField.setText("");  
		}
	    });   
	
	} 
	
	public void run() {   
	
	    byte[] info = new byte[1024];
	    String s = "";
	    while(true)
	    {
			DatagramPacket dp = new DatagramPacket(info, info.length);
			try {
				 udpClientSocket.receive(dp);
		         s = new String (dp.getData());
			     messageArea.append(s + "\n");   
				} catch(IOException er) {
				 System.out.println(er);
			 } 
			 
		} 
		 
	}
     
    
	
	public static void main(String [] args) {	
	
      
        try {
            udpClientSocket = new DatagramSocket();
        }catch (IOException er) {
					System.out.println(er);
		             } 
        
        Thread t = new Thread(new Client()); 
		t.start();  

    } 
	
} 
