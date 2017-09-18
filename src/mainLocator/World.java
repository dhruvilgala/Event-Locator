package mainLocator;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Comparator;

public class World {

	private int Xmin, Xmax, Ymin, Ymax, minEvents, maxEvents, maxTickets, numNearbyEvents;
	
	private double maxPrice, minPrice;
	
	public boolean[][] flag;
	
	public Event[] events;
	
	//Non-custom Constructor with values based on program requirements
	public World(){
		Xmin = -10;
		Xmax = 10;
		Ymin = -10;
		Ymax = 10;
		minEvents = 50;
		maxEvents = 100;
		maxTickets = 50;
		minPrice = 20.0;
		maxPrice = 200.0;
		numNearbyEvents = 5;
	}

	//Constructor where user can set the min and max coordinates for the world, 
	//the max number of events and the k nearest events to select
	public World(int xmin, int xmax, int ymin, int ymax, int minevents, int maxevents, int k){
		Xmin = xmin;
		Xmax = xmax;
		Ymin = ymin;
		Ymax = ymax;
		minEvents = minevents;
		maxEvents = maxevents;
		maxTickets = 50;
		minPrice = 20.0;
		maxPrice = 200.0;
		numNearbyEvents = k;
	}
	
	//generates random integer value between min and max values inclusive of both
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	//generates random double value between min and max
	public static double randDouble(double min, double max) {
		double random = ThreadLocalRandom.current().nextDouble(min, max);
		return random;
	}
	
	//creates a 2D boolean array representing the size of the world and initializes everything to false
	public void initializeFlag(){
		
		flag = new boolean[Math.abs(Xmin)+Math.abs(Xmax)+1][Math.abs(Ymin)+Math.abs(Ymax)+1];
		
		for(boolean[] b: flag){
			Arrays.fill(b, Boolean.FALSE);
		}
	}

	public void setXmax(int x){
		Xmax = x;
	}
	
	public void setXmin(int x){
		Xmin = x;
	}
	
	public void setYmax(int y){
		Ymax = y;
	}
	
	public void setYmin(int y){
		Ymin = y;
	}

	public int getXmax(){
		return Xmax;
	}
	
	public int getXmin(){
		return Xmin;
	}
	
	public int getYmax(){
		return Ymax;
	}
	
	public int getYmin(){
		return Ymin;
	}

	public int getMinEvents(){
		return minEvents;
	}
	
	public int getMaxEvents(){
		return maxEvents;
	}

	public int getMaxTicket(){
		return maxTickets;
	}

	public double getMaxTicketPrice(){
		return maxPrice;
	}
	
	public double getMinTicketPrice(){
		return minPrice;
	}

	public int getK(){
		return numNearbyEvents;
	}
	
	public void SetKNearestEvents(int numevents){
		numNearbyEvents = numevents;
	}
	
	public void setMinEvents(int minevents){
		minEvents = minevents;
	}
	
	public void setMaxEvents(int maxevents){
		maxEvents = maxevents;
	}

	public void setMaxTickets(int maxtickets){
		maxTickets = maxtickets;
	}

	public void setMaxTicketPrice(double maxprice){
		maxPrice = maxprice;
	}
	
	public void setMinTicketPrice(double minprice){
		minPrice = minprice;
	}
	
	//converts x value in accordance with the indices of the boolean array
	public int convertX(int x){
		if(Xmin < 0){
			return x + Math.abs(Xmin);
		}
		else{
			return x - Xmin;
		}
	}
	
	//converts y value in accordance with the indices of the boolean array
	public int convertY(int y){
		if(Ymin<0){
			return y + Math.abs(Ymin);
		}
		else return y - Ymin;
	}
	
	//randomly generates events in the world
	//makes sure every coordinate only has one event
	public void createEvents(){
		
		//random selection of number of events between min and max values
		int numevents = randInt(minEvents, maxEvents);
		
		//sets the size of the events array to the number of events
		events = new Event[numevents];
		
		//generates events randomly
		for(int i = 0; i < numevents; i++){
			
			//randomly selects event coordinates
			int x = randInt(Xmin, Xmax);
			int y = randInt(Ymin, Ymax);
			
			//checks whether the coordinate is occupied already
			//if so, randomly generates events till an unoocupied coordinate is reached
			if(flag[convertX(x)][convertY(y)] == true){
				while(flag[convertX(x)][convertY(y)]!=false){
					x = randInt(Xmin, Xmax);
					y = randInt(Ymin, Ymax);
				}
			}
			
			//sets the boolean value for the coordinate to true (occupied)
			flag[convertX(x)][convertY(y)] = true;
			
			//generates the event object for the event
			Event newEvent = new Event(i+1, x, y);
			
			createTickets(newEvent);
			
			//adds event object to the events array
			events[i] = newEvent;
			
		}
		
	}
	
	//randomizes ticket creation (between 0 and max value)
	// randomizes prices for each tickets between min and max
	public void createTickets(Event event){
		
		//randomly selects number of tickets
		int numTickets = randInt(0, maxTickets);
		
		//if number of tickets is 0, nothing needs to be done. Exits method.
		if(numTickets == 0){
			return;
		}
				
		event.initializeTickets(numTickets);
		
		//randomly generates prices for the selected number of tickets
		for(int i = 0; i < numTickets; i++){
			event.addTicket(randDouble(minPrice, maxPrice));
		}
		
	}

	//sorts the events array based on the compareTo function written in the Event class (based on requirements)
	public void sortEvents(){
		
		Arrays.sort(events, new Comparator<Event>() {
	        @Override
	        public int compare(Event o1, Event o2) {
	            return o1.compareTo(o2);
	        }
	    });
		
	}
	
	//seeds the world with randomized events
	public void seedWorld(){

		initializeFlag();
		
		createEvents();

	}
	
	//locates the k nearest events from the user entered point based on the requirements
	public void locateEvents(int x, int y){
		
		//goes through the events and calculates Manhattan distance for every one of them
		for(Event myEvent: events){
			myEvent.calculateManhattanDist(x, y);
		}
		
		//sorts the events array
		sortEvents();
		
		//Prints out the nearest events as shown in the requirements
		System.out.println();
		System.out.println("Closest Events to ("+x+","+y+"): ");
		System.out.println();

		for(int i = 0; i < numNearbyEvents; i++){
			System.out.println(events[i]);
		}		

	}
	
}
