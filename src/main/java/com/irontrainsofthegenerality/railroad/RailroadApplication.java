package com.irontrainsofthegenerality.railroad;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.irontrainsofthegenerality.railroad.builder.RouteParser;
import com.irontrainsofthegenerality.railroad.domain.Journey;
import com.irontrainsofthegenerality.railroad.domain.RailRoad;
import com.irontrainsofthegenerality.railroad.domain.Route;

@SpringBootApplication
public class RailroadApplication {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private RailRoad railRoad;
	@Autowired
	private RouteParser routeParser;
	
    public static void main(String[] args) {
        SpringApplication.run(RailroadApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(){
    	return args -> {
    		
    		
    		List<String> routes = Arrays.asList("A-B-C", "A-D","A-D-C", "A-E-B-C-D", "A-E-D");
    		int i = 1;
    		for (String r : routes) {
			
    			String routeStr = r;
    			
    			Route route = routeParser.parse(new ByteArrayInputStream(routeStr.getBytes()));
    			
    			Journey journey = railRoad.planJourney(route);
    			
    			logger.info("OUTPUT #{}: {}", i, journey.getResult());
    			
    			i++;
    		}
    	};
    }
    
}
