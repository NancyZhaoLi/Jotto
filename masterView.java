import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.event.*;
import java.awt.Container;
import java.util.Observable;
import java.util.Observer;

class masterView extends JPanel implements Observer {
	private JPanel container = new JPanel();
	private JPanel sp = new JPanel();
	private JPanel dp = new JPanel();
	private JButton sb = new JButton("Single Player");
	private JButton db = new JButton("Double Player");
	private Model model;
	private CardLayout cl = new CardLayout();
	
	masterView(Model model_) {
		// set the model 
		model = model_;
		
		container.setLayout(cl);
		sp.add(sb);
		dp.add(db);
		container.add(sp,"1");
		container.add(dp,"2");
		
		cl.show(container, "1");
		
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)
		sb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(container,"2");
			}
		});	
		db.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(container,"1");
			}
		});
	} 

	// Observer interface 
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("View: update");
		//button.setText(Integer.toString(model.getCounterValue()));		
	}
} 
