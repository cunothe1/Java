package pws;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
 
public class Frame extends Application  {
	
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start (Stage primaryStage) {

    	//=================================================================================================================
    	//part 1 World
    	//=================================================================================================================
    	World world = new World(); //hier de wereld aanmaken
    	
    	world.worldGeneration(30,25,5,10,4,15); //uitvoeren worldGeneration
    	

    	//=================================================================================================================
    	//part 2 Units
    	//=================================================================================================================
    	world.unitAdd("archer", 1); //unit[1]
    	world.unitAdd("lancer", 2); //unit[2]
    	//=================================================================================================================
    	//part last Drawing
    	//=================================================================================================================
        primaryStage.setTitle("De game");
        Group root = new Group();
        Canvas canvas = new Canvas(1200, 800); // border
        GraphicsContext g = canvas.getGraphicsContext2D();
        
        draw(g, world); //aanstellen van de variabelen voor draw methode
        root.getChildren().add(canvas);
        
        /*Draws rectangles for units
         * this should be temporarly
         * */
        Rectangle unitRect[] = new Rectangle[255];
        int drawDis=2;
        int i;
        for (i=1;i<=drawDis;i+=1) {
        unitRect[i] = new Rectangle(); // character in wereld spawnen binnen de grenzen // WARRIOR
        unitRect[i].setWidth(30);
        unitRect[i].setHeight(30);
        if (world.unit[i].getInfo(0)==1) unitRect[i].setFill(Color.BLUE); //if archer -> blue
        if (world.unit[i].getInfo(0)==3) unitRect[i].setFill(Color.GREEN); //if lancer -> green
        unitRect[i].setX(world.unit[i].getX()); 
        unitRect[i].setY(world.unit[i].getY()); 
        root.getChildren().add(unitRect[i]);
        }
        
        
        // HET IS IRRITANT MAAR BELANGRIJK OM ELKE PLAYER MET ROOT.GETCH... TE ADDEN
        // IMPORTANTE !!!
        // HARDCODE MAAR MOEILIJK ANDERS
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        // Canvas opzetten, een frame bouwen, maken van draw variabele g
       
        
     
    }

    private void draw(GraphicsContext g, World w) { //tekenen van de wereld
        String[][] c = w.getTile(); // c = tile[][]
    	g.setStroke(Color.BLACK);
    	Image forest = new Image("forest.png");
    	Image dunes = new Image("dunes.png"); // de afbeeldingen moeten zelf gemaakt worden en ze moeten 50x50 zijn !!!!
    	Image mountain = new Image("mountain.png");
    	Image river = new Image("water.png");
    	Image ravine = new Image("ravine.png");
    	Image plains = new Image("plains.png");	
    	Image temple = new Image("tempel.png");
    	Image castle = new Image("castle.png");
    	int a = 150; //x-co voor tabel
        int b = 0; // y-co voor tabel
        for(int i = 1; i<=16; i++) { //loop voor tabel maken
        for(int j = 1; j<=24; j++) {
        g.strokeRect(a, b, 50, 50);	
        a += 50;
        }
        a = 150;
        g.strokeRect(a, b, 50, 50);
        b += 50;
        }
        int d = 150; //x-co voor landschappen
        int e = 0; // y-co voor landschappen 
        for (int j = 1; j <= 16; j++) {
		    for (int i = 1; i <= 24; i++) {
		    	if(c[i][j].substring(0,1).contains("i")) { g.drawImage(forest,d,e); } //FOREST
		    	if(c[i][j].substring(0,1).contains("-")) { g.drawImage(dunes,d,e);}//DUNES
		    	if(c[i][j].substring(0,1).contains("M")) { g.drawImage(mountain,d,e);}//MOUNTAIN
		    	if(c[i][j].substring(0,1).contains("~")) { g.drawImage(river,d,e);}//WATER
		    	if(c[i][j].substring(0,1).contains("X")) { g.drawImage(ravine,d,e);}//RAVINE
		    	if(c[i][j].substring(0,1).contains(" ")) { g.drawImage(plains,d,e);}//Plains
		    	if(c[i][j].substring(0,1).contains("O")) {g.drawImage(castle,d,e);} //Base
		    	if(c[i][j].substring(0,1).contains("T")) { g.drawImage(temple,d,e);}//Temple
		    	 d += 50;
		    }
		    d = 150; //d reset
		    e += 50;
		}
        
        
  
    }
    private void mouseCheck(MouseEvent event) {
    	int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;    
        //if ((mouseX == <warrior>.getX()) && (mouseY == <warrior>.getY())) {
        	//MOET NOG AFGEMAAKT WORDEN met een code dat mogelijk maakt dat delen van de map gemarkeerd wordt
        	//Dus if mouseclicked ... hiervoor moet er eerst een code gemaakt worden voor mouseclicked. 
        	//Daarnaast moet er ook een code gemaakt worden voor het bewegen van units.
        //}
    }
}
