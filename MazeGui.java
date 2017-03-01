import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;

public class MazeGui {

	JFrame frame;
	private JTextField textField;
	private JTextField textRows;
	private JTextField textCols;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MazeGui window = new MazeGui();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public MazeGui(MyMaze myMaze) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
			}
		});
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(true);
		frame.setBounds(100, 100, 818, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(47, 16, 705, 492);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton buttonCreateMaze = new JButton("Create Maze!");
		buttonCreateMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int num1,num2;
				try {
					num1 = Integer.parseInt(textRows.getText());
					num2 = Integer.parseInt(textCols.getText());
					MyMaze myMaze = new MyMaze(num1, num2);
					
				} catch (Exception e) {
					
				}
			}
		});
		buttonCreateMaze.setBackground(Color.BLACK);
		buttonCreateMaze.setForeground(Color.BLACK);
		buttonCreateMaze.setBounds(358, 527, 149, 29);
		frame.getContentPane().add(buttonCreateMaze);
		
		textRows = new JTextField();
		textRows.setText("rows");
		textRows.setBounds(47, 524, 73, 26);
		frame.getContentPane().add(textRows);
		textRows.setColumns(10);
		
		textCols = new JTextField();
		textCols.setText("cols");
		textCols.setColumns(10);
		textCols.setBounds(149, 524, 73, 26);
		frame.getContentPane().add(textCols);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(767, 0, 26, 572);
		frame.getContentPane().add(scrollBar);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
