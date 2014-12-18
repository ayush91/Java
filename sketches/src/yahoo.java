import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/////////////////////////////////////////
/////////////// ExP1c class ///////////////

public class yahoo extends JFrame implements MouseListener {

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
    JFrame    rightFrame     = null; // This will "contain" three elements:
    JLabel    rightUpperImg  = null; // a clickable "image" in the top-right
    ImageIcon rightUpperImg1 = null; // from p1-ullmer-screen[12]: mediterranean
    ImageIcon rightUpperImg2 = null; // from p1-ullmer-screen3: raw food
    ImageIcon rightUpperImgCurrent = null; // as before, express which is current

/////////////// main ///////////////

  public static void main(String[] args) {
    new ExP1a();
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
    root.setLayout(new FlowLayout(FlowLayout.LEADING));

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

        //rightFrame = new JFrame();
        //root.add(rightFrame);
        //rightFrame.add(rightUpperImg);
        root.add(rightUpperImg);

    pack();
    setVisible(true);
  }

/////////////// constructor ///////////////

  public yahoo() {
    super("ExP1a");
        buildUI();
  }
}
