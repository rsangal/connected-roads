package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class CityTest {

    @Test
    public void build() {
        City city = City.build("Montreal");
        assertEquals("MONTREAL", city.getName());
    }

    @Test
    public void buildWithNeighbours() {
        City city = City.build("Montreal");
        city.addAdjacent(City.build("Laval"))
                .addAdjacent(City.build("Lachine"));

        Set<City> nearby = city.getAdjacent();
        assertEquals(2, nearby.size());
        assertTrue(nearby.contains(City.build("Laval")));
    }


    @Test
    public void addNearby() {
        City city = City.build("Montreal");
        city.addAdjacent(City.build("Laval"))
                .addAdjacent(City.build("Lachine"));

        assertEquals(2, city.getAdjacent().size());
    }

    @Test
    public void addNearbyDuplicates() {
        City city = City.build("Montreal");
        city.addAdjacent(City.build("Laval"))
                .addAdjacent(City.build("LAVAL"))
                .addAdjacent(City.build("  LaVal"))
                .addAdjacent(City.build("  LaVaL "));

        assertEquals(1, city.getAdjacent().size());
    }


}