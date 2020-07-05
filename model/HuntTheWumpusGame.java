package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/*
 * File: HuntTheWumpusGame.java
 * Author: Steve Prisco
 * Description: Everything necessary to start playing Hunt the Wumpus!
 */

public class HuntTheWumpusGame extends Observable
{
	private Tile[][] map;
	private Tile tempTile;
	private Hunter hunter;
	private Wumpus wumpus;
	private boolean hasShot;
	private boolean over = false;
	
	// testing below this...
	private TextField outputText;
	private TextArea gameText;
	
	// starts the game!
	public HuntTheWumpusGame(TextField textField, TextArea textArea)
	{
		map = new Tile[12][12];
		hunter = new Hunter();
		wumpus = new Wumpus();
		
		outputText = textField;
		gameText = textArea;
		
		outputText.setDisable(true);
		outputText.setFont(Font.font("Courier", 20));
		outputText.setStyle("-fx-text-fill: black ; -fx-opacity: 1.0;");
		
		init();
		
		setChanged();
	    notifyObservers();
	}
	
	// returns a random number 0 - 11
	public int genRandomCoord()
	{
		Random rand = new Random();
			
		return rand.nextInt(12);
	}
	
	// returns the map associated with the game
	public Tile[][] getMap()
	{
		return this.map;
	}
	
	// returns the textField associated with the game
	public TextField getOutputText()
	{
		return this.outputText;
	}
	
	// returns the textArea associated with the game
	public TextArea getGameText()
	{
		return this.gameText;
	}
	
	// returns the tile that the hunter is currently standing on
	public Tile getTempTile()
	{
		return this.tempTile;
	}
	
	// returns the hunter
	public Hunter getHunter()
	{
		return this.hunter;
	}
	
	// initializes the map with every tile necessary to play the game
	public void init()
	{
		over = false;
		hasShot = false;
		
		outputText.setText("You have entered the cave... can you find the Wumpus?");
		
		// get new tiles for the map...
		for (int r = 0; r < 12; r++)
		{
			for (int c = 0; c < 12; c++)
			{
				map[r][c] = new Tile();
			}
		}
		
		tempTile = new Tile();
		
		wumpus = new Wumpus();
			
		wumpus.setColumn(genRandomCoord());
		wumpus.setRow(genRandomCoord());
		
		wumpus.spawn(map);
		
		// place the slime pits...
		Random rand = new Random();
		
		int numPits = rand.nextInt(3)+3;
		
		int tempRow = genRandomCoord(), tempCol = genRandomCoord();
		
		for (int i = 0; i < numPits; i++)
		{
			while (map[tempRow][tempCol].getText() == "W" || map[tempRow][tempCol].getText() == "P")
			{
				tempRow = genRandomCoord();
				tempCol = genRandomCoord();
			}
			
			map[tempRow][tempCol] = new Pit();
			
			// now place slime...
			
			// slime directly north of pit
			if (tempRow-1 < 0)
			{
				if (map[11][tempCol].getText() != "W" && map[11][tempCol].getText() != "P")
				{
					if (map[11][tempCol].getText() == "B")
						map[11][tempCol] = new Goop();
					else
						map[11][tempCol] = new Slime();
				}
			}else
			{
				if (map[tempRow-1][tempCol].getText() != "W" && map[tempRow-1][tempCol].getText() != "P")
				{
					if (map[tempRow-1][tempCol].getText() == "B")
						map[tempRow-1][tempCol] = new Goop();
					else
						map[tempRow-1][tempCol] = new Slime();
				}
			}
			
			// slime directly south of pit
			if (tempRow+1 > 11)
			{
				if (map[0][tempCol].getText() != "W" && map[0][tempCol].getText() != "P")
				{
					if (map[0][tempCol].getText() == "B")
						map[0][tempCol] = new Goop();
					else
						map[0][tempCol] = new Slime();
				}
			}else
			{
				if (map[tempRow+1][tempCol].getText() != "W" && map[tempRow+1][tempCol].getText() != "P")
				{
					if (map[tempRow+1][tempCol].getText() == "B")
						map[tempRow+1][tempCol] = new Goop();
					else
						map[tempRow+1][tempCol] = new Slime();
				}
			}
			
			//  slime directly west of pit
			if (tempCol-1 < 0)
			{
				if (map[tempRow][11].getText() != "W" && map[tempRow][11].getText() != "P")
				{
					if (map[tempRow][11].getText() == "B")
						map[tempRow][11] = new Goop();
					else
						map[tempRow][11] = new Slime();
				}
			}else
			{
				if (map[tempRow][tempCol-1].getText() != "W" && map[tempRow][tempCol-1].getText() != "P")
				{
					if (map[tempRow][tempCol-1].getText() == "B")
						map[tempRow][tempCol-1] = new Goop();
					else
						map[tempRow][tempCol-1] = new Slime();
				}
			}
			
			//  slime directly east of pit
			if (tempCol+1 > 11)
			{
				if (map[tempRow][0].getText() != "W" && map[tempRow][0].getText() != "P")
				{
					if (map[tempRow][0].getText() == "B")
						map[tempRow][0] = new Goop();
					else
						map[tempRow][0] = new Slime();
				}
			}else
			{
				if (map[tempRow][tempCol+1].getText() != "W" && map[tempRow][tempCol+1].getText() != "P")
				{
					if (map[tempRow][tempCol+1].getText() == "B")
						map[tempRow][tempCol+1] = new Goop();
					else
						map[tempRow][tempCol+1] = new Slime();
				}
			}
		}
			
		// now place the hunter...
		hunter = new Hunter(genRandomCoord(), genRandomCoord());
		
		while (map[hunter.getRow()][hunter.getColumn()].getText() != " ")
		{
			hunter.setRow(genRandomCoord());
			hunter.setColumn(genRandomCoord());
		}
		
		hunter.spawn(map);
		
		for (int r = 0; r < 12; r++)
		{
			for (int c = 0; c < 12; c++)
			{
				map[r][c].setFound(false);
			}
		}
		
		setChanged();
		notifyObservers();
	}
	
