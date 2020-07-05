package views;

/*
 * File: ImageView.java
 * Author: Steve Prisco
 * Description: Describes the Graphical representation of Hunt the Wumpus
 */

import java.util.Observable;
import java.util.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.HuntTheWumpusGame;

public class ImageView extends BorderPane implements Observer 
{
	private HuntTheWumpusGame theGame;
	static GridPane grid;
	static Button buttonU;
	static Button buttonD;
	static Button buttonL;
	static Button buttonR;
	static BorderPane window;

	Canvas canvas;
	GraphicsContext gc;

	Image ground;
	Image empty;
	
	public ImageView(HuntTheWumpusGame huntTheWumpus)
	{
		theGame = huntTheWumpus;
		
		ground = new Image("file:images/Ground.png", false);
		empty = new Image("file:images/Empty.png", false);
		
	    initializePane();
	}

	private void initializePane()
	{	

		
		grid = new GridPane();
		canvas = new Canvas(600, 600);
	    
	    gc = canvas.getGraphicsContext2D();
	    
	    gc.setFill(Color.BLACK);
	    gc.fillRect(0, 0, 600, 600);
	    
	    initMap();
		
		setLeft(canvas);
	}
	
	// draws the map on the canvas
	public void drawMap()
	{
		for (int r = 0; r < 12; r++)
	    {
	    	for (int c = 0; c < 12; c++)
	    	{
	    		gc.drawImage(empty, (c*50), (r*50));
	    		
	    		if (theGame.getMap()[r][c].getFound() == true || theGame.getMap()[r][c].getText() == "O")
	    		{
	    			if (theGame.getMap()[r][c].getText() == "O")
	    			{
	    				gc.drawImage(theGame.getTempTile().getImage(), (c*50), (r*50));
	    			}
	    		
	    			gc.drawImage(theGame.getMap()[r][c].getImage(), (c*50), (r*50));
	    		}else
	    			gc.drawImage(ground, (c*50), (r*50));
	        }
	    }
	}
	
	// initializes the map to a bunch of tile images
	public void initMap()
	{
		for (int r = 0; r < 12; r++)
	    {
	    	for (int c = 0; c < 12; c++)
	    	{
	    		if (theGame.getMap()[r][c].getText() != "O")
	    			gc.drawImage(ground, (c*50), (r*50));
	    		else
	    			gc.drawImage(theGame.getHunter().getImage(), (c*50), (r*50));
	        }
	    }
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		drawMap();
		theGame.gameUpdate(theGame.getTempTile());
	}
}
