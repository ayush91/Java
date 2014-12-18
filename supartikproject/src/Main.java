
/**
 *
 * @author ayush and niyati
 */

import java.io.*;
import java.util.*;
public class Main extends javax.swing.JFrame {

	public Scanner x;
	private String a;
	private String b;
	private String c;
	private String d;

     public void open()
     {
    	 try {
     		
     		x = new Scanner(new File("database.txt"));
       	 while(x.hasNext()){
       	  a = x.next();
       	  b = x.next();
       	  c = x.next();
       	  d = x.next();
       	 
       	 }
     		
     	}
     	catch(Exception e)
     	{ System.out.println("Can't read the file");
     	}
     }
     
     public void readfile()
     {
    	 while(x.hasNext()){
    	  a = x.next();
    	  b = x.next();
    	  c = x.next();
    	  d = x.next();
    	 
    	 }
     }
    public void close(){
    	x.close();
    }
    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
    	open();
    	readfile();
    	close();
    }            
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        database = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        database.setModel(new javax.swing.table.DefaultTableModel(
        		
            new Object [][] {
                {"Electric","Entergy", "01/03/13", "$125.04"},
                {"Water","BR Water Comp", "01/06/13", "$86.22"},
                {"Car","Gas", "01/08/13", "$45.04"},
                {"Car","Gas", "01/24/13", "$48.96"},
                {"Home Bill","The Government", "01/29/13", "$284"},
                {"Speeding","Br Police", "02/08/13", "$138.70"},
                {"Laptop Broken","Best Buy", "02/24/13", "$322.64"},
                {"Car","Gas", "02/25/13", "$42.12"},
                {"Car","Gas", "03/02/13", "$39.92"},
                {"Property Tax","Government", "03/09/13", "$99.44"},
                {"Grocery","Target", "03/11/13", "$226.24"},
                {"Electric","Entergy", "01/03/13", "$125.04"},
                {"Car","Gas", "03/02/13", "$39.92"},
                {"Electric","Entergy", "01/03/13", "$125.04"},
                {"Grocery","Target", "05/16/13", "$189.55"},
                {"Water","BR Water Comp", "05/06/13", "$82.22"},
                {"Car","Gas", "05/08/13", "$37.67"},
                {"Grocery","Target", "05/11/13", "$226.24"},
                {"Car","Gas", "05/18/13", "$39.92"},
                {"Electric","Entergy", "05/19/13", "$125.04"},
                {"Grocery","Target", "05/25/13", "$129.55"},
                {"Electric","Entergy", "08/08/13", "$122.04"},
                {"Water","BR Water Comp", "08/16/13", "$186.22"},
                {"Car","Gas", "08/18/13", "$46.04"},
                {"Car","Gas", "08/28/13", "$49.96"},
                {"Home Bill","The Government", "09/02/13", "$283.99"},
                {"Speeding","Br Police", "09/08/13", "$132.70"},
                {"Tablet","Best Buy", "09/24/13", "$322.64"},
                {"Car","Gas", "10/25/13", "$42.12"},
                {"Car","Gas", "11/02/13", "$39.92"},
                {null,null,null,null},
                {null,null,null,null},
                {null,null,null,null},
                {null,null,null,null},
             },
            new String [] {
                "Type of Bill", "Company Name", "Date", "Amount"
            }
        ));
        
        
        jScrollPane1.setViewportView(database);

        jButton1.setText("Create");

        jButton2.setText("Delete");

        jButton3.setText("Update");

        jButton4.setText("Print");
        jButton5.setText("Exit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(34, 34, 34)
                .addComponent(jButton2)
                .addGap(74, 74, 74)
                .addComponent(jButton3)
                .addGap(70, 70, 70)
                .addComponent(jButton4)
                .addGap(72, 72, 72)
                .addComponent(jButton5)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
        );
       

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JTable database;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
