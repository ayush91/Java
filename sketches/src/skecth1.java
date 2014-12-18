import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class skecth1 extends JFrame{
	
	private ImageIcon image1;
	private JLabel label1;
	private ImageIcon image2;
	private JLabel label2;
	private ImageIcon image3;
	private JLabel label3;
	ImageIcon currentScreenImgIcon = null; 
	JButton   toggleButton         = null;
	JLabel    screenImgLabel  = null; 
	
	

	
	
		skecth1()
		{
		super("http://www.iamawesome.com");
		

		

	    

		image1 = new ImageIcon("sketch2-stage1.jpg");
		label1 = new JLabel(image1);
		add(label1);
		Container root = this.getContentPane();
		
		currentScreenImgIcon = image1;
		image2 = new ImageIcon("sketch2-stage2.jpg");
		label2 = new JLabel(image2);
		//root.add(label2);
		image3 = new ImageIcon("sketch2-stage3.jpg");
		label3 = new JLabel(image3);
		//root.add(label3);
		screenImgLabel = new JLabel(currentScreenImgIcon);
		Action action = new AbstractAction("News")
		{
			public void actionPerformed(ActionEvent ae) 
			{
				System.out.println("button pressed!");
				 buttonCB();
			}
		};
		toggleButton   = new JButton(action);
		root.add(screenImgLabel, BorderLayout.WEST);
		 root.add(toggleButton, BorderLayout.WEST);
		 pack();
		 
		
					


		
		//con.add(pane);
		//pressme.setMnemonic('P');
		//pane.add(pressme);
		//pressme.requestFocus();
		
		
		}
		
		//public void actionPerformed
		
		
	public static void main(String args[]){ 
		skecth1 gui = new skecth1();
	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gui.setVisible(true);
	gui.setBounds(100,100,400,540);
	}
	
	public void buttonCB() {
			       
			
			    if (currentScreenImgIcon == image1) {
			      currentScreenImgIcon = image2;
			      screenImgLabel.setIcon(currentScreenImgIcon);
			    } else {
			      currentScreenImgIcon = image1;
			      screenImgLabel.setIcon(currentScreenImgIcon);
			    }
			  }

}
