import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

class TAView extends JPanel implements Observer {
	// the view's main user interface
	private JTextField guess = new JTextField(10);
	private Model model;
	
	TAView(Model model_) {
		//INPUT	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel("Please type in the word to be guessed "));
		this.add(new JLabel("the word need to exist in dictionary "));
		this.add(new JLabel("press enter to set and a new game will start."));
		this.add(guess);
		guess.setMaximumSize(new Dimension(1200,20));
		// set the model 
		model = model_;

		guess.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (model.inDictionary(guess.getText().toUpperCase())){
					model.start();
					model.word = guess.getText().toUpperCase();
					System.out.println(model.word);
				}
			}
		});
	} 

	// Observer interface 
	@Override
	public void update(Observable arg0, Object arg1) {
	}
} 