package model;

import javafx.scene.image.Image;

/*
 * File: Pit.Java
 * Author: Steve Prisco
 * Description: Describes the pit and extends Tile
 */

public class Pit extends Tile
{
	private int myRow;
	private int myColumn;
	
	public Pit()
	{
		myRow = 0;
		myColumn = 0;
	}
	
	public int getRow()
	{
		return this.myRow;
	}
	
	public int getColumn()
	{
		return this.myColumn;
	}
	
	public String getText()
	{
		if (found)
			return "P";
		
		return "_";
	}
	
	public String getFoundText()
	{
		return "P";
	}
	
	// setters assume input of 0 - 11
	public void setRow(int row)
	{
		this.myRow = row;
	}
	
	public void setColumn(int column)
	{
		this.myColumn = column;
	}
	
	public boolean getFound()
	{
		return this.found;
	}
	
	public void setFound(boolean bool)
	{
		this.found = bool;
	}
	
	public void spawn(Tile[][] map)
	{
		map[this.getRow()][this.getColumn()] = this;
		
		// pit placed, now place slime tiles...
		
		// slime directly north of pit
		if (this.getRow()-1 < 0)
		{
			if (map[0][this.getColumn()].getText() != "W")
			{
				if (map[11][this.getColumn()].getText() == "B")
					map[11][this.getColumn()] = new Goop();
				else
					map[11][this.getColumn()] = new Slime();
			}
		}else
		{
			if (map[0][this.getColumn()].getText() != "W")
			{
				if (map[this.getRow()-1][this.getColumn()].getText() == "B")
					map[this.getRow()-1][this.getColumn()] = new Goop();
				else
					map[this.getRow()-1][this.getColumn()] = new Slime();
			}
		}
		
		// slime directly south of pit
		if (this.getRow()+1 > 11)
		{
			if (map[0][this.getColumn()].getText() != "W")
			{
				if (map[0][this.getColumn()].getText() == "B")
					map[0][this.getColumn()] = new Goop();
				else
					map[0][this.getColumn()] = new Slime();
			}
		}else
		{
			if (map[this.getRow()+1][this.getColumn()].getText() != "W")
			{
				if (map[this.getRow()+1][this.getColumn()].getText() == "B")
					map[this.getRow()+1][this.getColumn()] = new Goop();
				else
					map[this.getRow()+1][this.getColumn()] = new Slime();
			}
		}
		
		//  slime directly west of pit
		if (this.getColumn()-1 < 0)
		{
			if (map[this.getRow()][11].getText() != "W")
			{
				if (map[this.getRow()][11].getText() == "B")
					map[this.getRow()][11] = new Goop();
				else
					map[this.getRow()][11] = new Slime();
			}
		}else
		{
			if (map[this.getRow()][this.getColumn()-1].getText() != "W")
			{
				if (map[this.getRow()][this.getColumn()-1].getText() == "B")
					map[this.getRow()][this.getColumn()-1] = new Goop();
				else
					map[this.getRow()][this.getColumn()-1] = new Slime();
			}
		}
		
		//  slime directly east of pit
		if (this.getColumn()+1 > 11)
		{
			if (map[this.getRow()][0].getText() != "W")
			{
				if (map[this.getRow()][0].getText() == "B")
					map[this.getRow()][0] = new Goop();
				else
					map[this.getRow()][0] = new Slime();
			}
		}else
		{
			if (map[this.getRow()][this.getColumn()+1].getText() != "W")
			{
				if (map[this.getRow()][this.getColumn()+1].getText() == "B")
					map[this.getRow()][this.getColumn()+1] = new Goop();
				else
					map[this.getRow()][this.getColumn()+1] = new Slime();
			}
		}
	}
	
	public Image getImage()
	{
		return new Image("file:images/SlimePit.png", false);
	}
}
