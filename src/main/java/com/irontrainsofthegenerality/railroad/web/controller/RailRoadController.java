package com.irontrainsofthegenerality.railroad.web.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import scala.annotation.meta.getter;

import com.irontrainsofthegenerality.railroad.builder.RouteParser;
import com.irontrainsofthegenerality.railroad.domain.RailRoad;
import com.irontrainsofthegenerality.railroad.domain.Route;

@RestController
public class RailRoadController {

	private RailRoad railRoad;
	private RouteParser routeParser;
	
	
	@Autowired
	public RailRoadController(RailRoad railRoad, RouteParser routeParser) {
		super();
		this.railRoad = railRoad;
		this.routeParser = routeParser;
	}

	@RequestMapping("/")
    public String index() {
        return railRoad.toString();
    }
	
	@RequestMapping(value = "/route/{route}",method = RequestMethod.GET)
    public String route(@PathVariable String route) {
		if (route != null){
			try{
				Route r = routeParser.parse(new ByteArrayInputStream(route.getBytes()));
				
		        return railRoad.planJourney(r).getResult();
			}
			catch(Exception e){
				return e.getMessage();
			}
		}else{
			return "Please introduce a valid route as A-B-C is for example";
		}
    }
}
