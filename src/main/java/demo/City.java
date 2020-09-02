package demo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * This Class contains City and stores it's adjacent neighbors to the city
 * City names are case insensitive
 */



@Data
@NoArgsConstructor
public class City {

	private String name;
	private Set<City> adjacent = new HashSet<>();
	
    private City(String name) {
        Objects.requireNonNull(name);
        this.name = name.trim().toUpperCase();
    }
	
    public static City build(String name) {
        return new City(name);
    }
	
    
    public City addAdjacent(City city) {
        adjacent.add(city);
        return this;
    }
    
    @Override
    public String toString() {

        return "City{" +
                "name='" + name + "'" +
                ", nearby='" + print() +
                "'}";
    }

    public String print() {
        return adjacent
                .stream()
                .map(City::getName)
                .collect(Collectors.joining(","));
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
