package demo;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Travel {

	 private static final Log logger = LogFactory.getLog(Travel.class);
	 

	    /**
	     * Find if destination city is reachable from origin. Will visit all the cities
	     * on the bucket list which is built by collecting all the neighbors of a visited place
	     * @param origin the origin
	     * @param destination the destination
	     * @return true if cities are connected
	     */
	    public static boolean commute(City origin, City destination) {



	        logger.info("Origin: " + origin.getName() + ", destination: " + destination.getName());

	        if (origin.equals(destination)) return true;

	        if (origin.getAdjacent().contains(destination)) return true;

	        /*
	         * The origin city was already visited since we have started from it
	         */
	        Set<City> visited = new HashSet<>(Collections.singleton(origin));

	        /*
	         * Put all the neighboring cities into a bucket list
	         */
	        Deque<City> bucketlist = new ArrayDeque<>(origin.getAdjacent());


	        while (!bucketlist.isEmpty()) {


	            City city = bucketlist.getLast();

	            if (city.equals(destination)) return true;

	            // remove the city from the bucket list

	            // first time visit?
	            if (!visited.contains(city)) {

	                visited.add(city);

	                // add neighbors to the bucket list and
	                // remove already visited cities from the list
	                bucketlist.addAll(city.getAdjacent());
	                bucketlist.removeAll(visited);

	                logger.info("Visiting: ["
	                        + city.getName()
	                        + "] , neighbours: ["
	                        + (city.print())
	                        + "], bucketlist: ["
	                        + bucketlist.toString()
	                        + "]");
	            } else {
	                // the city has been visited, so remove it from the bucket list
	                bucketlist.removeAll(Collections.singleton(city));
	            }
	        }

	        return false;
	    }


}
