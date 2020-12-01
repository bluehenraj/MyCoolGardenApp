import java.io.File;
import java.util.*;


public class Garden implements java.io.Serializable{
	ArrayList<Plant> gardensPlants = new ArrayList<>();
	String gardenLight;
	String gardenWater;
	String gardenSoil;
	File bg;
	
	public void addPlant(Plant p, double x, double y) {
		this.gardensPlants.add(new Plant(p.name, x, y, p.plantLight, p.plantSoil, p.plantSize));
	}

	public File getBg() {
		return bg;
	}

	public void setBg(File bg) {
		this.bg = bg;
	}

	public void deletePlant(Plant p) {
		gardensPlants.remove(p);
	}
	
	public int tallyGardenScore() {
		return 0;
	}
	
	public String writeGardenScore() {
		return "";
	}
	
	public String sumGarden() {
		return "";
	}
	
	public void setGardensPlants(ArrayList<Plant> plants) {
		this.gardensPlants = plants;
	}
}