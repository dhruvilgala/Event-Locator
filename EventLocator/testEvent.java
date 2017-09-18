package EventLocator;

import static org.junit.Assert.*;

import mainLocator.Event;
import org.junit.Test;

public class testEvent {
	Event e = new Event(1, 1, 1);
	
	@Test
	public void testSettersAndGetters(){
		assertEquals(1, e.getId());
		e.setId(3);
		assertEquals(3, e.getId());
		e.setX(10);
		assertEquals(10, e.getX());
		e.setY(-4);
		assertEquals(-4, e.getY());
	}
	
	@Test
	public void testTickets(){
		e.initializeTickets(20);
		assertEquals(0, e.getNumberOfTickets());
		for(int i = 0; i < 10 ; i++){
			e.addTicket(10.0);
		}
		assertEquals(10, e.getNumberOfTickets());
		assertEquals(10.0, e.getCheapestTicket());
		e.addTicket(2.0);
		assertEquals(2.0, e.getCheapestTicket());

	}
	
	@Test
	public void testManhattanDistance(){
		e.calculateManhattanDist(2, 2);
		assertEquals(2, e.getManhattanDist());
		e.setX(-4);
		e.setY(-5);
		e.calculateManhattanDist(2, -8);
		assertEquals(9, e.getManhattanDist());
	}
	

	@Test
	public void testCompareToByDistance(){
		Event x = new Event(2, 6, 7);
		e.initializeTickets(20);
		double ticketPrice = 20.0;
		for (int i = 0; i<20; i++){
			e.addTicket(ticketPrice);
			ticketPrice+=5.0;
		}
		ticketPrice = 10.0;
		x.initializeTickets(15);
		for (int i = 0; i<15; i++){
			x.addTicket(ticketPrice);
			ticketPrice+=5.0;
		}
		e.calculateManhattanDist(0, 0);
		x.calculateManhattanDist(0, 0);
		
		assertTrue(e.compareTo(x) < 1);
	}
	
	@Test
	public void testCompareToByCheapestTicket(){
		Event x = new Event(2, -1, -1);
		e.initializeTickets(20);
		double ticketPrice = 20.0;
		for (int i = 0; i<20; i++){
			e.addTicket(ticketPrice);
			ticketPrice+=5.0;
		}
		ticketPrice = 10.0;
		x.initializeTickets(15);
		for (int i = 0; i<15; i++){
			x.addTicket(ticketPrice);
			ticketPrice+=5.0;
		}
		e.calculateManhattanDist(0, 0);
		x.calculateManhattanDist(0, 0);
		
		assertTrue(e.compareTo(x) > 1);
	}
	
	@Test
	public void testCompareToWith0Tickets(){
		Event x = new Event(2, -1, -1);
		e.initializeTickets(20);
		double ticketPrice = 20.0;
		for (int i = 0; i<20; i++){
			e.addTicket(ticketPrice);
			ticketPrice+=5.0;
		}
		x.initializeTickets(0);
		e.calculateManhattanDist(0, 0);
		x.calculateManhattanDist(0, 0);
		
		assertTrue(e.compareTo(x) < 1);
	}
	
	
}


