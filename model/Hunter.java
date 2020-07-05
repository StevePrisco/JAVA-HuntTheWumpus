package model;

import javafx.scene.image.Image;

/*
 * File: Hunter.Java
 * Author: Steve Prisco
 * Description: Describes the hunter and extends tile
 */

public class Hunter extends Tile
{
	private int myRow;
	private int myColumn;
	
	public Hunter()
	{
		myRow = 0;
		myColumn = 0;
	}
	
	public Hunter(int row, int column)
	{
		myRow = row;
		myColumn = column;
	}
	
	// returns the row that the hunter is currently in
	public int getRow()
	{
		return this.myRow;
	}
	
	// returns the column that the hunter is currently in
	public int getColumn()
	{
		return this.myColumn;
	}
	
	public String getText()
	{
		return "O";
	}
	
	public String getFoundText()
	{
		return "O";
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
	
	// puts hunter on the map that was passed to it, assumes hunter has been given a valid position
	public void spawn(Tile[][] map)
	{
		map[this.getRow()][this.getColumn()] = this;
	}
	
	public Image getImage()
	{
		return new Image("file:images/TheHunter.png", false);
	}
}
