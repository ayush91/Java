
//11/15/13
//Niyati Desai(cs433010) & Ayush Gupta(cs433018)
//Source Code for the Database System 

import javax.swing.*;

import java.awt.event.*;

class databaseproject extends JFrame
{
	private JPanel panel;
	private JLabel messageLabel;
	private JTextField welcomeTextField;
	private JRadioButton CreateButton;
	private JRadioButton DeleteButton;
	private JRadioButton UpdateButton;
	private JRadioButton ViewButton;
	private JRadioButton ExitButton;
	private ButtonGroup radioButtonGroup;
	
	private final int WINDOW_WIDTH=250;
	private final int WINDOW_HEIGHT=250;
	
	public databaseproject()
	{
		setTitle("Main view of the Database");
		
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildPanel();
		
		add(panel);
		
		setVisible(true);
		
	}
	
	private void buildPanel()
	{
		messageLabel = new JLabel("Type in the Bill number");
		welcomeTextField = new JTextField(20);
	
		CreateButton = new JRadioButton("Create a new Bill");
		
		DeleteButton = new JRadioButton("Delete an old Bill");
		
		UpdateButton = new JRadioButton("Update an existing Bill");
		
		ViewButton = new JRadioButton("View an existing Bill");
		
		ExitButton = new JRadioButton("Exit from the Database");
		
		radioButtonGroup= new ButtonGroup();
		radioButtonGroup.add(CreateButton);
		radioButtonGroup.add(DeleteButton);
		radioButtonGroup.add(UpdateButton);
		radioButtonGroup.add(ViewButton);
		radioButtonGroup.add(ExitButton);
		
		CreateButton.addActionListener(new RadioButtonListener());
		DeleteButton.addActionListener(new RadioButtonListener());
		UpdateButton.addActionListener(new RadioButtonListener());
		ViewButton.addActionListener(new RadioButtonListener());
		ExitButton.addActionListener(new RadioButtonListener());
		
		panel = new JPanel();
		panel.add(messageLabel);
		panel.add(welcomeTextField);
		panel.add(CreateButton);
		panel.add(DeleteButton);
		panel.add(UpdateButton);
		panel.add(ViewButton);
		panel.add(ExitButton);
	}
	
	private class RadioButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String input;
			
			double result = 0.0;
			input = welcomeTextField.getText();
			
			if (e.getSource() == CreateButton)
			{
				result = Double.parseDouble(input);
			}
			
			if (e.getSource() == DeleteButton)
			{
				result = Double.parseDouble(input);
			}
			
			if (e.getSource() == UpdateButton)
			{
				result = Double.parseDouble(input);
			}
			
			if (e.getSource() == ViewButton)
			{
				result = Double.parseDouble(input);
			}
			
			if (e.getSource() == ExitButton)
			{
				result = Double.parseDouble(input);
			}
		
			JOptionPane.showMessageDialog(null, "The bill is" + result);
		}
		
	}
	public static void main(String[] args)
	{
		new databaseproject();
	}

}