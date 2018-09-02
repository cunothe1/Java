package pws;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
public class Window extends JFrame implements ActionListener {
	JFrame frame = new JFrame("GameDemo");
	public static void main(String[] args) {
		new Window().setVisible(true);
	}
	
	private Window(){
	super("Demo");
	setSize(600,600);
	setResizable(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	JButton b1 = new JButton("Start");
	JButton b2 = new JButton("Exit");
	setLayout(new FlowLayout());
	b1.addActionListener(this);
	b2.addActionListener(this);
	add(b1);
	add(b2);
	
	}
	
	public void actionPerformed(ActionEvent a) {
		String name = a.getActionCommand();
		if(name.equals("Start")) {
			World.worldGeneration();
		} else if (name.equals("Exit")) {
			System.exit(0);
		}
	}

}
