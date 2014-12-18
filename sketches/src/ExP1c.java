	// Example code toward P1
	// Illustrates a simple screenshot, toggled between two states using a button
	// Brygg Ullmer, LSU
// February 15, 2011
	
//There are several graphics packages in Java, including Swing, SWT, et al.
	// Here, we'll demonstrate Swing.
	
    import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	
	///////////////////////////////////////////
	/////////////// ExP1dKhurana class ///////////////
	
 public class ExP1c extends JFrame implements MouseListener {
	
	/////////////// public member variables ///////////////
	
	    int w = 1024, h = 768;  // This example centers on a 1024x768 screen
	    int lw = 774, lh = 768; // ... the left portion of which is full height and 774 pixels wide.
	    int rw = 242, rh = 557; // The upper portion of the right side is 557 pixels tall.
	   
	    Container root = null; // This will be used as a "root frame" for the GUI
	
	    // The GUI is divided into several portions:
	    JLabel    leftImg  = null; // The left 2/3 of the screen. Prototyped as several still images:
	    ImageIcon leftImg1 = null; // from p1-ullmer-screen1: mediterranean, high "adherance," "always"
	    ImageIcon leftImg2 = null; // from p1-ullmer-screen2: mediterranean, low "adherance," "always"
	    ImageIcon leftImg3 = null; // from p1-ullmer-screen3: raw food, low "adherance," "always"
	    ImageIcon leftImgCurrent = null; // which one is currently displayed
	
	    // On the right side, we begin with a surrounding "frame"
	    JPanel    rightPanel     = null; // This will "contain" three elements:
	    JLabel    rightUpperImg  = null; // a clickable "image" in the top-right
   ImageIcon rightUpperImg1 = null; // from p1-ullmer-screen[12]: mediterranean
	    ImageIcon rightUpperImg2 = null; // from p1-ullmer-screen3: raw food
	    ImageIcon rightUpperImgCurrent = null; // as before, express which is current
	
	    // Sliders
	    JSlider rightSlider1    = null;     // And two Sliders/sliders: one for adherance
	    String  rightSlider1Txt = "adherance";
	    JSlider rightSlider2    = null;     // and one for "when"
	    String  rightSlider2Txt = "when";
	
	    JLabel  rightSlider1Label = null;
	    JLabel  rightSlider2Label = null;
	
	    JPanel rightSlider1Sub = null;      // Now, frames for containing sublabels
	    JPanel rightSlider2Sub = null;      //
	    String rightSlider1SubTxt[] = {"loose", "strict"};
	    String rightSlider2SubTxt[] = {"now", "always"};
	
	    int    sliderwidth     = 30;         // pixel width of slider/Slider
	    Font  Sliderfont       = null;      // Font for label of Slider/slider widgets
	    //    Sliderbg        = '#685f42' #Slider colors (acquired by inspecting original screen layout)
	    //    Sliderfg        = '#c6b86d' #and foreground
   //    Sliderfgsub     = '#292411' #and foreground of sub-labels
	
	/////////////// main ///////////////
	
	  public static void main(String[] args) {
	    new ExP1c();
	  }
	
	/////////////// toggleLeftImg ///////////////
	 
	  public void toggleLeftImg() {
	
	    if (leftImgCurrent == leftImg1) {
	      leftImgCurrent = leftImg2;
	    } else {
	      leftImgCurrent = leftImg1;
	    }
	    leftImg.setIcon(leftImgCurrent);
	  }
	
	/////////////// mouse events ///////////////
	
	  public void mousePressed(MouseEvent e) { System.out.println("mouse pressed"); }
	  public void mouseReleased(MouseEvent e) { System.out.println("mouse released"); }
	  public void mouseEntered(MouseEvent e) { System.out.println("mouse entered"); }
	  public void mouseExited(MouseEvent e) { System.out.println("mouse exited"); }
	
	  public void mouseClicked(MouseEvent e) { 
	    System.out.println("mouse clicked / custom"); 
	    toggleLeftImg();
	  }

	/////////////// buildUI ///////////////
	
	  public void buildUI () {
	    root = getContentPane();
	    root.setLayout(new FlowLayout(0,0,0));
	    // http://java.sun.com/developer/onlineTraining/GUI/AWTLayoutMgr/shortcourse.html
	
	    Font font = new Font("Serif", Font.PLAIN, 30);
	    root.setFont(font);
	
	    /// Set up left side of screen ///
	    leftImg1 = new ImageIcon("sketch2-stage1.jpg");
	    leftImg2 = new ImageIcon("sketch2-stage2.jpg");
	    leftImgCurrent = leftImg1;
	
	    leftImg = new JLabel(leftImgCurrent);
	    root.add(leftImg, BorderLayout.WEST);
	
	    /// Set up right side of screen ///
	
	    rightUpperImg1 = new ImageIcon("sketch2-stage1.jpg");
	    rightUpperImg2 = new ImageIcon("sketch2-stage2.jpg");
	    rightUpperImgCurrent = rightUpperImg1;
	
	    rightUpperImg = new JLabel(rightUpperImgCurrent);
	    rightUpperImg.addMouseListener(this);
	
	    rightPanel = new JPanel();
	    rightPanel.setPreferredSize(new Dimension(rw, h));
	
	    //rightPanel.setAlignmentY(TOP_ALIGNMENT);
	    rightPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
	    root.add(rightPanel);
	    rightPanel.add(rightUpperImg);
	    //root.add(rightUpperImg);
	
	    //// Sliders ////
	    rightSlider1 = new JSlider(0, 100, 50);
	    rightSlider2 = new JSlider(0, 100, 50);
	    rightSlider1Label = new JLabel(rightSlider1Txt);
	    rightSlider2Label = new JLabel(rightSlider2Txt);
	
	    rightSlider1.setPreferredSize(new Dimension(rw, sliderwidth));
	
	    rightPanel.add(rightSlider1Label);
    rightPanel.add(rightSlider1);
	    rightPanel.add(rightSlider2Label);
	    rightPanel.add(rightSlider2);
	
	    pack();
	    setVisible(true);
	  }
	
	/////////////// constructor ///////////////
	
	  public ExP1c() {
	    super("ExP1dKhurana");
	    buildUI();
	  }
	}