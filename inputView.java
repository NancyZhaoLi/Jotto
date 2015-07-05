import javax.swing.*;
import javax.swing.event.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;	
import java.util.Observable;
import java.util.Observer;

class inputView extends JPanel implements Observer {

	// the view's main user interface
	private JTextField guess = new JTextField(10);
	private JLabel feedback = new JLabel("");
	// the model that this view is showing
	private Model model;
	
	inputView(Model model_) {
		//INPUT	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(guess);
		this.add(feedback);
		guess.setMaximumSize(new Dimension(2000,20));
		feedback.setForeground(Color.blue);
		// set the model 
		model = model_;

		guess.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent de){
			}
			public void insertUpdate(DocumentEvent de){
				String txt = guess.getText().toUpperCase();
				if (model.tries1 == 10){
					feedback.setText("Too slow! Game over, will restart at next guess");
					return;
				}
				if (model.tries1 == 11){
					model.start();
				}
				if (txt.length() > 5)feedback.setText("This word is too long        ");
				else if (txt.length() == 5){
					if (model.inDictionary(txt)){
						if (model.alreadyGuessed(txt)){
							feedback.setText(txt + " have been guessed");
						}
						else {
							if (model.guess(1,txt)){
								feedback.setText("Correct! Press restart to play again!");
								
							}
							else feedback.setText("Just guessed : " + txt);
						}
					}else feedback.setText(txt + " is not in dictionary");
				}
				else feedback.setText("                     ");
			}
			public void removeUpdate(DocumentEvent de){
				String txt = guess.getText().toUpperCase();
				if (model.tries1 == 10){
					feedback.setText("Too slow! Game over, will restart at next guess");
					return;
				}
				if (model.tries1 == 11){
					model.start();
				}
				if (txt.length() > 5)feedback.setText("too long");
				else if (txt.length() == 5){
					if (model.inDictionary(txt)){
						if (model.alreadyGuessed(txt)){
							feedback.setText(txt + " have been guessed");
						}
						else {
							if (model.guess(1,txt)){
								feedback.setText("Correct!");
							}
							else feedback.setText("Just guessed : " + txt);
						}
					}else feedback.setText(txt + " is not in dictionary");
				}
				else feedback.setText("           ");
			}
		});
	} 

	// Observer interface 
	@Override
	public void update(Observable arg0, Object arg1) {
		feedback.setText("");
	}
} 
