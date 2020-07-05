package tests;

/*
 * THIS IS INCOMPLETE
 * I ran into a some errors and was unable to test my code properly...
 * :(
 */

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.HuntTheWumpusGame;
import model.Hunter;
import model.Tile;
import model.Wumpus;

public class WumpusTest {

  @Test
  public void testHunter()
  {
	  Hunter hunter = new Hunter();
	  hunter.setRow(2);
	  assertEquals(hunter.getRow(), 2);
	  
	  hunter.setColumn(2);
	  assertEquals(hunter.getColumn(), 2);
	  
	  hunter.setFound(false);
	  assertEquals(hunter.getFound(), false);
	  
	  hunter.getText();
	  hunter.getFoundText();
	  
	  Tile[][] map = new Tile[12][12];
	  hunter.spawn(map);
	  
	  hunter = new Hunter(3,3);
  }
  
  @Test
  public void testWumpus()
  {
	  Wumpus Wumpus = new Wumpus();
	  Wumpus.setRow(2);
	  assertEquals(Wumpus.getRow(), 2);
	  
	  Wumpus.setColumn(2);
	  assertEquals(Wumpus.getColumn(), 2);
	  
	  Wumpus.setFound(false);
	  assertEquals(Wumpus.getFound(), false);
	  
	  Wumpus.getText();
	  Wumpus.getFoundText();
	  
	  Tile[][] map = new Tile[12][12];
	  Wumpus.spawn(map);
	  
	  Wumpus = new Wumpus(5,5);
	  map = new Tile[12][12];
	  Wumpus.spawn(map);
	  
	  Wumpus = new Wumpus(0,0);
	  map = new Tile[12][12];
	  Wumpus.spawn(map);
	  
	  Wumpus = new Wumpus(11,11);
	  map = new Tile[12][12];
	  Wumpus.spawn(map);
	  
	  Wumpus = new Wumpus(10,10);
	  map = new Tile[12][12];
	  Wumpus.spawn(map);
	  
	  Wumpus = new Wumpus(1,1);
	  map = new Tile[12][12];
	  Wumpus.spawn(map);
  }
}
