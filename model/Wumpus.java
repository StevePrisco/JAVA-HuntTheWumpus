package model;

/*
 * File: Wumpus.Java
 * Author: Steve Prisco
 * Description: Describes the Wumpus and extends tile
 */

import javafx.scene.image.Image;

public class Wumpus extends Tile
{
	private int myRow;
	private int myColumn;
	
	public Wumpus()
	{
		myRow = 0;
		myColumn = 0;
	}
	
	public Wumpus(int row, int column)
	{
		myRow = row;
		myColumn = column;
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
			return "W";
		
		return "_";
	}
	
	public String getFoundText()
	{
		return "W";
	}
	
	public boolean getFound()
	{
		return this.found;
	}
	
	public void setFound(boolean bool)
	{
		this.found = bool;
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
	
	public Image getImage()
	{
		return new Image("file:images/Wumpus.png", false);
	}
	
	// spawns wumpus on the map that was passed to it, also places surrounding blood tiles
	public void spawn(Tile[][] map)
	{
		map[this.getRow()][this.getColumn()] = this;
		
		// this placed, now place blood...
		
		int tempRow = this.getRow();
		int tempCol = this.getColumn();
		
		// place "inner" blood tiles...
		
		// three bloods immediately above this...
		if (tempRow-1 < 0)
		{
			if (tempCol-1 < 0)
			{
				map[11][11] = new Blood();
			}else
			{
				map[11][tempCol-1] = new Blood();
			}

			map[11][tempCol] = new Blood();
			
			if (tempCol+1 > 11)
			{
				map[11][0] = new Blood();
			}else
			{
				map[11][tempCol+1] = new Blood();
			}
		}else
		{
			if (tempCol-1 < 0)
			{
				map[tempRow-1][11] = new Blood();
			}else
			{
				map[tempRow-1][tempCol-1] = new Blood();
			}

			map[tempRow-1][tempCol] = new Blood();
			
			if (tempCol+1 > 11)
			{
				map[tempRow-1][0] = new Blood();
			}else
			{
				map[tempRow-1][tempCol+1] = new Blood();
			}
		}
		
		// L and R of this
		if (tempCol-1 < 0)
		{
			map[tempRow][11] = new Blood();
		}else
		{
			map[tempRow][tempCol-1] = new Blood();
		}
		
		if (tempCol+1 > 11)
		{
			map[tempRow][0] = new Blood();
		}else
		{
			map[tempRow][tempCol+1] = new Blood();
		}
		
		// 3 immediately below this...
		if (tempRow+1 > 11)
		{
			if (tempCol-1 < 0)
			{
				map[0][11] = new Blood();
			}else
			{
				map[0][tempCol-1] = new Blood();
			}

			map[0][tempCol] = new Blood();
			
			if (tempCol+1 > 11)
			{
				map[0][0] = new Blood();
			}else
			{
				map[0][tempCol+1] = new Blood();
			}
		}else
		{
			if (tempCol-1 < 0)
			{
				map[tempRow+1][11] = new Blood();
			}else
			{
				map[tempRow+1][tempCol-1] = new Blood();
			}

			map[tempRow+1][tempCol] = new Blood();
			
			if (tempCol+1 > 11)
			{
				map[tempRow+1][0] = new Blood();
			}else
			{
				map[tempRow+1][tempCol+1] = new Blood();
			}
		}
		
		// place outer tiles...
		
		// most "north" tile
		if (tempRow-2 == -2)
		{
			map[10][tempCol] = new Blood();
		}else if (tempRow-2 == -1)
		{
			map[11][tempCol] = new Blood();
		}else
		{
			map[tempRow-2][tempCol] = new Blood();
		}

		// most "south" tile...
		if (tempRow+2 == 13)
		{
			map[1][tempCol] = new Blood();
		}else if (tempRow+2 == 12)
		{
			map[0][tempCol] = new Blood();
		}else
		{
			map[tempRow+2][tempCol] = new Blood();
		}
		
		// most "west" tile...
		if (tempCol-2 == -2)
		{
			map[tempRow][10] = new Blood();
		}else if (tempCol-2 == -1)
		{
			map[tempRow][11] = new Blood();
		}else
		{	
			map[tempRow][tempCol-2] = new Blood();
		}
		
		// most "east" tile...
		if (tempCol+2 == 13)
		{
			map[tempRow][1] = new Blood();
		}else if (tempCol+2 == 12)
		{
			map[tempRow][0] = new Blood();
		}else
		{
			map[tempRow][tempCol+2] = new Blood();
		}
	}
}
