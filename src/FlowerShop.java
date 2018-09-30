import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class FlowerShop extends JFrame implements ActionListener{
	
	JTextField t1,t2,t3,t4,t5,t6;
	String name,address,id;
	JTextArea a1,a2;
	double total;
	DataOutputStream output;
	RandomAccessFile input;
	public FlowerShop(){

    JLabel background = new JLabel(new ImageIcon("10.png"));
    add(background);
	JPanel p1 = new JPanel();
	p1.setOpaque(false); 
	p1.setBounds(5,30,470,180);
	background.add(p1);
	JLabel info = new JLabel( "PERSONAL INFORMATION ");
	Font sty1 = new Font("Chalkduster",Font.BOLD,12);
	Font sty2 = new Font("Casual",Font.BOLD,15);
	Font sty3 = new Font("Casual",Font.BOLD,9);
	info.setFont(sty1);
	
    info.setOpaque(false);
	info.setBounds(60,30,300,20);  
	background.add(info);
	JLabel n  = new JLabel("Name:");
	n.setFont(sty2);
	n.setBounds(20,100,500,20);
	background.add(n);
	t1 = new JTextField();
	t1.setBounds(150,100,200,20);
	background.add(t1);
	
	JLabel i  = new JLabel("National ID:");
	i.setFont(sty2);
	i.setBounds(20,130,500,20);
	background.add(i);
	t2 = new JTextField();
	t2.setBounds(150,130,200,20);
	background.add(t2);
	
	JLabel a  = new JLabel("Address:");
	a.setFont(sty2);
	a.setBounds(20,160,500,20);
	background.add(a);
	t3 = new JTextField();
	t3.setBounds(150,160,200,20);
	background.add(t3);
	
	
	JPanel p2 = new JPanel();
	p2.setOpaque(false); 
	p2.setBounds(5,230,470,180);
	background.add(p2);
	JLabel or = new JLabel( " ORDER");
	or.setFont(sty1);
    or.setOpaque(false);
	or.setBounds(60,220,300,20);  
	background.add(or);
	JLabel f1  = new JLabel("ROSE 2.00 SR ");
	f1.setFont(sty3);
	f1.setBounds(45,380,500,20);
	background.add(f1);
	t4 = new JTextField();
	t4.setBounds(35,363,80,20);
	background.add(t4);
	
	JLabel f2  = new JLabel("VOILET 3.00 SR ");
	f2.setBounds(210,380,500,20);
	background.add(f2);
	t5 = new JTextField();
	t5.setBounds(205,363,80,20);
	background.add(t5);
	f2.setFont(sty3);
	
	JLabel f3  = new JLabel("ORCHID - 5.00 SR ");
	f3.setBounds(375,380,500,20);
	background.add(f3);
	t6 = new JTextField();
	t6.setBounds(380,363,80,20);
	background.add(t6);
	f3.setFont(sty3);
	
	JLabel m  = new JLabel("MESSAGES");
	m.setBounds(70,415,500,20);
	m.setFont(sty1);
	background.add(m);
	a1 = new JTextArea("You don't have any messages yet");
	a1.setForeground(Color.black);
	a1.setLineWrap(true);
    a1.setWrapStyleWord(true);
    a1.setOpaque(false);
    a1.setEditable(false);
	a1.setBounds(130,460,800,20);
	background.add(a1);
	
	
	
	JButton order= new JButton("Place Order");
    order.addActionListener(this);
	order.setBounds(70,500,130,25);
	background.add(order);
    
    JButton Can= new JButton("Cancel");
    Can.addActionListener(this);
	Can.setBounds(300,500,130,25);
	background.add(Can);
	
	
	JPanel p3 = new JPanel();
	p3.setOpaque(false);
	p3.setBounds(5,560,470,180);
	background.add(p3);
	JLabel s  = new JLabel("Order Summery:");
	s.setFont(sty1);
	s.setBounds(70,565,500,20);
	background.add(s);
	a2 = new JTextArea("You have not placed any order yet");
	a2.setForeground(Color.black);
	a2.setLineWrap(true);
    a2.setWrapStyleWord(true);
    a2.setOpaque(false);
    a2.setEditable(false);
	a2.setBounds(50,620,1000,1000);
	background.add(a2);

	}
	
	
	  public void actionPerformed(ActionEvent ae){ 
    		try{
    		output = new DataOutputStream(new FileOutputStream("orders.txt",true));
    		input = new RandomAccessFile("orders.txt","r");
    			String s= ae.getActionCommand();
    		
    	if(s.equals("Place Order"))
    		{
    			
    			if(t1.getText()==null ||t2.getText()==null ||t3.getText()==null)
    				throw new InputMismatchException();
    			long a = input.length();

    			output.writeUTF(t1.getText());
    			output.writeUTF(t2.getText());
    			output.writeUTF(t3.getText());
    			
    			int p1 = Integer.parseInt((t4.getText()));
    			int p2 = Integer.parseInt((t5.getText()));
    			int p3 = Integer.parseInt((t6.getText()));
    			
    			
    			total =(2*p1)+(3*p2)+(5*p3);
    			String stotal = String.valueOf(total);
    			output.writeUTF(stotal);
             input.seek(a);
    			a1.setText("Thank you for odering from A&L flower shop");
    			a2.setText("coustomer name :"+input.readUTF()+ "\nID:  "+input.readUTF()+"\nAddress:  "+input.readUTF()+"\norder total:"+input.readUTF());
    			    		}
    
    		else
    	    System.exit(0);
               
    		}
    		catch(InputMismatchException ex){
    			a1.setText("you have missed one of the info ");
    		}
    		catch(IOException ex){
    			a1.setText("Sorry somthing went wrong!");
    		}
    			catch(MaxException ex){
    		a1.setText("Maximum number of flowers to be order is 20 for each type");
    			
    		}
    	
    	}
	 
	  public static void main(String [] args){
     
        FlowerShop f = new FlowerShop();
        
       	f.setVisible(true);
        f.setSize(500,800);
        f.setLocation(200,0);
        f.setTitle("A&L Flower Shop");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }
}
