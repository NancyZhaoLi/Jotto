import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;	
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

class letterGuessedView extends JPanel implements Observer {

	// the view's main user interface
	private Vector<JLabel> letters = new Vector<JLabel>(); 
	private String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 
	// the model that this view is showing
	private Model model;
	
	letterGuessedView(Model model_) {
		for (String s : alphabet){
			letters.add(new JLabel(s));
		}
		for (int i = 0; i < letters.size(); i++){
			this.add(letters.elementAt(i));
			letters.elementAt(i).setForeground(Color.white);
		}
	
		// set the model 
		model = model_;	
	} 

	// Observer interface 
	@Override
	public void update(Observable arg0, Object arg1) {
		//restartButton.setText(difficulty.getSelectedItem().toString());
		for (int i = 0; i < 26 ;i++){
			if (model.letterGuessed[i]){
				letters.elementAt(i).setForeground(Color.black);
			}
			else letters.elementAt(i).setForeground(Color.white);
		}
	}
} 
