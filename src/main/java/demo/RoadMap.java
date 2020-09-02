package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Build a road map from the text file. Names are 
 * The application will fail fast if data file is not readable or invalid
 * 
 */

@Component
public class RoadMap {

	
    private final Log logger = LogFactory.getLog(getClass());
    
    private Map<String, City> map = new HashMap<>();
    
    @Value("${data.file:classpath:cities.txt}")
    private String CITIES;
    
    
    @Autowired
    private ResourceLoader resourceLoader;


    public Map<String, City> getCityMap() {
        return map;
    }
    
    
    @PostConstruct
    private void read() throws IOException {

        logger.info("Reading data");
        
        Resource resource = resourceLoader.getResource(CITIES);

        InputStream is;

        if (!resource.exists()) {
            is = new FileInputStream(new File(CITIES));
        } else {
            is = resource.getInputStream();
        }

        
        
        Scanner scanner = new Scanner(is);

        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            if (StringUtils.isEmpty(line)) continue;

            logger.info(line);

            String[] split = line.split(",");
            String Akey = split[0].trim().toUpperCase();
            String Bkey = split[1].trim().toUpperCase();

            if (!Akey.equals(Bkey)) {
                City A = map.getOrDefault(Akey, City.build(Akey));
                City B = map.getOrDefault(Bkey, City.build(Bkey));

                
                A.addAdjacent(B);
                B.addAdjacent(A);

                map.put(A.getName(), A);
                map.put(B.getName(), B);
            }
        }

        logger.info("Map: " + map);
        
        scanner.close();
    }

    public City getCity(String name) {
        return map.get(name);
    }
    
}
