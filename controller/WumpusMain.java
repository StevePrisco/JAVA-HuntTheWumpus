package controller;

import java.util.Observer;

/*
 * File: WumpusMain.java
 * Author: Steven Prisco-Deglman
 * Description: This contains the base GUI elements for Hunt the Wumpus
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.HuntTheWumpusGame;
import views.ImageView;
import views.TextView;

public class WumpusMain extends Application
{
	private BorderPane window;
	private static final int width = 825;
	private static final int height = 700;
	private HuntTheWumpusGame theGame;
	private MenuBar menuBar;

	private Observer currentView;
	private Observer textView;
	private Observer imageView;
	
	// ~~~~ TESTING STUFF BELOW HERE ~~~~
	
	private static TextArea gameText = new TextArea();
	private static TextField outputText = new TextField();
	private static GridPane grid;
	private static Button buttonU;
	private static Button buttonD;
	private static Button buttonL;
	private static Button buttonR;
	
	// launches the window
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		window = new BorderPane();
		Scene scene = new Scene(window, width, height);
		
		theGame = new HuntTheWumpusGame(outputText, gameText);
		
		setupMenus();
	    window.setTop(menuBar);
	    
	    grid = new GridPane();
		
		buttonU = new Button("N");
			buttonU.setFont(Font.font("Consolas"));
		buttonD = new Button("S");
			buttonD.setFont(Font.font("Consolas"));
		buttonL = new Button("W");
			buttonL.setFont(Font.font("Consolas"));
		buttonR = new Button("E");
			buttonR.setFont(Font.font("Consolas"));
		
		ButtonListener handlerV = new ButtonListener();
		ButtonListener handlerH = new ButtonListener();
		
	    buttonU.setOnAction(handlerV);
	    buttonD.setOnAction(handlerV);
	    buttonL.setOnAction(handlerH);
	    buttonR.setOnAction(handlerH);

	    grid.setPadding(new Insets(10,10,10,10));
	    grid.setHgap(5);
	    grid.setVgap(5);

	    grid.add(buttonU, 1, 60);
	    grid.add(buttonL, 0, 61);
	    grid.add(buttonR, 2, 61);
	    grid.add(buttonD, 1, 62);
	    
	    textView = new TextView(theGame);
	    imageView = new ImageView(theGame);
	    
	    theGame.addObserver(textView);
	    theGame.addObserver(imageView);
		
		scene.setOnKeyReleased(e -> {
		    if (e.getCode() == KeyCode.LEFT)
		    {
		    	theGame.moveHunter(theGame.getMap(), -2);
//		        System.out.println("Left was pressed");
		    }else if (e.getCode() == KeyCode.RIGHT)
		    {
		    	theGame.moveHunter(theGame.getMap(), 2);
//		        System.out.println("Right was pressed");
		    }else if (e.getCode() == KeyCode.UP)
		    {
		    	theGame.moveHunter(theGame.getMap(), 1);
//		        System.out.println("Up was pressed");
		    }else if (e.getCode() == KeyCode.DOWN)
		    {
		    	theGame.moveHunter(theGame.getMap(), -1);
//		        System.out.println("Down was pressed");
		    } });
		
		window.setPadding(new Insets(10, 10, 10, 10));
		window.setRight(grid);
		window.setBottom(theGame.getOutputText());
		
		setViewTo(textView);
		primaryStage.setScene(scene);
		primaryStage.show();

/*
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Directions");
        alert.setHeaderText("Use your hunting skills to track down the Wumpus!");
        alert.setContentText("Directions:\n1. Use Arrow Keys to move around the map"
        		+ "\n2. Avoid deadly Hazards!\n3. Once you have located the Wumpus"
        		+ " use the buttons on the right to fire your arrow in the"
        		+ " direction you think the Wumpus is hiding!\n\nHazards:\n"
        		+ "   Wumpus: A deadly monster surrounded by Blood!\n"
        		+ "   Slime Pit: A bottomless pit surrounded by Slime! Walking into"
        		+ " it will\n   mean your death!\n\nRemember! Slime and Blood mix"
        		+ " together to form Goop! Goop means that both a pit and the"
        		+ " Wumpus are nearby!");
		
		alert.show();
*/
	}
	
	// initializes the menus
	private void setupMenus()
	{
	    MenuItem textual = new MenuItem("Textual");
	    MenuItem graphical = new MenuItem("Graphical");
	    Menu views = new Menu("Views");
	    views.getItems().addAll(textual, graphical);

	    MenuItem newGame = new MenuItem("New Game");
	    Menu options = new Menu("Options");
	    options.getItems().addAll(newGame, views);

	    menuBar = new MenuBar();
	    menuBar.getMenus().addAll(options);
	 
	    // Add the same listener to all menu items requiring action
	    MenuItemListener menuListener = new MenuItemListener();
	    newGame.setOnAction(menuListener);
	    textual.setOnAction(menuListener);
	    graphical.setOnAction(menuListener);
	}
	
	// sets current view
	private void setViewTo(Observer newView)
	{
		window.setCenter(null);
	    currentView = newView;
	    window.setCenter((Node) currentView);
	}
	
	// listener and handler for the menu options
	private class MenuItemListener implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e)
		{
			// Find out the text of the JMenuItem that was just clicked
			String text = ((MenuItem) e.getSource()).getText();

			if (text.equals("New Game"))
				theGame.init();
			else if (text.equals("Textual"))
				setViewTo(textView);
			else if (text.equals("Graphical"))
				setViewTo(imageView);
		}
	}
	
	// listener and handler for the button
	private class ButtonListener implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			String buttonText = ((Button) event.getSource()).getText();
			
			if (buttonText == "W" || buttonText == "E")
				theGame.shoot(2);
			else
				theGame.shoot(1);
		}	
	}
}
