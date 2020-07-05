package model;

/*
 * File: Tile.java
 * Author: Steve Prisco
 * Desc: Describes the tile and its respective functions
 */

import javafx.scene.image.Image;

public class Tile
{
	public boolean found = true;
	
	// sets the boolean "found" to whatever the argument is
	public void setFound(boolean bool)
	{
		found = bool;
	}
	
	// returns found
	public boolean getFound()
	{
		return this.found;
	}
	
	// returns the text associated with this tile
	public String getText()
	{
		if (found)
			return " ";
		
		return "_";
	}
	
	// returns the "found" text, used for printing the map with hazards and warming revealed
	public String getFoundText()
	{
		return " ";
	}
	
	// returns the image associated with this tile
	public Image getImage()
	{
		if (found)
			return new Image("file:images/Empty.png", false);
		
		return new Image("file:images/Ground.png", false);
	}
}
