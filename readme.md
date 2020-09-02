## Find if a path exists between two cities
#### This application determines if two cities are connected. 

Two points are considered connected if there’s a series of roads that can be traveled from one city to another.

Demo list of roads is available in a project file `cities.txt` located in the `resource` directory. 
File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

# IMPORTANT: Lombok

To avoid having to write and maintain what is mostly boilerplate Java code, I've elected to use https://projectlombok.org/[Lombok] in the project. When building from the command line, using Maven, you shouldn't encounter any problems, as Lombok is included as part of the build process. But you will very likely encounter issues if you import these projects into your IDE.

If, after importing the projects into your IDE, you see errors complaining about missing getters, setters, constructors, or `log` instance variables, it's because Lombok is not installed in your IDE. These bits of code will be generated by Lombok automatically, but your IDE doesn't know that and complains that they are missing.

To fix the errors, simply install Lombok. Lombok has support for most common (and arguably a few uncommon) IDEs, so no matter which IDE you use, you should be covered. See https://projectlombok.org/setup/overview for details on installing Lombok in your IDE.


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


   

