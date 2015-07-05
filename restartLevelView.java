import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;	
import java.util.Observable;
import java.util.Observer;

class restartLevelView extends JPanel implements Observer {

	// the view's main user interface
	private JButton restartButton;
	private DefaultComboBoxModel difficulty = new DefaultComboBoxModel();
	// the model that this view is showing
	private Model model;
	
	restartLevelView(Model model_) {
		//DIFFICULTY
		this.add(new JLabel("Select level:"));
		for (String s : model.LEVELS){
			difficulty.addElement(s);
		}
		this.add(new JComboBox(difficulty));

		// RESTART
		restartButton = new JButton("RESTART");
		restartButton.setMaximumSize(new Dimension(100, 25));
		restartButton.setPreferredSize(new Dimension(100, 25));
		this.add(restartButton, new GridBagConstraints());
		
		
		// set the model 
		model = model_;
		
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.random = false;
				switch(difficulty.getSelectedItem().toString()){
				case "Any Difficulty":
					model.random = true;
					break;
				case "Easy":
					model.level = 0;
					break;
				case "Medium":
					model.level = 1;
					break;
				case "Hard":
					model.level = 2;
					break;
				}
				model.start();
			}
		});	
	} 

	// Observer interface 
	@Override
	public void update(Observable arg0, Object arg1) {
	}
} 
