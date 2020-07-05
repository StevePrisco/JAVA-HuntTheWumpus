package model;

/*
 * File: Goop.Java
 * Author: Steve Prisco
 * Description: Describes the goop and extends tile
 */

import javafx.scene.image.Image;

public class Goop extends Tile
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
			return "G";
		
		return "_";
	}
	
	public String getFoundText()
	{
		return "G";
	}
	
	public Image getImage()
	{
		return new Image("file:images/Goop.png", false);
	}
}
