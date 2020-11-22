package Application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ViewTwo {
	ModelTwo model = new ModelTwo();
	ArrayList<PlantImageView> sideView = new ArrayList<PlantImageView>();;
	ArrayList<PlantImageView> plantsInGarden = new ArrayList<PlantImageView>();	
	TilePane tp;
	GridPane gp;
	FlowPane fp;
	BorderPane bp;
	ScrollPane sp;
	Menu viewMenu;
	Menu fileMenu;
	Menu undoMenu;
	Menu redoMenu;
	MenuItem undoItem;
	MenuItem redoItem;
	MenuBar menuBar;
	MenuButton sortBy;
	Button nameButton;
	Button soilButton;
	Button sunButton;
	HBox hbox;
	VBox vbox;
	Scene scene;
	ArrayList<Plant> plants;
	Image undoImage = new Image("img/undoImage.png");
	Image redoImage = new Image("img/redoImage.png");
	ImageView undoImgView;
	ImageView redoImgView;
	ControllerTwo controllerTwo;
	final static int WIDTH = 800;
	final static int HEIGHT = 600;

	public void buttonMaker(GridPane grid) {		
    	sortBy = new MenuButton("Sort by");
    	
    	nameButton = new Button("Name");
		CustomMenuItem nameItem = new CustomMenuItem(nameButton);
		sortBy.getItems().add(nameItem);
		nameItem.setHideOnClick(false);
    	
		soilButton = new Button("Soil");
		CustomMenuItem soilItem = new CustomMenuItem(soilButton);
		sortBy.getItems().add(soilItem);
		soilItem.setHideOnClick(false);
		
		sunButton = new Button("Sun");
		CustomMenuItem sunItem = new CustomMenuItem(sunButton);
		sortBy.getItems().add(sunItem);
		sunItem.setHideOnClick(false);
		
		sortBy.setOnMouseClicked(e->controllerTwo.sortButtonHandler(this));
		grid.getChildren().add(sortBy);
	}
	
	public void topMenuMaker() {
		// Undo and Redo Image View
		undoImgView = new ImageView(undoImage);
		redoImgView = new ImageView(redoImage);
		undoImgView.setPreserveRatio(true);
		redoImgView.setPreserveRatio(true);
		undoImgView.setFitWidth(100);
		undoImgView.setFitHeight(20);
		redoImgView.setFitWidth(100);
		redoImgView.setFitHeight(20);

		// Creates items to be added for a Menu
		undoItem = new MenuItem("Undo Item");
		redoItem = new MenuItem("Redo Item");
		 
		fileMenu = new Menu("File");
		viewMenu = new Menu("View");
		undoMenu = new Menu("",undoImgView);
		redoMenu = new Menu("",redoImgView);
			
		// Adds items for specific Menu
		undoMenu.getItems().add(undoItem);
	    redoMenu.getItems().add(redoItem);
		
		MenuBar topBar = new MenuBar();
		
		topBar.getMenus().add(fileMenu);
		topBar.getMenus().add(viewMenu);
		topBar.getMenus().add(undoMenu);
		topBar.getMenus().add(redoMenu);
		vbox = new VBox(topBar);
	}
	
	public void plantIVAdder(ArrayList<Plant> plants) {
		int i=0;
		for(Plant p : plants) {
			Image im1 = new Image("img/"+i+".png");
			PlantImageView piv = new PlantImageView(p);
			piv.setImage(im1); //write function to change to a plant later
	    	piv.setPreserveRatio(true);
	    	piv.setFitHeight(100);
	    	Tooltip tooltip =  new Tooltip("This is "+p.name+".\n"+"It needs "+p.plantLight+" and "+p.plantSoil+".");
	    	Tooltip.install(piv, tooltip);
	    	piv.setPaneLoc("grid");
			sideView.add(piv);
	    	gp.add(piv, 0, i+1);
	    	i++;
	    }
	}
	//sort by name type
	public void nameSort() {
		Collections.sort(sideView,new Comparator<PlantImageView>(){
			@Override
			public int compare(PlantImageView p1, PlantImageView p2) {
				return p1.plant.name.compareTo(p2.plant.name);
			}
		});
	}
	//sort by sun type
	public void sunSort() {
		Collections.sort(sideView,new Comparator<PlantImageView>(){
			@Override
			public int compare(PlantImageView p1, PlantImageView p2) {
				int i = p1.plant.plantLight.compareTo(p2.plant.plantLight);
				if(i==0) {
					return p1.plant.name.compareTo(p2.plant.name);
				}
				else {
					return i;
				}
			}
		});
	}
	//sort by soil type
	public void soilSort() {
		Collections.sort(sideView,new Comparator<PlantImageView>(){
			@Override
			public int compare(PlantImageView p1, PlantImageView p2) {
				int i = p1.plant.plantSoil.compareTo(p2.plant.plantSoil);
				if(i==0) {
					return p1.plant.name.compareTo(p2.plant.name);
				}
				else {
					return i;
				}
			}				
		});
	}
	
	//uses sortmode to sort the sideview plant image views ArrayList
	public void sideViewSortHelper(String sortMode) { // convert to enum in the future
		if (sortMode.equals("name")) {
			nameSort();
		}
		else if (sortMode.equals("sun")) {
			sunSort();
		}
		else if (sortMode.equals("soil")) {
			soilSort();
		}
	}
	
	//sorts the ImageViews in the sideBar based on the sort mode
	public void sortSideView(String sortMode) {
		for(PlantImageView p: sideView) {
			gp.getChildren().remove(p);
		}
		sideViewSortHelper(sortMode);
		GridPane sortedGrid = new GridPane();
    	sortedGrid.setMaxWidth(1);
    	sortedGrid.setStyle("-fx-background-color: #ADD8E6");
		buttonMaker(sortedGrid);
		int i = 0;
    	for(PlantImageView p: sideView) {
    		gp.add(p,0,i+1);
    		i++;
    	}
	}
	
	
	/**
	 * Simple constructor that sets initial imageview and controller.
	 */

	public ViewTwo(Stage stage){
		controllerTwo = new ControllerTwo();
		plants = model.getHotBarPlants();
		topMenuMaker();
    	gp = new GridPane();
    	gp.setMaxWidth(1);
    	gp.setStyle("-fx-background-color: #ADD8E6");
		buttonMaker(gp);
		plantIVAdder(plants);
    	hbox = new HBox();
    	hbox.getChildren().add(gp);
    	sp = new ScrollPane();
    	sp.setFitToWidth(true);
    	sp.setContent(hbox);
    	fp = new FlowPane();
    	fp.setStyle("-fx-background-color: #BFFF00");
    	fp.getChildren().add(vbox);
    	bp = new BorderPane();
    	bp.setTop(vbox);
    	bp.setCenter(fp);
    	bp.setLeft(sp);
    	scene = new Scene(bp,WIDTH,HEIGHT);
    	stage.setScene(scene);
    	stage.show();
	}



	public ViewTwo() {
	}

	public Parent getBP() {
		return this.bp;
	}



	public TilePane getTP() {
		return this.tp;
	}
	
	public Scene getScene() {
		return scene;
	}
}