	// determines what happens when the hunter shoots their arrow, handles winning and losing
	public void shoot(int dir)
	{
		/*
		 *  1 : up or down
		 *  2 : right or left
		 */
		
		hasShot = true;
		
		int row = hunter.getRow();
		int col = hunter.getColumn();
		
		if (!over)
		{
			if (dir == 1) // up or down
			{		
				if (col == wumpus.getColumn())
				{
					outputText.setText(getWinMessage());
					gameOver();
				}else
				{
					outputText.setText(getLossMessage());
					gameOver();
				}
			}else // right or left
			{
				if (row == wumpus.getRow())
				{
					outputText.setText(getWinMessage());
					gameOver();
				}else
				{
					outputText.setText(getLossMessage());
					gameOver();
				}
			}
			
			setChanged();
		    notifyObservers();
		}
	}

	// moves the hunter tile in the Tile array
	public void moveHunter(Tile[][] map, int dir)
	{
		/*
		 *  1 : up
		 *  2 : right 
		 * -1 : down
		 * -2 : left
		 */
		
		if (!over)
		{
			if (tempTile != null)
			{
				map[hunter.getRow()][hunter.getColumn()] = tempTile;
			}else
			{
				map[hunter.getRow()][hunter.getColumn()] = new Tile();
				map[hunter.getRow()][hunter.getColumn()].setFound(true);
			}
		
			if (dir == 1) // up
			{
				if (hunter.getRow()-1 < 0)
				{
					hunter.setRow(11);
				}else
				{
					hunter.setRow(hunter.getRow()-1);
				}
			}else if (dir == 2) // right
			{
				if (hunter.getColumn()+1 > 11)
				{
					hunter.setColumn(0);
				}else
				{
					hunter.setColumn(hunter.getColumn()+1);
				}
			}else if (dir == -1) // down
			{
				if (hunter.getRow()+1 > 11)
				{
					hunter.setRow(0);
				}else
				{
					hunter.setRow(hunter.getRow()+1);
				}
			}else // left
			{
				if (hunter.getColumn()-1 < 0)
				{
					hunter.setColumn(11);
				}else
				{
					hunter.setColumn(hunter.getColumn()-1);
				}
			}
		
			tempTile = map[hunter.getRow()][hunter.getColumn()];
			tempTile.setFound(true);
			
			gameUpdate(tempTile);
		
			map[hunter.getRow()][hunter.getColumn()] = hunter;
			
			setChanged();
		    notifyObservers();
		}
	}
	
	// a "toString()" of the game... idk why I didn't just name this ""toString()"...
	public String getGame()
	{
		String result = "";
		
		for (int r = 0; r < 12; r++)
		{
			for (int c = 0; c < 12; c++)
			{
				result += " " + map[r][c].getText() + " ";
			}
			
			result += "\n";
		}
		
		return result;
	}
	
	// checks to see what tile the hunter has stepped on, updates status of game and outputText accordingly
	public void gameUpdate(Tile tempTile)
	{
		if (!hasShot)
		{
			if (tempTile.getText() == " ")
			{
				outputText.setText(getEmptyMessage());
			}else if (tempTile.getText() == "B")
			{
				outputText.setText(getBloodMessage());
			}else if (tempTile.getText() == "S")
			{
				outputText.setText(getSlimeMessage());
			}else if (tempTile.getText() == "G")
			{
				outputText.setText(getGoopMessage());
			}else if (tempTile.getText() == "W")
			{
				outputText.setText(getWumpusMessage());
				gameOver();
			}else if (tempTile.getText() == "P")
			{
				outputText.setText(getPitMessage());
				gameOver();
			}
		}
		
		gameText.setText(getGame());
	}
	
