package pws;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class Frame extends Application  {
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start (Stage primaryStage) {
    	World world = new World(); //hier de wereld aanmaken
    	world.worldGeneration(); //uitvoeren worldGeneration
    	
    	
        primaryStage.setTitle("GOGOGO");
        Group root = new Group();
        Canvas canvas = new Canvas(1550, 1000); // border
        GraphicsContext g = canvas.getGraphicsContext2D();
        
        draw(g, world); //aanstellen van de variabelen voor draw methode
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        // Canvas opzetten, een frame bouwen, maken van draw variabele g
       
        
     
    }

    private void draw(GraphicsContext g, World w) { //tekenen van de wereld
        String[][] c = w.getTile(); // c = tile[][]
    	g.setStroke(Color.BLACK);
    	Image forest = new Image("file:C:\\Users\\Lenovo\\Pictures\\GREEN.jpg");
    	Image dunes = new Image("dunes.jpg"); // de afbeeldingen moeten zelf gemaakt worden en ze moeten 50x50 zijn !!!!
    	Image mountain = new Image("mountain.jpg"); 
    	Image river = new Image("river.jpg");
    	Image ravine = new Image("ravine.jpg");
    	Image plains = new Image("plains.jpg");	
    	Image ocean = new Image("ocean.jpg");	
    	int a = 150; //x-co voor tabel
        int b = 0; // y-co voor tabel
        for(int i = 0; i<17; i++) { //loop voor tabel maken
        for(int j = 0; j<25; j++) {
        g.strokeRect(a, b, 50, 50);	
        a += 50;
        }
        a = 150;
        g.strokeRect(a, b, 50, 50);
        b += 50;
        }
        int d = 150; //x-co voor landschappen
        int e = 0; // y-co voor landschappen
        for (int i = 1; i < c.length; i++) {
		    for (int j = 1; j < c[i].length; j++) {
		    	if(c[i][j].contains("i")) { //FOREST
		    		g.drawImage(forest,d,e);
		    	}
		    	if(c[i][j].contains("-")) { //DUNES
		    		g.drawImage(dunes,d,e);
		    	}
		    	if(c[i][j].contains("M")) { //MOUNTAIN
		    		g.drawImage(mountain,d,e);
		    	}
		    	if(c[i][j].contains("~")) { //RIVER
		    		g.drawImage(river,d,e);
		    	}
		    	if(c[i][j].contains("X")) { //RAVINE
		    		g.drawImage(ravine,d,e);
		    	}
		    	if(c[i][j].contains(" ")) { //Plains
		    		g.drawImage(plains,d,e);
		    	}
		    	if(c[i][j].contains("Y")) { //OCEAN
		    		g.drawImage(ocean,d,e);
		    	}
		    	 d += 50;
		    }
		    d = 150; //d reset
		    e += 50;
		}
        

  
    }
    
}
