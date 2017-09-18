# Event-Locator
This is an Event Locator program I built for Viagogo. <br />
-> It operates in a world that is a bounded cartesian plane. <br />
-> It randomly generates seed data (events and tickets for the events) and then locates the k (k=5 here) nearest events from a user-entered coordinate. <br />

Instructions to run code:
1. Download the runnable jar file Event_Locator.jar from the folder.
2. Open terminal/Command Line and switch directory to the one containing Event_Locator.jar
3. type "java -jar Event_Locator.jar" into the command line and run program.

Assumptions:
1. Each coordinate holds only one event.
2. Each event has a unique numeric identifier.
3. Each event has 0 or more tickets.
4. Each ticket has a nonzero price.
5. Distance between 2 points is calculated as the Manhattan Distance.
6. The coordinates are integers.
7. We can only pre-process the Manhattan Distance i.e the seed data has to be generated first and only then can we take a coordinate input from the user.
8. In case of event clashes (same manhattan distance), the event with the cheapest tickets are preferred. The events with no tickets are given last preference.

Scalability questions:
1. If we had to support multiple events in one location, we could get rid of the boolean flag array, and not check if a coordinate is occupied when we randomly generate events. 
2. This would in fact reduce the memory allocation for a much larger world size and in case of clashes, the compareTo method would still work and sort the events based on Cheapest Tickets.
3. We could reduce space and time complexities that come with the larger world size by using more efficient data structures -> trees, graphs, hash tables.

Possible Improvements:
1. If we post-process the world i.e take coordinate input and then seed data, we could reduce runtime by combining the Manhattan distance calculation with event generation and use a knockout array to keep the nearest K events.
2. If we have to pre-process, we could possibly calculate a relative distance from (0,0) and use an algorithm that calculates the maximum probability range of the nearest events so we don't have to go through all the events.

Example program run: <br />
<br />
Please Input X Coordinate: <br />
-> 12<br />
Please re-enter X Coordinate value between -10 and 10: <br />
-> 2<br />
Please Input Y Coordinate: <br />
-> -14<br />
Please re-enter Y Coordinate value between -10 and 10: <br />
-> 4<br />
<br />
Closest Events to (2,4):<br /> 
<br />
Event 030 - $23.04, Distance 2<br />
Event 053 - $28.54, Distance 2<br />
Event 027 - $31.78, Distance 3<br />
Event 009 - $21.98, Distance 4<br />
Event 008 - $39.26, Distance 4<br />




