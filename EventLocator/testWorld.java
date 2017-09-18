package EventLocator;

import mainLocator.Event;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import mainLocator.World;

@SuppressWarnings("unused")
public class testWorld {
	World myWorld = new World();

	
	@Test
	public void testInitializeFlag(){
		myWorld.initializeFlag();
		assertEquals(21, myWorld.flag.length);
		assertEquals(21, myWorld.flag[0].length);
		for(int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				assertFalse(myWorld.flag[i][j]);
			}
		}	
	}
	
	@Test
	public void testSettersGettersConstructors(){
		
		myWorld = new World(-20, 10, -10, 20, 80, 120, 4);
		myWorld.initializeFlag();
		
		assertEquals(31, myWorld.flag.length);
		assertEquals(31, myWorld.flag[0].length);
		assertEquals(10, myWorld.getXmax());
		assertEquals(-20, myWorld.getXmin());
		assertEquals(-10, myWorld.getYmin());
		assertEquals(20, myWorld.getYmax());
		assertEquals(80, myWorld.getMinEvents());
		assertEquals(120, myWorld.getMaxEvents());
		assertEquals(4, myWorld.getK());
		
		myWorld.SetKNearestEvents(8);
		myWorld.setMinEvents(5);
		myWorld.setMaxEvents(20);
		myWorld.setMaxTicketPrice(10);
		myWorld.setMinTicketPrice(5);
		
		assertEquals(8, myWorld.getK());
		assertEquals(5, myWorld.getMinEvents());
		assertEquals(20, myWorld.getMaxEvents());
		assertEquals(10, myWorld.getMaxTicketPrice());
		assertEquals(5, myWorld.getMinTicketPrice());
	}
	
	@Test
	public void testConverts(){
		assertEquals(0, myWorld.convertX(-10));
		assertEquals(2, myWorld.convertX(-8));
		assertEquals(25, myWorld.convertX(15));
		
		assertEquals(0, myWorld.convertY(-10));
		assertEquals(2, myWorld.convertY(-8));
		assertEquals(25, myWorld.convertY(15));
		
		myWorld.setXmax(20);
		myWorld.setXmin(-50);
		myWorld.setYmax(50);
		myWorld.setYmin(4);

		assertEquals(40, myWorld.convertX(-10));
		assertEquals(60, myWorld.convertX(10));
		assertEquals(6, myWorld.convertY(10));
	}
	
	@Test
	public void testCreateTickets(){
		Event e = new Event(1, 0, 0);
		myWorld.createTickets(e);
		assertTrue(e.getNumberOfTickets() <= myWorld.getMaxTicket() && e.getNumberOfTickets() >= 0);
		assertTrue(e.getCheapestTicket() <= myWorld.getMaxTicketPrice() && e.getCheapestTicket() >= myWorld.getMinTicketPrice());
	}
	
	@Test
	public void testSeedWorldAndSort(){
		myWorld.seedWorld();
		myWorld.sortEvents();
		
		for(int i = 0; i < myWorld.events.length-1; i++){
			assertTrue(myWorld.events[i].compareTo(myWorld.events[i+1])<=0);
		}
	}
	
}
