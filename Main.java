// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

/**
 *  Two views with integrated controllers.  Uses java.util.Observ{er, able} instead
 *  of custom IView.
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;	
import javax.swing.BorderFactory;
import java.awt.BorderLayout;

public class Main{

	public static void main(String[] args){	
		JFrame frame = new JFrame("Jotto");
		
		// create Model and initialize it
		Model model = new Model();
		
		// create View, tell it about model (and controller)
		inputView input = new inputView(model);
		// tell Model about View. 
		model.addObserver(input);
		
		// create second view ...
		TAView view2 = new TAView(model);
		model.addObserver(view2);
		
		tableView table = new tableView(model);
		model.addObserver(table);
		
		letterGuessedView letter = new letterGuessedView(model);
		model.addObserver(letter);
		
		restartLevelView rl = new restartLevelView(model);
		model.addObserver(rl);
		
		// let all the views know that they're connected to the model
		model.notifyObservers();
		
		// create the window
		JTabbedPane tabs = new JTabbedPane();
		JPanel game = new JPanel(new BorderLayout());
		Box b = Box.createVerticalBox();
		b.add(rl);
		b.add(letter);
		letter.setBorder(BorderFactory.createTitledBorder("Letters guessed"));
		input.setBorder(BorderFactory.createTitledBorder("Guess:"));
		table.setBorder(BorderFactory.createTitledBorder("Guess Results"));
		game.add(b, BorderLayout.NORTH);
		game.add(input, BorderLayout.CENTER);
		game.add(table, BorderLayout.SOUTH);
		
		frame.getContentPane().add(tabs);
		tabs.add("Single Player",game);	
		tabs.add("TA: set word",view2);
		
		frame.setPreferredSize(new Dimension(400,400));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} 
}
