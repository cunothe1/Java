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
	//Mouse mouse = new Mouse(); // muis erbij
	Characters selectA = world.unit[1];
	Characters selectB = world.unit[1];
	public int turn = 0;//TIJDELIJK
    @Override
    public void start (Stage primaryStage) throws Exception {
    	stage = primaryStage;
    	frame = this;
    	
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
    	world.unitAdd("empty",0); //unit[1]
    	world.unitAdd("archer", 1); //unit[2]
    	world.unitAdd("lancer", 2); //unit[3]
		if (selectA==null)selectA=world.unit[1];
		if (selectB==null)selectB=world.unit[1];
		world.unitPlace(2,world.randomRange(1, 3),world.randomRange(5, 12));
		world.unitPlace(3,world.randomRange(18, 21),world.randomRange(5, 12));
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
        if(turn == 0) {
        root.getChildren().add(image2);
        image2.setLayoutX(480);
        image2.setLayoutY(200);
        }	
      //nextTurn();=====================================================================================
        turn += 1;
        if (turn>world.players) turn=1;
        int j;
        for(j=2;j<=world.unitNew;j+=1) {
        	Characters unit=world.unit[j];
        	if (unit.getInfo(11)==turn) {
        		if (unit.getInfo(0)==1) {
        			unit.setInfo(7,3); //archer
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==2) {
        			unit.setInfo(7,6); //assassin
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==3) {
        			unit.setInfo(7,3); //builder
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==4) {
        			unit.setInfo(7,3); //Commander
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==5) {
        			unit.setInfo(7,5); //Healer
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==6) {
        			unit.setInfo(7,5); //Houndmaster
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==7) {
        			unit.setInfo(7,8); //Knight
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==8) {
        			unit.setInfo(7,2); //Lancer
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==9) {
        			unit.setInfo(7,3); //Mage
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==10) {
        			unit.setInfo(7,2); //Paladin
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==11) {
        			unit.setInfo(7,3); //summoned
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==12) {
        			unit.setInfo(7,2); //summoner
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==13) {
        			unit.setInfo(7,3); //swordsman
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==14) {
        			unit.setInfo(7,8); //templar
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		if (unit.getInfo(0)==15) {
        			unit.setInfo(7,1); //trebuchet
            		if (world.getTile(unit.getInfo(9),unit.getInfo(10))=="temple") {
            			unit.setInfo(5, unit.getInfo(5)+30);
                		if (unit.getInfo(5)>50)unit.setInfo(5, 50);
            		}}
        		unit.setInfo(8,0);
        	}
        }
     	if(turn == 2) {sc.setFill(Color.BLUE);
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
            //exitGame();=====================================================================================
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
				world.restart();
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
        
        
        
       
        
        
       // this.addMouseListener(new Mouse());
        root.setOnMouseClicked((evt) -> {
        	int rx = (int) (Math.floor(evt.getX()/50)-2); //muis x is nu afgerond op tile waardes
			int ry = (int) (Math.floor(evt.getY()/50)+1); //muis y is nu afgerond op tile waardes
			if (rx<=1) rx=1;
			if (ry<=1) ry=1;
        	System.out.println(" ");
        	System.out.println(turn);
    		System.out.println(world.getUnitAt(rx,ry).getInfo(15)+","+world.getUnitAt(rx, ry));
			if (world.getUnitAt(rx,ry)!=world.unit[1]) { //als plaats niet leeg is
				if (selectA==world.unit[1]) { //als uA leeg is
					selectA=world.getUnitAt(rx,ry);
					selectA.setInfo(8, 1);
					if (selectA.getInfo(11)==turn) { //als same team
						drawMoves(selectA);
					} else { 
						selectA.setInfo(8, 0);
						selectA = world.unit[1];
					}
				} else { //uA is niet leeg
					selectB=world.getUnitAt(rx,ry);
					selectB.setInfo(8, 1);
					if (selectB.getInfo(11)==turn) { //same team
						
						//APPLY SPECIALS HERE
						//Op het eind do A&B terug naar unit[1]
					} else { 
						selectA.attack(selectB.getInfo(15));
						//unitRect[selectA.getInfo(15)].setX(selectA.getInfo(9)*50+110); 
				        //unitRect[selectA.getInfo(15)].setY(selectA.getInfo(10)*50-40); 
						//unitRect[selectB.getInfo(15)].setX(selectB.getInfo(9)*50+110); 
				        //unitRect[selectB.getInfo(15)].setY(selectB.getInfo(10)*50-40); 
						selectA.setInfo(2, 1);
						selectA.setInfo(8, 0);
						selectB.setInfo(8, 0);
						selectA=world.unit[1];
						selectB=world.unit[1];
					}
					selectB.setInfo(8, 0);
					selectB = world.unit[1];
				}
				} else { //plaats is leeg
					if (selectA!=world.unit[1]) {
						if (selectA.getInfo(9)==rx-1&&selectA.getInfo(10)==ry) {selectA.move(rx, ry);} //links
						if (selectA.getInfo(9)==rx+1&&selectA.getInfo(10)==ry) {selectA.move(rx, ry);}//rechts
						if (selectA.getInfo(9)==rx&&selectA.getInfo(10)==ry-1) {selectA.move(rx, ry);}//boven
						if (selectA.getInfo(9)==rx&&selectA.getInfo(10)==ry+1) {selectA.move(rx, ry);}//onder
						//unitRect[selectA.getInfo(15)].setX(selectA.getInfo(9)*50+110); 
				        //unitRect[selectA.getInfo(15)].setY(selectA.getInfo(10)*50-40); 
					}
					selectA.setInfo(8, 0);
					selectA=world.unit[1];
					
				}
			draw(g,world);
			drawUnits(g,world);
			Image smoke = new Image("gray_select.png");
			g.drawImage(smoke,selectA.getX()-10,selectA.getY()-10);
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
    
	
	 /*Draws rectangles for units
     * this should be temporarly
     * 
     * */
	public void drawUnits(GraphicsContext g, World w) {
    Group root = new Group();
	root.getChildren().clear();
    int i;
    Image blueSword = new Image("blue_sword.png");
    Image blueSpear = new Image("blue_spear.png");
    Image blueTemplar = new Image("blue_templar.png");
    Image blueKnight = new Image("blue_knight.png");
    Image blueBow = new Image("blue_bow.png");
    Image blueHealer = new Image("blue_healer.png");
    //Image blueKnife = new Image("blue_knife.png");
    //Image blueBuilder = new Image("blue_builder.png");
    //Image blueHero = new Image("blue_hero.png");
    //Image blueMage = new Image("blue_mage.png");
    Image bluePaladin = new Image("blue_paladin.png");
    //Image blueSummon = new Image("blue_summon.png");
    //Image blueSummoner = new Image("blue_summoner.png");
    //Image blueTrebuchet = new Image("blue_trebuchet.png");
    //Image blueHound = new Image("blue_hound.png");
    Image redSword = new Image("red_sword.png");
    Image redSpear = new Image("red_spear.png");
    Image redTemplar = new Image("red_templar.png");
    Image redKnight = new Image("red_knight.png");
    Image redBow = new Image("red_bow.png");
    Image redHealer = new Image("red_healer.png");
    //Image redKnife = new Image("red_knife.png");
    //Image redBuilder = new Image("red_builder.png");
    //Image redHero = new Image("red_hero.png");
    //Image redMage = new Image("red_mage.png");
    Image redPaladin = new Image("red_paladin.png");
    //Image redSummon = new Image("red_summon.png");
    //Image redSummoner = new Image("red_summoner.png");
    //Image redTrebuchet = new Image("red_trebuchet.png");
    //Image redHound = new Image("red_hound.png");
    for (i=2;i<=world.unitNew;i+=1) {
    	if (world.unit[i].getInfo(11)==1) {
    		if (world.unit[i].getInfo(0)==1) g.drawImage(blueBow,world.unit[i].getX()-10,world.unit[i].getY()-10); //archer
    		//if (world.unit[i].getInfo(0)==2) g.drawImage(blueKnife,world.unit[i].getX()-10,world.unit[i].getY()-10); //assassin
    		//if (world.unit[i].getInfo(0)==3) g.drawImage(blueBuilder,world.unit[i].getX()-10,world.unit[i].getY()-10); //builder
    		//if (world.unit[i].getInfo(0)==4) g.drawImage(blueHero,world.unit[i].getX()-10,world.unit[i].getY()-10); //commander
    		if (world.unit[i].getInfo(0)==5) g.drawImage(blueHealer,world.unit[i].getX()-10,world.unit[i].getY()-10); //healer
    		//if (world.unit[i].getInfo(0)==6) g.drawImage(blueHound,world.unit[i].getX()-10,world.unit[i].getY()-10); //houndmaster
    		if (world.unit[i].getInfo(0)==7) g.drawImage(blueKnight,world.unit[i].getX()-10,world.unit[i].getY()-10); //knight
    		if (world.unit[i].getInfo(0)==8) g.drawImage(blueSpear,world.unit[i].getX()-10,world.unit[i].getY()-10); //Lancer
    		//if (world.unit[i].getInfo(0)==9) g.drawImage(blueMage,world.unit[i].getX()-10,world.unit[i].getY()-10); //Mage
    		if (world.unit[i].getInfo(0)==10) g.drawImage(bluePaladin,world.unit[i].getX()-10,world.unit[i].getY()-10); //Paladin
    		//if (world.unit[i].getInfo(0)==11) g.drawImage(blueSummon,world.unit[i].getX()-10,world.unit[i].getY()-10); //Summoned
    		//if (world.unit[i].getInfo(0)==12) g.drawImage(blueSummoner,world.unit[i].getX()-10,world.unit[i].getY()-10); //Summoner
    		if (world.unit[i].getInfo(0)==13) g.drawImage(blueSword,world.unit[i].getX()-10,world.unit[i].getY()-10); //Swordsman
    		if (world.unit[i].getInfo(0)==14) g.drawImage(blueTemplar,world.unit[i].getX()-10,world.unit[i].getY()-10); //Templar
    		//if (world.unit[i].getInfo(0)==15) g.drawImage(blueTrebuchet,world.unit[i].getX()-10,world.unit[i].getY()-10); //Trebuchet
    	}
    	if (world.unit[i].getInfo(11)==2) {
    		if (world.unit[i].getInfo(0)==1) g.drawImage(redBow,world.unit[i].getX()-10,world.unit[i].getY()-10); //archer
    		//if (world.unit[i].getInfo(0)==2) g.drawImage(redKnife,world.unit[i].getX()-10,world.unit[i].getY()-10); //assassin
    		//if (world.unit[i].getInfo(0)==3) g.drawImage(redBuilder,world.unit[i].getX()-10,world.unit[i].getY()-10); //builder
    		//if (world.unit[i].getInfo(0)==4) g.drawImage(redHero,world.unit[i].getX()-10,world.unit[i].getY()-10); //commander
    		if (world.unit[i].getInfo(0)==5) g.drawImage(redHealer,world.unit[i].getX()-10,world.unit[i].getY()-10); //healer
    		//if (world.unit[i].getInfo(0)==6) g.drawImage(redHound,world.unit[i].getX()-10,world.unit[i].getY()-10); //houndmaster
    		if (world.unit[i].getInfo(0)==7) g.drawImage(redKnight,world.unit[i].getX()-10,world.unit[i].getY()-10); //knight
    		if (world.unit[i].getInfo(0)==8) g.drawImage(redSpear,world.unit[i].getX()-10,world.unit[i].getY()-10); //Lancer
    		//if (world.unit[i].getInfo(0)==9) g.drawImage(redMage,world.unit[i].getX()-10,world.unit[i].getY()-10); //Mage
    		if (world.unit[i].getInfo(0)==10) g.drawImage(redPaladin,world.unit[i].getX()-10,world.unit[i].getY()-10); //Paladin
    		//if (world.unit[i].getInfo(0)==11) g.drawImage(redSummon,world.unit[i].getX()-10,world.unit[i].getY()-10); //Summoned
    		//if (world.unit[i].getInfo(0)==12) g.drawImage(redSummoner,world.unit[i].getX()-10,world.unit[i].getY()-10); //Summoner
    		if (world.unit[i].getInfo(0)==13) g.drawImage(redSword,world.unit[i].getX()-10,world.unit[i].getY()-10); //Swordsman
    		if (world.unit[i].getInfo(0)==14) g.drawImage(redTemplar,world.unit[i].getX()-10,world.unit[i].getY()-10); //Templar
    		//if (world.unit[i].getInfo(0)==15) g.drawImage(redTrebuchet,world.unit[i].getX()-10,world.unit[i].getY()-10); //Trebuchet
    	}
    }
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
