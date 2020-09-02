## Find if a path exists between two cities
#### This application determines if two cities are connected. 

Two points are considered connected if there’s a series of roads that can be traveled from one city to another.

Demo list of roads is available in a project file `cities.txt` located in the `resource` directory. 
File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

## The Project uses Lombok which help in generating getters and setters for the POJO classes. Make sure to download lombok jar for your IDE. Otherwise, the IDE doesn't understand Lombok annotation. Follow the link for instructions for your IDE
[https://projectlombok.org/setup/eclipse](https://projectlombok.org/setup/eclipse)


### Build from source
```bash
    mvn clean install
```
### Run the application

Using maven Spring Boot plugin 
``` 
    mvn spring-boot:run 
```
Using Java command line 
```
    java -jar target/connected-roads-0.0.1-SNAPSHOT.jar
```

### Show the city list and it's connections

[http://localhost:8080/](http://localhost:8080/) 


### Swagger

Swagger UI for API docs

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html`)


### Test Cases

Example `Boston` and `Newark` _are_ connected:

[http://localhost:8080/connected?origin=Boston&destination=Newark](http://localhost:8080/connected?origin=Boston&destination=Newark) (result **true**)

Example `Boston` and `Philadelphia` _are_ connected:

[http://localhost:8080/connected?origin=Boston&destination=Philadelphia](http://localhost:8080/connected?origin=Boston&destination=Philadelphia) (result **true**)

 
Example `Philadelphia` and `Albany` _are not_ connected

[http://localhost:8080/connected?origin=Philadelphia&destination=Albany](http://localhost:8080/connected?origin=Philadelphia&destination=Albany) (result **false**)


   

