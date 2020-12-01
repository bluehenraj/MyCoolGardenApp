import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlantImageView extends ImageView{
	String paneLocation;
	Plant plant;
	
	/***
	 * Initializes the instance of Plant
	 * @param plant the Plant to set
	 */
	public PlantImageView(Plant plant){
		super();
		this.setPickOnBounds(true);
		this.plant = plant;
	}
	
	/***
	 * Calls parent class constructor and initializes the parameter value
	 * @param im the Image to be set
	 */
	public PlantImageView(Image im){
		super(im);
		this.setPickOnBounds(true);
	}
	
	/***
	 * Sets the location of Pane with the given location value
	 * @param s the location to set
	 */
	public void setPaneLoc(String s) {
		this.paneLocation = s;
	}
	
	/***
	 * Returns the most recent location of Pane
	 * @return the current instance of Pane location
	 */
	public String getPaneLoc() {
		return this.paneLocation;
	}
}
