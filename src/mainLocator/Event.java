package mainLocator;

public class Event implements Comparable<Event>{
	
	private int id, xcoord, ycoord, ticketIndex, manhattandist;

	private double[] tickets;
		
	private double cheapestTicket;
	
	//Main constructor for clas which takes in coordinates and event id
	public Event(int id, int x, int y){
		this.id = id;
		this.xcoord = x;
		this.ycoord = y;
		manhattandist = 0;
		ticketIndex = 0;
		cheapestTicket = -1.0;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}
	
	public int getX(){
		return xcoord;
	}
	
	public int getY(){
		return ycoord;
	}
	
	public void setX(int x){
		xcoord = x;
	}
	
	public void setY(int y){
		ycoord = y;
	}
	
	//Creates tickets array with length = number of tickets
	public void initializeTickets(int numtickets){
		tickets = new double[numtickets];
	}
	
	//Adds ticket with a decimal price value to the tickets array
	public void addTicket(double price){
		
		//for the first entry to the array
		if(ticketIndex==0){
			cheapestTicket = price;
		}
		
		//maintains cheapest ticket value
		else if(price<cheapestTicket){
			cheapestTicket = price;
		}
		
		tickets[ticketIndex++] = price;
		
	}
	
	public double getCheapestTicket(){
		return cheapestTicket;
	}
	
	public int getNumberOfTickets(){
		return ticketIndex;
	}
	
	//calculates manhattan distance by definition from point entered by user
	public void calculateManhattanDist(int x, int y){
		manhattandist = Math.abs(x-xcoord) + Math.abs(y-ycoord);
	}
	
	public int getManhattanDist(){
		return manhattandist;
	}
	
	//Custom toString method to print out the contents of an event as required by the challenge
	@Override
	public String toString(){
		if(this.getNumberOfTickets()==0){
			return "Event "+String.format("%03d", getId())+" - Unfortunately, this event has no tickets left! Distance "+getManhattanDist();
		}
		return "Event "+String.format("%03d", getId())+" - $"+String.format("%.2f", getCheapestTicket())+", Distance "+getManhattanDist();
	}

	//Custom compareTo method for tickets that compares their values based on 
	//manhattan distance from the user-entered points
	//used for sorting the tickets based on these conditions
	
	@Override
	public int compareTo(Event e) {
		
		//in case of ties for distance, the next compare condition is the cheapest ticket price
		if(this.getManhattanDist()-e.getManhattanDist()==0){
			
			//if number of tickets are 0, then other event takes preference
			if(this.getNumberOfTickets() == 0){
				return 1;
			}
			
			else if(e.getNumberOfTickets() == 0){
				return -1;
			}
			
			return (int)(this.getCheapestTicket()-e.getCheapestTicket());
		}
		
		return this.getManhattanDist()-e.getManhattanDist();
	}

}
