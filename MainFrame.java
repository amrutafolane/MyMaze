import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	public MainFrame (String title, MyMaze myMaze, int m, int n) {

		super(title);

		//Set layout manager
		setLayout(new BorderLayout());

		//Create Swing component
		final JTextArea textArea = new JTextArea();
		JButton button = new JButton("CLICK FOR A MAZE!");

		//Add Swing components to the control pane
		Container c = getContentPane();

		c.add(textArea, BorderLayout.CENTER);
		c.add(button, BorderLayout.SOUTH);

		//Add behavior
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				String finalMaze = myMaze.printMaze(); 
				textArea.removeAll();
				textArea.append(finalMaze);
			}
		});	
	} 

}
