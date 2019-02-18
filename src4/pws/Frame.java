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
import java.awt.event.MouseListener;

public class Frame extends Application { //implements MouseListener 
		
    public static void main(String[] args) {
        launch(args);
    }
    
	World world = new World(); //hier de wereld aanmaken
	//Mouse mouse = new Mouse(); // muis erbij
	Characters selectA = world.unit[1];
	Characters selectB = world.unit[1];
	//Button button;
	
	
	
	public int turn = 1;//TIJDELIJK
    @Override
    public void start (Stage primaryStage) throws Exception {
    	
    	//=================================================================================================================
    	//part 1 World
    	//=================================================================================================================
    	

    	//mouse.setWorld(world);
    	//mouse.setFrame(this);
    	world.worldGeneration(30,25,5,10,4,15); //uitvoeren worldGeneration
    	world.setFrame(this);
    	
    	

    	//=================================================================================================================
    	//part 2 Units
    	//=================================================================================================================
    	world.unitAdd("empty",0);
    	world.unitAdd("archer", 1); //unit[1]
    	world.unitAdd("lancer", 2); //unit[2]
		if (selectA==null)selectA=world.unit[1];
		if (selectB==null)selectB=world.unit[1];
		world.unitPlace(1,2,2);
		world.unitPlace(2, 12, 4);
    	//=================================================================================================================
    	//part last Drawing
    	//=================================================================================================================
        primaryStage.setTitle("De game");
        Group root = new Group();
        Scene sc = new Scene(root);
        Canvas canvas = new Canvas(1200, 800); // border
        GraphicsContext g = canvas.getGraphicsContext2D();
        //mouse.setG(g);
        draw(g, world); //aanstellen van de variabelen voor draw methode
        root.getChildren().add(canvas);
        
        /*Draws rectangles for units
         * this should be temporarly
         * */
        Rectangle unitRect[] = new Rectangle[255];
        int i;
        for (i=2;i<=world.unitNew;i+=1) {
        unitRect[i] = new Rectangle(); // character in wereld spawnen binnen de grenzen // WARRIOR
        unitRect[i].setWidth(30);
        unitRect[i].setHeight(30);
        if (world.unit[i].getInfo(0)==1) unitRect[i].setFill(Color.BLUE); //if archer -> blue
        if (world.unit[i].getInfo(0)==3) unitRect[i].setFill(Color.GREEN); //if lancer -> green
        unitRect[i].setX(world.unit[i].getX()); 
        unitRect[i].setY(world.unit[i].getY()); 
        root.getChildren().add(unitRect[i]);
       
        }
        
       // this.addMouseListener(new Mouse());
        root.setOnMouseClicked((evt) -> {
        	int rx = (int) (Math.floor(evt.getX()/50)-2); //muis x is nu afgerond op tile waardes
			int ry = (int) (Math.floor(evt.getY()/50)+1); //muis y is nu afgerond op tile waardes
			if (rx<=1) rx=1;
			if (ry<=1) ry=1;
        	System.out.println(world.getUnitAt(rx, ry));
			if (world.getUnitAt(rx,ry)!=world.unit[1]) { //als plaats niet leeg is
				if (selectA==world.unit[1]) { //als uA leeg is
					selectA=world.getUnitAt(rx,ry);
					if (selectA.getInfo(11)==turn) { //als same team
			        	System.out.println("A="+selectA);
						drawMoves(selectA);
					} else { selectA = world.unit[1];}
				} else { //uA is niet leeg
					selectB=world.getUnitAt(rx,ry);
		        	System.out.println("B="+selectB);
					if (selectB.getInfo(11)==turn) { //same team
						//APPLY SPECIALS HERE
						//Op het eind do A&B terug naar unit[1]
					} else{ //ATTACK
						
					}}
				} else { //plaats is leeg
					if (selectA!=world.unit[1]) {
			        	System.out.println("moving"+selectA);
						if (selectA.getInfo(9)==rx-1&&selectA.getInfo(10)==ry) {selectA.move(rx, ry);} //links
						if (selectA.getInfo(9)==rx+1&&selectA.getInfo(10)==ry) {selectA.move(rx, ry);}//rechts
						if (selectA.getInfo(9)==rx&&selectA.getInfo(10)==ry-1) {selectA.move(rx, ry);}//boven
						if (selectA.getInfo(9)==rx&&selectA.getInfo(10)==ry+1) {selectA.move(rx, ry);}//onder
						unitRect[selectA.getInfo(15)].setX(selectA.getInfo(9)*50+110); 
				        unitRect[selectA.getInfo(15)].setY(selectA.getInfo(10)*50-40); 
					}
					selectA=world.unit[1];
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
		if (left=="mountains"&&s>=4)/*draw left*/;
		if (left=="forest"||left=="dunes") { if (s>=2)/*draw left*/;}
		if (left=="plains"||left=="temple"||left=="base") { if (s>=1)/*draw left*/;}
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
