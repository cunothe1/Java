package pws;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javafx.scene.paint.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
	
	  public static void main(String[] args) throws IOException {
	        JFrame frame = buildFrame();

	        final BufferedImage image = ImageIO.read(new File("C:\\Users\\Lenovo\\Pictures\\GREEN.jpg"));

	        JPanel pane = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(image, 20, 20, null);
	            }
	        };


	        frame.add(pane);
	    }


	    private static JFrame buildFrame() {
	        JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        frame.setSize(1550, 1000);
	        frame.setVisible(true);
	        return frame;
	    }
	  
}

