package mainLocator;
import java.util.Scanner;

public class EventLocator {
	
	public static void main(String[] args){
		
		//Generate new world called viagogo with X coordinates and Y coordinates between -10 and 10
		World viagogo = new World();
		
		viagogo.seedWorld();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please Input X Coordinate: ");
		
		int xcoord = scan.nextInt();
		
		//Error handling for case where user inputs x coordinate outside the defined world
		if(xcoord < viagogo.getXmin() || xcoord > viagogo.getXmax()){
			
			while(xcoord < viagogo.getXmin() || xcoord > viagogo.getXmax()){
				System.out.println("Please re-enter X Coordinate value between "+viagogo.getXmin()+" and "+viagogo.getXmax()+": ");
				xcoord = scan.nextInt();
			}
		}

		System.out.println("Please Input Y Coordinate: ");
		
		int ycoord = scan.nextInt();

		//Error handling for case where user inputs x coordinate outside the defined world
		if(ycoord < viagogo.getYmin() || ycoord > viagogo.getYmax()){
			
			while(ycoord < viagogo.getYmin() || ycoord > viagogo.getYmax()){
				System.out.println("Please re-enter Y Coordinate value between "+viagogo.getYmin()+" and "+viagogo.getYmax()+": ");
				ycoord = scan.nextInt();
			}
		}
		
		//Executes the program, locates nearest 5 events and prints to the console
		viagogo.locateEvents(xcoord, ycoord);
		
		scan.close();
	}
	
}

