package demo;

import java.util.HashMap;
import java.util.Map;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ConnectedRoadsApplicationTests {

	
	  @Autowired
	  RoadMap roadMap;

	    @Autowired
	    private TestRestTemplate restTemplate;

	    @Test
	    public void fileLoad() {
	        assertFalse(roadMap.getCityMap().isEmpty(), "File load failed");
	    }

	    @Test
	    public void sameCity() {
	        City city = City.build("A");
	        assertTrue(Travel.commute(city, city));
	    }

	    @Test
	    public void neighbours() {
	        City cityA = roadMap.getCity("A");
	        City cityB = roadMap.getCity("B");

	        assertNotNull(cityA, "Invalid test data. City not found: A");
	        assertNotNull(cityB, "Invalid test data. City not found: B");

	        assertTrue(Travel.commute(cityA, cityB));
	    }

	    @Test
	    public void distantConnected() {
	        City cityA = roadMap.getCity("F");
	        City cityB = roadMap.getCity("A");

	        assertNotNull(cityA,"Invalid test data. City not found: F");
	        assertNotNull(cityB,"Invalid test data. City not found: A");

	        assertTrue(Travel.commute(cityA, cityB));
	    }

	    @Test
	    public void restConnectedIT() {

	        Map<String, String> params = new HashMap<>();
	        params.put("origin", "F");
	        params.put("destination", "A");

	        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
	        assertEquals("true", body);
	    }

	    @Test
	    public void restNotConnectedIT() {

	        Map<String, String> params = new HashMap<>();
	        params.put("origin", "a");
	        params.put("destination", "l");

	        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
	        assertEquals("false", body);
	    }

	    @Test
	    public void badRequestIT() {
	        String body = restTemplate.getForObject("/connected", String.class);
	        ResponseEntity<String> response = restTemplate.exchange("/connected?origin=none&destination=none", HttpMethod.GET, HttpEntity.EMPTY, String.class);
	        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	    }

	
	
}