	// reveals all hazards and warnings, sets over to true
	private void gameOver()
	{
		over = true;
		
		for (int row = 0; row < 12; row++)
		{
			for (int col = 0; col < 12; col++)
			{
				if (map[row][col].getFoundText() != " ")
				{
					map[row][col].setFound(true);
				}
			}
		}
	}
	
	/* ~~~~~ Below here are functions that contain an arraylist of strings which correspond   ~~~~~
	 * ~~~~~ to actions that the user has taken (stepping onto a different tile, winning      ~~~~~
	 * ~~~~~ the game, losing the game, etc. The only difference between them is the strings. ~~~~~
	 */
	public String getEmptyMessage()
	{
		ArrayList<String> strings = new ArrayList<String>();
		
		strings.add("You cautiously take a step... and enter an empty room.");
		strings.add("You pick a direction to walk... and enter an empty room.");
		strings.add("You gather your strength to push on... and enter an empty room.");
		strings.add("You walk on... and enter an empty room.");
		strings.add("The eerie sounds of the cave fill your ears as you walk... and enter an empty room.");
		strings.add("Your feet echo throughout the cave as you walk on... and enter an empty room.");
		
		Random rand = new Random();
		
		return strings.get(rand.nextInt(strings.size()));
	}
	
	public String getSlimeMessage()
	{
		ArrayList<String> strings = new ArrayList<String>();
		
		strings.add("You smell something foul and notice slime at your feet... a pit is nearby.");
		strings.add("A bright green substance shines in your torchlight... a pit is nearby.");
		strings.add("You boots start to get heavy; looking down you notice bits of slime... a pit is nearby.");
		
		Random rand = new Random();
		
		return strings.get(rand.nextInt(strings.size()));
	}
	
	public String getBloodMessage()
	{
		ArrayList<String> strings = new ArrayList<String>();
		
		strings.add("You notice all the blood... the Wumpus is nearby.");
		strings.add("You shine your torch, pools of blood lie everywhere... the Wumpus is nearby.");
		strings.add("Your boots are covered in blood... the Wumpus is nearby.");
		
		Random rand = new Random();
		
		return strings.get(rand.nextInt(strings.size()));
	}
	
	public String getGoopMessage()
	{
		ArrayList<String> strings = new ArrayList<String>();
		
		strings.add("You trudge through a horrid organge substance... the Wumpus and a pit are nearby.");
		strings.add("You reel back as an offensive odor fills your lungs... the Wumpus and a pit are nearby.");
		strings.add("Before you step, you notice all the blood and slime... the Wumpus and a pit are nearby.");
		
		Random rand = new Random();
		
		return strings.get(rand.nextInt(strings.size()));
	}
	
	public String getWumpusMessage()
	{
		ArrayList<String> strings = new ArrayList<String>();
		
		strings.add("Before you even realize it, the Wumpus is upon you... game over.");
		strings.add("You catch of glimpse of the Wumpus, but then it catches a glimpse of you... game over.");
		strings.add("You hear the Wumpus's loud roar; it's the last thing you ever hear... game over.");
		strings.add("You step into the Wumpus's den while the Wumpus is still there... game over.");
		
		Random rand = new Random();
		
		return strings.get(rand.nextInt(strings.size()));
	}
	
	public String getPitMessage()
	{
		ArrayList<String> strings = new ArrayList<String>();
		
		strings.add("You slip and fall into a slime pit. An untimely demise for such a bold hunter... game over.");
		strings.add("The ground beneath you crumbles away as you fall into a slime pit... game over.");
		strings.add("You lose your balance, and fall into a slime pit... game over.");
		
		Random rand = new Random();
		
		return strings.get(rand.nextInt(strings.size()));
	}
	
	public String getLossMessage()
	{
		ArrayList<String> strings = new ArrayList<String>();
		
		strings.add("You fire your arrow, but you miss the Wumpus... game over.");
		strings.add("You loose your arrow into the dark, but miss the Wumpus. It hits you instead... game over.");
		strings.add("You shoot at what appears to be the Wumpus, but your arrow lands in you... game over.");
		
		Random rand = new Random();
		
		return strings.get(rand.nextInt(strings.size()));
	}
	
	public String getWinMessage()
	{
		ArrayList<String> strings = new ArrayList<String>();
		
		strings.add("You fire your arrow, and hear the sounds of a dying Wumpus... you win!");
		strings.add("You bravely fire your arrow in the dark, and hear it connect with Wumpus flesh... you win!");
		strings.add("You calculate the trajectory and fire your arrow. It hits your target... you win!");
		
		Random rand = new Random();
		
		return strings.get(rand.nextInt(strings.size()));
	}
}