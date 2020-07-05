package model;

/*
 * File: Slime.Java
 * Author: Steve Prisco
 * Description: Describes the Slime and extends tile
 */

import javafx.scene.image.Image;

public class Slime extends Tile
{
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
			return "S";
		
		return "_";
	}
	
	public String getFoundText()
	{
		return "S";
	}
	
	public Image getImage()
	{
		return new Image("file:images/Slime.png", false);
	}
}
