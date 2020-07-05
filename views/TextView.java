package views;

/*
 * File: TextView.java
 * Author: Steve Prisco
 * Description: Describes the textual representation of Hunt the Wumpus
 */

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.HuntTheWumpusGame;

public class TextView extends BorderPane implements Observer 
{
	private HuntTheWumpusGame theGame;
	static GridPane grid;
	static Button buttonU;
	static Button buttonD;
	static Button buttonL;
	static Button buttonR;
	static BorderPane window;
	
	public TextView(HuntTheWumpusGame huntTheWumpus)
	{
		theGame = huntTheWumpus;
	    initializePane();
	}

	private void initializePane()
	{
	
		theGame.getGameText().setDisable(true);
		theGame.getGameText().setPadding(new Insets(10,10,10,10));

		setCenter(theGame.getGameText());
		
		theGame.getGameText().setFont(Font.font("Consolas", 32));
		theGame.getGameText().setStyle("-fx-text-fill: black ; -fx-opacity: 1.0;");
		
		theGame.getGameText().setText(theGame.getGame());
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		theGame.getGameText().setText(theGame.getGame());
		theGame.gameUpdate(theGame.getTempTile());
	}
}
