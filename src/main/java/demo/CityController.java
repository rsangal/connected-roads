package demo;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CityController {
	
	
	@Autowired
	 RoadMap roadMap;
	
	
	  @GetMapping(value = "/connected", produces = "text/plain")
	    public String isConnected(@RequestParam String origin, @RequestParam String destination) {

		  
	        City originCity = roadMap.getCity(origin.toUpperCase());
	        City destCity = roadMap.getCity(destination.toUpperCase());

	        Objects.requireNonNull(originCity);
	        Objects.requireNonNull(destCity);

	        return String.valueOf(Travel.commute(originCity, destCity));
	    }

	    @GetMapping(value = "/", produces = "text/html")
	    public String info() {

	        StringBuilder html = new StringBuilder();

	        html.append("<!DOCTYPE html><head><meta charset=\"utf-8\"><title>City Data</title></head><body>")
	                .append("<h2>Cities Connection List</h2>")
	                .append("<ul>");

	        Collection<City> cities = roadMap.getCityMap().values();
	        for (City city : cities) {
	            html.append("<li>")
	                    .append(city.getName())
	                    .append(" &rarr; ")
	                    .append(city.print())
	                    .append("</li>");
	        }
	        html.append("</ul>");
	        html.append("</body></html>");
	        return html.toString();
	    }

	    @ExceptionHandler(NullPointerException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public String cityError() {
	        return "Either destination or origin city does not exist or invalid";
	    }


}
