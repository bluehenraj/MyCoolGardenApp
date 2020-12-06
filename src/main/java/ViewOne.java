import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/***
 * 
 * @author Raj Trivedi, Noah Hodgson, Luis Figueroa
 *
 */
public class ViewOne {	
	FlowPane fp;
	Button uploadGardenImageButton;
	Scene scene;
	Stage stage;
	final static int WIDTH = 800;
	final static int HEIGHT = 600;
	final static int BUTTONX = 450;
	final static int BUTTONY = 300;
	final static int BUTTON_HEIGHT = 75;
	final static int BUTTON_WIDTH = 200;
	
	/***
	 * Initializes the instance variables, sets the Scene, and displays it on Stage
	 * @param stage the Stage to display for ViewOne
	 */
	public ViewOne(Stage stage){
		this.stage = stage;
		fp = new FlowPane();
    	BackgroundImage backgroundImage = new BackgroundImage(new Image("img/LoadImageBackground.jpg"),BackgroundRepeat.NO_REPEAT, 
    			BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
    	fp.setBackground(new Background(backgroundImage));
    	uploadGardenImageButton = new Button("Load Garden Background Image");
    	uploadGardenImageButton.setTranslateX(BUTTONX);
    	uploadGardenImageButton.setTranslateY(BUTTONY);
    	uploadGardenImageButton.setPrefHeight(BUTTON_HEIGHT);
    	uploadGardenImageButton.setPrefWidth(BUTTON_WIDTH);
    	fp.getChildren().add(uploadGardenImageButton);
    	scene = new Scene(fp,WIDTH,HEIGHT);
	}
	public void startShow() {
		stage.setScene(scene);
		stage.show();
	}
	/***
	 * Returns the most recent Scene for ViewOne
	 * @return the current Scene for ViewOne
	 */
	public Scene getScene() {
		return scene;
	}
}
