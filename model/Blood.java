package model;

/*
 * File: Blood.Java
 * Author: Steve Prisco
 * Description: Describes the blood and extends tile
 */

import javafx.scene.image.Image;

public class Blood extends Tile
{
	// sets the boolean "found" to whatever the argument is
	public void setFound(boolean bool)
	{
		found = bool;
	}
	
	public boolean getFound()
	{
		return this.found;
	}
	
	public String getText()
	{
		if (found)
			return "B";
		
		return "_";
	}
	
	public String getFoundText()
	{
		return "B";
	}
	
	public Image getImage()
	{
		return new Image("file:images/Blood.png", false);
	}
}
