package pws;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import java.beans.EventHandler;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javafx.event.ActionEvent;
import java.util.EventListener;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.event.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Frame extends Application { //implements MouseListener 
		
    public static void main(String[] args) {
        launch(args);
    }
    
    /*@Override
	public void mouseClicked(MouseEvent arg0) { 
    	int i = 0;
    	int maxUnits = 2;
    	if(i < maxUnits) {
    		i = i + 1;
    	}
		System.out.println("YEAH SUCCES");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}*/
	
    
	World world = new World(); //hier de wereld aanmaken
	//Button button;
    @Override
    public void start (Stage primaryStage) throws Exception {
    	
    	//=================================================================================================================
    	//part 1 World
    	//=================================================================================================================
    	
    	
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
        Scene sc = new Scene(root);
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
        Point p = MouseInfo.getPointerInfo().getLocation(); //MOUSE LOCATION GETTER
        int mx = (int) p.getX(); // X-CO muis
    	int my = (int) p.getY(); // Y-CO muis
    	
         
         sc.setOnMouseClicked(new EventHandler<MouseEvent>() { // DIT IS GWN COMPLEET GOED, WEET NIET WAAR HET FOUT GAAT

  			@Override										//ENIGE PROBLEEM KOMT VAN DE FCKING EVENTHANDLERRRRR
  			public void handle(MouseEvent event) {			//https://www.youtube.com/watch?v=_Zvn7xJZYxQ
  				Circle c = new Circle(30);				//https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/plaf/basic/BasicTreeUI.MouseHandler.html
  	     		c.setCenterX(p.getX());					//https://docs.oracle.com/javafx/2/events/convenience_methods.htm
  	     		c.setCenterY(p.getY()); 				//https://docs.oracle.com/javase/7/docs/api/java/beans/EventHandler.html
  	     		root.getChildren().add(c);				//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseClickedProperty
  			} 									//https://docs.oracle.com/javase/tutorial/uiswing/events/generalrules.html
           });								//https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm#CEGJAAFD
         sc.setOnMousePressed(new javafx.event.EventHandler<InputEvent>() {//https://docs.oracle.com/javafx/2/events/filters.htm
//https://stackoverflow.com/questions/16431455/addmouselistener-for-a-jpanel
 			@Override
 			public void handle(InputEvent event) {
 				Circle c = new Circle(30);
 	     		c.setCenterX(p.getX());
 	     		c.setCenterY(p.getY()); 
 	     		root.getChildren().add(c);
 			} 
          }); 
        	
        	//https://stackoverflow.com/questions/40729726/javafx-onmouseclicked
        	//SNAP NIET WAARON SETONMOUSEPRESSED EN MOUSEEVENT NIET LUKKEN :<

        /* button = new Button();
        button.setText("CLICK PLS");
        button.setOnAction(this);
        root.getChildren().add(button); */
        //BUTTON VOOR HET GEVAL DAT
        
        primaryStage.setScene(sc);
        primaryStage.show();
        
       
       /* Mouse ml = new Mouse();
        root.addMouseListener(ml); // HOE WERKT DIT NIET DYING v-v
        addMouseListener(ml);*/
        
      //https://docs.oracle.com/javafx/2/events/filters.htm
       /* canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	public void handle (MouseEvent) {
        		System.out.println("GOGOGO");
        		};
        }); */
       /* if((mx == world.unit[i].getX())&&(my == world.unit[i].getY())) {
    		System.out.println("LEMMMEE");
		}*/
        
        
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
  

    public void mouseCheck() throws AWTException {
    	
        //if ((mouseX == <warrior>.getX()) && (mouseY == <warrior>.getY())) {
        	//MOET NOG AFGEMAAKT WORDEN met een code dat mogelijk maakt dat delen van de map gemarkeerd wordt
        	//Dus if mouseclicked ... hiervoor moet er eerst een code gemaakt worden voor mouseclicked. 
        	//Daarnaast moet er ook een code gemaakt worden voor het bewegen van units.
        //}
    }
}
