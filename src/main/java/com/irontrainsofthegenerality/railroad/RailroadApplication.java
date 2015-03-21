package com.irontrainsofthegenerality.railroad;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.irontrainsofthegenerality.railroad.builder.RailRoadBuilder;
import com.irontrainsofthegenerality.railroad.builder.RouteParser;
import com.irontrainsofthegenerality.railroad.domain.Journey;
import com.irontrainsofthegenerality.railroad.domain.Route;
import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.Track;
import com.irontrainsofthegenerality.railroad.graph.DGraph;
import com.irontrainsofthegenerality.railroad.planner.RoutePlanner;
import com.irontrainsofthegenerality.railroad.repository.TownRepository;

@SpringBootApplication
public class RailroadApplication {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DGraph<Town, Track> railRoadgraph ;
	@Autowired
	private RailRoadBuilder rrb;
	@Autowired
	@Qualifier("DirectShortestRoutePlanner")
	private RoutePlanner routePlanner;
	@Autowired
	private RouteParser rp;
	@Autowired
	private TownRepository townRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(RailroadApplication.class, args);
    }
    @Bean
    CommandLineRunner init(){
    	return args -> {
    		
    		String graphString = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
    		
    		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
    		
    		railRoadgraph = rrb.buildGraph(ist);
    		
    		Set<Town> towns = railRoadgraph.getVertices();
    		towns.forEach(t->{
    			townRepository.save(t);
    		});
    		List<String> routes = Arrays.asList("A-B-C", "A-D","A-D-C", "A-E-B-C-D", "A-E-D");
    		int i = 1;
    		for (String r : routes) {
			
    			String routeStr = r;
    			
    			Route route = rp.parse(new ByteArrayInputStream(routeStr.getBytes()));
    			
    			Journey journey = routePlanner.planRoute(route, railRoadgraph);
    			
    			logger.info("OUTPUT #{}: {}", i, journey.getResult());
    			
    			i++;
    		}
    	};
    }
}
