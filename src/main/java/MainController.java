import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {

	ViewOne viewOne;
	Stage stage;
	FileChooser fileChooser;
	File fileToLoad;
	ViewTwo viewTwo;
	public MainController(Stage stage) {
		this.stage = stage;
		fileChooser = new FileChooser();
		viewOne = new ViewOne(stage);
	}
	public void handleLoadGardenButton(MouseEvent e) {
		fileChooser.setTitle("Load Your Garden");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized File(*.ser)", "*.ser"));
		fileToLoad = fileChooser.showOpenDialog(stage);
		Garden userSavedGarden = deserializeGarden(fileToLoad);
		viewTwo = new ViewTwo(stage, userSavedGarden);
	}

	public void handleNewGardenButtonPress(MouseEvent e) {
		stage.setScene(viewOne.getScene());
	}
	
	public Garden deserializeGarden(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Garden garden = (Garden)ois.readObject();
			ois.close();
			return garden;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}