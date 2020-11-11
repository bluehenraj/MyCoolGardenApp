import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Model implements java.io.Serializable { 
	static final int NAMESPOT = 0;
	static final int SUNSPOT = 1;
	static final int SOILSPOT = 2;
	Garden garden;
	ArrayList<Plant> trashBin;
	List<Plant> hotBarPlants;
	
	Model() throws IOException{
		this.garden = new Garden();
		this.trashBin = new ArrayList<>();
		this.hotBarPlants = getPlantsListFromFile("plants.txt");
	}
	
	public ArrayList<Plant> getPlantsListFromFile(String filename) throws IOException {
		List<Integer> lineNumbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
		ArrayList<Plant> plantsList = new ArrayList<>();
		ListIterator<Integer> itr = lineNumbers.listIterator();
		while(itr.hasNext()) {
			int index = itr.next();
			String currLine = Files.readAllLines(Paths.get(filename)).get(index-1);
			String[] parts = currLine.split("-");
			plantsList.add(new Plant(parts[NAMESPOT],0,index-1,parts[SUNSPOT],parts[SOILSPOT]));
		}
		return plantsList;
	}
	
	public void sortFlowersByCriteria(int criteria) {
		if(criteria == NAMESPOT)
			sortFlowersByName(hotBarPlants);
		else if(criteria == SUNSPOT)
			sortFlowersByLight(hotBarPlants);
		else if(criteria == SOILSPOT)
			sortFlowersBySoil(hotBarPlants);
		else
			System.out.println("Invalid criteria to sort flowers...");
	}
	
	public void sortFlowersByName(List<Plant> flowers) {
		Collections.sort(flowers,new Comparator<Plant>(){
			@Override
			public int compare(Plant p1, Plant p2) {
				return p1.name.compareTo(p2.name);
			}
			
		});
		
	}
	
	public void sortFlowersByLight(List<Plant> flowers) {
		Collections.sort(flowers,new Comparator<Plant>(){
			@Override
			public int compare(Plant p1, Plant p2) {
				return p1.plantLight.compareTo(p2.plantLight);
			}
			
		});
	}
	public void sortFlowersBySoil(List<Plant> flowers) {
		Collections.sort(flowers,new Comparator<Plant>(){
			@Override
			public int compare(Plant p1, Plant p2) {
				return p1.plantSoil.compareTo(p2.plantSoil);
			}
			
		});
	}

	public Plant selectPlant(double x, double y) {
		Plant tba = new Plant("if you are seeing this you done goofed", 99, 99); // tba == to be added
		for(Plant p : hotBarPlants) {
			if(p.yLoc==y) {
				tba=p;
			}
		}
		return tba;
	}
	
	public void addToGarden(double gX, double gY, Plant tba) {
		garden.addPlant(tba, gX, gY);
		//add more info as we work more with the new View
	}
	
	public Plant selectPlantInGarden(int x, int y) {
		Plant tbm = new Plant("no plant", 99, 99);
		for(Plant p : garden.gardensPlants) {
			if(p.xLoc == x && p.yLoc == y) {
				tbm=p;
			}
		}
		return tbm;
	}
	
	public void moveInGarden(double gX, double gY, Plant tbm) {
		tbm.updatePlantLocation(gX, gY);
		//add more info as we work more with the new View
	}
	
	public void handleDeletionInGarden(double x, double y, Plant tbm) {
		garden.deletePlant(tbm);
		trashBin.add(tbm);
		//add more info as we work more with the new View
		System.out.println("Deletion successful");
	}
	
	/*
	public void handleAddEdit(Scanner input, int x, int y) {
		Plant tba = handleBarSelect(x, y);
		boolean inside = true;
		while(inside) {
			System.out.println("Choose an x in your garden: ");
			int gX = input.nextInt();
			System.out.println("Good, now choose a y in your garden: ");
			int gY = input.nextInt();
			if(gX>=xMinWorkSpace && gX<=xMaxWorkSpace && gY >=yMinWorkSpace && gY <= yMaxWorkSpace) {
				handleAddToGarden(gX, gY, tba);
				inside=false;
			}
			else {
				System.out.println("Something went wrong. Make sure your x is between 1 and 9 and your y is between 0 and 14");
			}
		}
	}*/
	/*
	public void handleMoveEdit(Scanner input, int x, int y) {
		Plant tbm = handleSelectingPlantInGarden(x,y);
		if(tbm.name.equals("no plant")) {
			System.out.println("You select an area with no plant.");
		}
		else {
			//start of while loop
			boolean inside=true;
			while(inside) {
				System.out.println("Select the xLocation you want this plant to go");
				int gX = input.nextInt();
				System.out.println("Selecy the y location you want this plant to go");
				int gY = input.nextInt();
				if(gX >= xMinWorkSpace && gX<=xMaxWorkSpace && gY >= yMinWorkSpace && gY <= yMaxWorkSpace) {
					handleMoveInGarden(x,y,gX,gY,tbm);
					inside = false;
				}
				else if(gX == xWasteBasket && gY == yWasteBasket) {
					handleDeletionInGarden(x,y,tbm);
					inside = false;
				}
				else {
					System.out.println("The space you selected is not valid. Try again");
				}
			}
		}
	}*/
	
	public void handleShowTrash() {
		System.out.println("You selected the trashbin. Here's what's inside: ");
		for(Plant p : trashBin) {
			System.out.print(p.name+" ");
		}
		System.out.println("");
	}
	/*
	public void handleEdit(Scanner input) {
		System.out.println("\nInput an x coordinate");
		int x = input.nextInt();
		System.out.println("Now input a y coordinate (NOTE: y is going down instead of up)");
		int y = input.nextInt();
		//selecting space on the hotbar
		if(x >= xMinLeftBar && x <= xMaxLeftBar && y >= yMinLeftBar && y <= yMaxLeftBar) {
			handleAddEdit(input, x, y);
		}
		//selecting space in the garden
		else if(x>=xMinWorkSpace && x <= xMaxWorkSpace && y >= yMinWorkSpace && y <= yMaxWorkSpace) {
			handleMoveEdit(input, x, y);
		}
		//selecting trash bin
		else if(x==xWasteBasket && y==yWasteBasket) {
			handleShowTrash();
		}
		else {
			System.out.println("Nice try, but the value you inputed was not within our 15 by 10 grid.");
		}
	}
	
	public void handleFact(Scanner input) {
		System.out.println("Choose the y location of the plant in the left bar that you want to learn about");
		int yLoc = input.nextInt();
		for(Plant p : hotBarPlants) {
			if(p.yLoc==yLoc) {
				System.out.println("You have selected: " + p.name + ". It needs "+ p.plantLight +" and "+ p.plantSoil+".");
			}
		}
	}
	
	public boolean handleExit(Scanner input) {
		System.out.println("Exit program?(yes/no)");
		String exit = input.next();
		if(exit.equals("yes")) {
			return false;
		}
		else {
			return true;
		}
	}
	*/
}