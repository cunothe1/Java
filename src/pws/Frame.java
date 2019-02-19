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
import java.util.Random;
import java.beans.EventHandler;
import java.awt.event.*;

import javafx.event.ActionEvent;
import java.util.EventListener;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.MouseListener;

public class Frame extends Application { //implements MouseListener 
	public Stage stage;
	public Frame frame;
    public static void main(String[] args) {
        launch(args);
    }
    
	World world = new World(); //hier de wereld aanmaken
	Mouse mouse = new Mouse(); // muis erbij
	Characters selectA = world.unit[1];
	Characters selectB = world.unit[1];
	public int turn = 1;
    @Override
    public void start (Stage primaryStage) throws Exception {
    	stage = primaryStage;
    	frame = this;
    	//=================================================================================================================
    	//part 1 World
    	//=================================================================================================================
    	

    	mouse.setWorld(world);
    	mouse.setFrame(this);
    	world.worldGeneration(30,25,5,10,4,15); //uitvoeren worldGeneration
    	

    	//=================================================================================================================
    	//part 2 Units
    	//=================================================================================================================
    	world.unitAdd("empty",0);
    	world.unitAdd("archer", 1); //unit[1]
    	world.unitAdd("lancer", 2); //unit[2]
    	//=================================================================================================================
    	//part last Drawing
    	//=================================================================================================================
        primaryStage.setTitle("De game");
        Group root = new Group();
        Scene sc = new Scene(root);
        Canvas canvas = new Canvas(1500, 900);// border
        GraphicsContext g = canvas.getGraphicsContext2D();
        mouse.setG(g);
        draw(g, world); //aanstellen van de variabelen voor draw methode
        root.getChildren().add(canvas);
        
        /*Draws rectangles for units
         * this should be temporarly
         * */
        Rectangle unitRect[] = new Rectangle[255];
        int i;
        for (i=2;i<=3;i+=1) {
        unitRect[i] = new Rectangle(); // character in wereld spawnen binnen de grenzen // WARRIOR
        unitRect[i].setWidth(30);
        unitRect[i].setHeight(30);
        if (world.unit[i].getInfo(0)==1) unitRect[i].setFill(Color.BLUE); //if archer -> blue
        if (world.unit[i].getInfo(0)==3) unitRect[i].setFill(Color.GREEN); //if lancer -> green
        //unitRect[i].setX(world.unit[i].getX()); 
        Random r = new Random();
  	    unitRect[i].setX(r.nextInt((3 - 1) + 1)* 50 + 160);
  	    unitRect[i].setY(r.nextInt((16 - 1) + 1)* 50 + 10);
        //unitRect[i].setY(world.unit[i].getY()); 
        root.getChildren().add(unitRect[i]);
        }
        
        sc.setFill(Color.RED);
    	Button btn = new Button(); 
    	Button btn2 = new Button();
    	Button btn3 = new Button();
    	Button btn4 = new Button();
    	Button btn5 = new Button();
    	//note: bij deze functies is het noodzakelijk dat image in het mapje van pws wordt gedaan ipv images map 
    	//anders werkt het niet
    	Image image = new Image(getClass().getResourceAsStream("NextTurnB.png"));
    	Image image3 = new Image(getClass().getResourceAsStream("Exit.png"));
    	Image image4 = new Image(getClass().getResourceAsStream("Manual.png"));
    	Image image5 = new Image(getClass().getResourceAsStream("Restart.png"));
    	Image image6 = new Image(getClass().getResourceAsStream("CloseM.png"));
    	//bij deze maakt het niet uit (imageview)
    	ImageView image2 = new ImageView("NextTurn.png");
    	ImageView img = new ImageView("Rule.png");
    	btn.setGraphic(new ImageView(image));
    	btn.setStyle("-fx-background-color: transparent");

        //eerste button voor turn, next turm afbeelding die wegvaagt en rode en blauwe achtergrondwisseling
        btn.setLayoutX(140);
        btn.setLayoutY(795);
        btn.setOnAction(e -> {
        if(turn == 1) {
        root.getChildren().add(image2);
        image2.setLayoutX(480);
        image2.setLayoutY(200);
        }	
        turn += 1;
     	if(turn % 2 == 0) {sc.setFill(Color.BLUE);
     	}
     	else { sc.setFill(Color.RED);
     	}
     	FadeTransition ft = new FadeTransition(Duration.millis(3000), image2);
       	ft.setFromValue(1.0);
       	ft.setToValue(0.0);
       	ft.play();
        });
        
        //Afsluiten van de gamevenster
        btn2.setLayoutX(793);
        btn2.setLayoutY(795);
        btn2.setGraphic(new ImageView(image3));
    	btn2.setStyle("-fx-background-color: transparent");
        btn2.setOnAction(e -> { 
        	System.exit(0);
        });
        
        //Zichtbaar maken van uitleg
        btn3.setLayoutX(358);
        btn3.setLayoutY(795);
        btn3.setGraphic(new ImageView(image4));
        btn3.setStyle("-fx-background-color: transparent");
        btn3.setOnAction(e -> { 
        root.getChildren().add(img);
        img.setLayoutX(100);
        img.setLayoutY(50);
        });
     
        //MOEILIJk werkt niet
        btn4.setLayoutX(1010);
        btn4.setLayoutY(795);
        btn4.setGraphic(new ImageView(image5));
    	btn4.setStyle("-fx-background-color: transparent");
        btn4.setOnAction(e -> {
        	turn = 1;
        	root.getChildren().clear();
        	try {
				frame.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        
        	
        });
        
        //Sluiten van uitleg
        btn5.setLayoutX(576);
        btn5.setLayoutY(795);
        btn5.setGraphic(new ImageView(image6));
        btn5.setStyle("-fx-background-color: transparent");
        btn5.setOnAction(e -> { 
           	root.getChildren().remove(img);
        });
        
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);
        root.getChildren().add(btn4);
        root.getChildren().add(btn5);
        
        //this.addMouseListener(new Mouse());
        root.setOnMouseClicked((evt) -> {
        	int rx = (int) (Math.floor(evt.getX()/50)-2); //muis x is nu afgerond op tile waardes
			int ry = (int) (Math.floor(evt.getY()/50)+1); //muis y is nu afgerond op tile waardes
		//VOor muisfunctie hier iets invullen
			
			if (world.getUnitAt(rx,ry)!=world.unit[1]) {
				if (selectA==world.unit[1]) {
					selectA=world.getUnitAt(rx,ry);
					drawMoves(selectA);
				} else {
					selectB=world.getUnitAt(rx,ry);
				}
			} else {
				
			}
        }) ;
      
        primaryStage.setScene(sc);
        primaryStage.show();
        
        
    }
	public void drawMoves(Characters c) {
		int x = c.getInfo(9);
		int y = c.getInfo(10);
		int s = c.getInfo(7);
		String left = world.tile[x-1][y];
		String right = world.tile[x+1][y];
		String up = world.tile[x][y-1];
		String down = world.tile[x][y+1];
		if (left=="mountains"&&s>=4) /*draw left*/;
		if (left=="forest"||left=="dunes") { if (s>=2)/*draw left*/;}
		if (left=="plains"||left=="temple"||left=="base") { if (s>=1)/*draw left*/ ;}
		if (up=="mountains"&&s>=4)/*draw up*/;
		if (up=="forest"||up=="dunes") { if (s>=2)/*draw up*/;}
		if (up=="plains"||up=="temple"||up=="base") { if (s>=1)/*draw up*/;}
		if (down=="mountains"&&s>=4)/*draw down*/;
		if (down=="forest"||down=="dunes") { if (s>=2)/*draw down*/;}
		if (down=="plains"||down=="temple"||down=="base") { if (s>=1)/*draw down*/;}
		if (right=="mountains"&&s>=4)/*draw right*/;
		if (right=="forest"||right=="dunes") { if (s>=2)/*draw right*/;}
		if (right=="plains"||right=="temple"||right=="base") { if (s>=1)/*draw right*/;}
	}
    
	public void draw(GraphicsContext g, World w) { //tekenen van de wereld
        String[][] c = w.getTile(); 
    	g.setStroke(Color.BLACK);
    	Image forest = new Image("forest.png");
    	Image dunes = new Image("dunes.png"); 
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
		    	if(c[i][j]=="forest") { g.drawImage(forest,d,e); } //FOREST
		    	if(c[i][j]=="dunes") { g.drawImage(dunes,d,e);}//DUNES
		    	if(c[i][j]=="mountain") { g.drawImage(mountain,d,e);}//MOUNTAIN
		    	if(c[i][j]=="water") { g.drawImage(river,d,e);}//WATER
		    	if(c[i][j]=="ravine") { g.drawImage(ravine,d,e);}//RAVINE
		    	if(c[i][j]=="plains") { g.drawImage(plains,d,e);}//Plains
		    	if(c[i][j]=="base") { g.drawImage(castle,d,e);} //Base
		    	if(c[i][j]=="temple") { g.drawImage(temple,d,e);}//Temple
		    	 d += 50;
		    }
		    d = 150; //d reset
		    e += 50;
		}
        
        
  
    }
  

    
}