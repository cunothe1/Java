package pws;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javafx.scene.canvas.GraphicsContext;


public class Mouse implements MouseListener{
	World world;
	Frame frame;
	GraphicsContext g;
   public void setWorld(World w){world=w;}
   public void setFrame(Frame f){frame=f;}
   public void setG(GraphicsContext gr) {g=gr;}
   
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		   int mx = e.getX();
		   int my = e.getY();
		   
		   for (int j = 1; j <= 16; j++) {
			    for (int i = 1; i <= 24; i++) {
						if (world.tile[i][j]!="~") {
							world.tile[i][j]="~";
							frame.draw(g, world);
						
					}
			    }
			}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}