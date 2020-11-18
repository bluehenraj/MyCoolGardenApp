package GardenMenus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneOne {	
	Menu fileMenu;
	Menu viewMenu;
	Menu undoMenu;
	Menu redoMenu;
	MenuItem fileMenuItem1;
	MenuItem fileMenuItem2;
	MenuItem fileMenuItem3;
	MenuItem viewMenuItem1;
	MenuItem viewMenuItem2;
	MenuItem viewMenuItem3;
	MenuItem undoItem;
	MenuItem redoItem;
	MenuBar menuBar;
	FlowPane fp;
	Button uploadGardenImageButton;
	Scene scene;
	VBox vBox;
	Image undoImage = new Image("img/undoImage.png");
	Image redoImage = new Image("img/redoImage.png");
	ImageView undoImgView;
	ImageView redoImgView;
	final static int WIDTH = 800;
	final static int HEIGHT = 600;
	final static int MENU_BAR_WIDTH = 1380;
	final static int MENU_BAR_HEIGHT = 32;
	public SceneOne(Stage stage){
		// Undo and Redo Image View
		undoImgView = new ImageView(undoImage);
		redoImgView = new ImageView(redoImage);
		undoImgView.setPreserveRatio(true);
		redoImgView.setPreserveRatio(true);
		undoImgView.setFitWidth(100);
		undoImgView.setFitHeight(20);
		redoImgView.setFitWidth(100);
		redoImgView.setFitHeight(20);
				
		fp = new FlowPane();
				
		// Creates items to be added for a Menu
		fileMenuItem1 = new MenuItem("File Menu Item 1");
		fileMenuItem2 = new MenuItem("File Menu Item 2");
		fileMenuItem3 = new MenuItem("File Menu Item 3");
		viewMenuItem1 = new MenuItem("View Menu Item 1");
		viewMenuItem2 = new MenuItem("View Menu Item 2");
		viewMenuItem3 = new MenuItem("View Menu Item 3");
		undoItem = new MenuItem("Undo Item");
		redoItem = new MenuItem("Redo Item");
				
		// Creates a Menu 
		fileMenu = new Menu("File");
    	viewMenu = new Menu("View");
		undoMenu = new Menu("",undoImgView);
		redoMenu = new Menu("",redoImgView);
				
		// Adds items for specific Menu
		fileMenu.getItems().add(fileMenuItem1);
		fileMenu.getItems().add(fileMenuItem2);
		fileMenu.getItems().add(fileMenuItem3);
		viewMenu.getItems().add(viewMenuItem1);
		viewMenu.getItems().add(viewMenuItem2);
		viewMenu.getItems().add(viewMenuItem3);
		undoMenu.getItems().add(undoItem);
		redoMenu.getItems().add(redoItem);
				
		// Creates a Menu Bar
		menuBar = new MenuBar();
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(viewMenu);
		menuBar.getMenus().add(undoMenu);
		menuBar.getMenus().add(redoMenu);
				
		
		menuBar.setPrefWidth(MENU_BAR_WIDTH);
		menuBar.setPrefHeight(MENU_BAR_HEIGHT);
		
		// Creates a VBox
		vBox = new VBox(menuBar);
		vBox.setTranslateY(-75);
		
    	fp.setStyle("-fx-background-color: #BFFF00");
    	uploadGardenImageButton = new Button("Upload Garden Image");
    	uploadGardenImageButton.setTranslateX(450);
    	uploadGardenImageButton.setTranslateY(300);
    	uploadGardenImageButton.setPrefHeight(75);
    	uploadGardenImageButton.setPrefWidth(200);
    	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	stage.setScene(new SceneContainer(stage).getSceneMap().get(SceneName.SCENE2));
            } 
        };
        uploadGardenImageButton.setOnAction(event);
    	fp.getChildren().add(uploadGardenImageButton);
    	fp.getChildren().add(vBox);
    	
    	scene = new Scene(fp,WIDTH,HEIGHT);
    	stage.setScene(scene);
    	stage.show();
	}
	public Scene getScene() {
		return scene;
	}
}
