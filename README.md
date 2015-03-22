# Rail Road[Trains Exercise]
It's a java exercise.
Spring and the spring-boot project are used in this exercise, to show the knowledge of those technologies.
For such a small problem it's maybe a little bit overkilled to use Dependency injection and others stuff, but it's used just to show the knowledge of the technologies.

## Technologies used
* Java 8.
* Spring.
* Spring boot.

## Usage
Just clone the repo to your machine and yo can run the exercise in two ways:
1. If you have maven, just go to the root of the project and type: 
```
mvn spring-boot:run
```
2. If you don't have maven download a builded jar the exercise in the [githubs release](https://github.com/morfeo8marc/railroad/releases) section. Once dowloaded just type:
```
java -jar railroad.jar
```
## Building a release
In order to build an executable binary of the exercise just go to the project's root directory and type:
```
mvn package
cd target
java -jar railroad.jar
```
## Validating the results
The application shows te desired output using the system standard's output. 
```
                                                                   
          ,,,,,,,,,,,,,,,,,,,                                      
          ;;;;;;;;;;;;;;;;;;;                                      
          ;;;;;;;;;;;;;;;;;;;                                      
          ;;;;;;;;;;;;;;;;;;;                                      
          ;;;;;;;;;;;;;;;;;;;                                      
          ;;;;;;;;;;;;;;;;;;;                                      
          ;;;;;;;;;;;;;;;;;;;                                      
          ;;;;;;;;;;..,;;....   ;;;;;;    ##+      :##;            
          ;;;;;;;;;  `;;        @@@@@@  @@@@@@.   @@@@@@           
          ;;;;;;;;  `;;  `:::   @@     ,@@   @@  #@;  '@@          
          ;;;;;;;  `;;  .;:`;   @@     @@    ;;  @@    ++          
          ;;;;;;; .;:  ,;,  ;   @@###+ @@   ``` `@@                
          ;;;;;;; .:  :;.  ;;   @@@@@# @@  `@@@``@@                
          ;;;;;;; ., :;`  ;;`   @@     @@    .@` @@    @@          
          ;;;;;;; .;;;`  ;;`    @@     ;@@   @@` @@:  .@@          
          ;;;;;;;      `;;  `   @@      @@@@@@@`  @@@@@@           
          ;;;;;;;``````;;``.;   ++       :@@# +`   +@@+            
          ;;;;;;;;;;;;;;;;;;;                                      
          ;;;;;;;;;;;;;;;;;;;                                      
          ,,,,,,,,,,,,,,,,,,,                                      
                                                                

2015-03-22 11:32:16.437  INFO 8538 --- [           main] c.i.railroad.RailroadApplication         : Starting RailroadApplication v0.0.1-SNAPSHOT on toffenthinkpad with PID 8538 (/home/toffen/dev/workspace/railroad/target/railroad-0.0.1-SNAPSHOT.jar started by toffen in /home/toffen/dev/workspace/railroad/target)
2015-03-22 11:32:16.477  INFO 8538 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@51dceb5d: startup date [Sun Mar 22 11:32:16 CET 2015]; root of context hierarchy
2015-03-22 11:32:17.051  INFO 8538 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2015-03-22 11:32:17.075  INFO 8538 --- [           main] ication$$EnhancerBySpringCGLIB$$b502774f : OUTPUT #1: 9
2015-03-22 11:32:17.077  INFO 8538 --- [           main] ication$$EnhancerBySpringCGLIB$$b502774f : OUTPUT #2: 5
2015-03-22 11:32:17.077  INFO 8538 --- [           main] ication$$EnhancerBySpringCGLIB$$b502774f : OUTPUT #3: 13
2015-03-22 11:32:17.078  INFO 8538 --- [           main] ication$$EnhancerBySpringCGLIB$$b502774f : OUTPUT #4: 22
2015-03-22 11:32:17.078  INFO 8538 --- [           main] ication$$EnhancerBySpringCGLIB$$b502774f : OUTPUT #5: NO SUCH ROUTE
2015-03-22 11:32:17.081  INFO 8538 --- [           main] c.i.railroad.RailroadApplication         : Started RailroadApplication in 0.881 seconds (JVM running for 1.228)
2015-03-22 11:32:17.081  INFO 8538 --- [       Thread-2] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@51dceb5d: startup date [Sun Mar 22 11:32:16 CET 2015]; root of context hierarchy
2015-03-22 11:32:17.082  INFO 8538 --- [       Thread-2] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
```

## The Problem to model
The local commuter railroad services a number of towns in Kiwiland.
Because of monetary concerns, all of the tracks are 'one-way.' That is, a route from Kaitaia to Invercargill does not imply the existence of a route from Invercargill to Kaitaia. In fact, even if both of these routes do happen to exist, they are distinct and are not necessarily the same distance!

The purpose of this problem is to help the railroad provide its customers with information about the routes. In particular, you will compute the distance along a certain route, the number of different routes between two towns, and the shortest route between two towns.

Input: A directed graph where a node represents a town and an edge represents a route between two towns. The weighting of the edge represents the distance between the two towns. A given route will never appear more than once, and for a given route, the starting and ending town will not be the same town.

Output: For test input 1 through 5, if no such route exists, output 'NO SUCH ROUTE'.
Please use the most direct route and do not make any extra stops! For example, the first problem means to start at city A, then travel directly to city B (a distance of 5), then directly to city C (a distance of 4).
1. The distance of the route A-B-C.
2. The distance of the route A-D.
3. The distance of the route A-D-C.
4. The distance of the route A-E-B-C-D.
5. The distance of the route A-E-D.

Test Input:

For the test input, the towns are named using the first few letters of the alphabet from A to D. A route between two towns (A to B) with a distance of 5 is represented as AB5.

Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7

Expected Output:
Output #1: 9
Output #2: 5
Output #3: 13
Output #4: 22
Output #5: NO SUCH ROUTE
