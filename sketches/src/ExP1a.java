   import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
    import java.awt.event.*;
	import javax.swing.*;
	
	
	
	public class ExP1a extends JFrame implements MouseListener {
	
	/////////////// public member variables ///////////////
	
	        int w = 1024, h = 768;  
	        int lw = 774, lh = 768; 
	        int rw = 242, rh = 557; // The upper portion of the right side is 557 pixels tall.
	       
	        Container root = null; 
	        JLabel    leftImg  = null; 
	        ImageIcon leftImg1 = null; 
	        ImageIcon leftImg2 = null; 
	        ImageIcon leftImg3 = null; 
	        ImageIcon leftImgCurrent = null; // which one is currently displayed
	
	    // On the right side, we begin with a surrounding "frame"
	    JFrame    rightFrame     = null; 
	    JLabel    rightUpperImg  = null; 
	    ImageIcon rightUpperImg1 = null; 
	    ImageIcon rightUpperImg2 = null; 
	    ImageIcon rightUpperImgCurrent = null; 
	    ImageIcon check = null;
	    ImageIcon check1 = null;
	    JLabel click = null;
	    JPanel jp = new JPanel();
	    JButton jb = new JButton();
	
	/////////////// main ///////////////
	
	  public static void main(String[] args) {
	    new Exp1a();
	  }
	
	/////////////// toggleLeftImg ///////////////
	 
	  public void toggleLeftImg() {
		  
	
	    if (leftImgCurrent == leftImg1) {
	          leftImgCurrent = leftImg2;
	        } else if ( leftImgCurrent == leftImg2){
	          leftImgCurrent = leftImg3;
	    }
	        else 
	        {
	        	leftImgCurrent = leftImg1;
	        }
	        leftImg.setIcon(leftImgCurrent);
	  }
	
	/////////////// mouse events ///////////////
	
	  public void mousePressed(MouseEvent e) { System.out.println(""); }
	  public void mouseReleased(MouseEvent e) { System.out.println(""); }
	  public void mouseEntered(MouseEvent e) { System.out.println(""); }
	  public void mouseExited(MouseEvent e) { System.out.println(""); }
	
	  public void mouseClicked(MouseEvent e) { 
	    
	        mouseclicked(e);
	        jb.setIcon(new ImageIcon("button.jpg"));
	        jp.add(jb);
	        root.add(jb);
	        
	        toggleLeftImg();
	        

	        		
	  }
	  public void mouseclicked(MouseEvent e) {

		  Point  p;
		  p = e.getPoint();
		  System.out.print(p);
		 
		  
		 

		      }
	  public void change(MouseEvent e) {
		  		  
		  
		 		  
		 

		      }
	
	/////////////// buildUI ///////////////
	
	  public void buildUI () {
	    root = getContentPane();
	    root.setLayout(new FlowLayout(FlowLayout.LEADING));
	
	    Font font = new Font("Serif", Font.PLAIN, 30);
	    root.setFont(font);
	
	        /// Set up left side of screen ///
	    leftImg1 = new ImageIcon("sketch2-stage1.jpg");
	    leftImg2 = new ImageIcon("sketch2-stage2.jpg");
	    leftImg3 = new ImageIcon("sketch2-stage3.jpg");
	        leftImgCurrent = leftImg1;
	
	    leftImg = new JLabel(leftImgCurrent);
	    root.add(leftImg, BorderLayout.WEST);
	
	        /// Set up right side of screen ///
	

	
	        rightUpperImg = new JLabel(rightUpperImgCurrent);
        leftImg.addMouseListener(this);

	        root.add(rightUpperImg);
	
	    pack();
	    setVisible(true);
	  }

	/////////////// constructor ///////////////
	
	  public Exp1a() {
	    super("awesome");
	        buildUI();
	  }
	}	