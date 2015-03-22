package com.irontrainsofthegenerality.railroad.domain;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.irontrainsofthegenerality.railroad.builder.RailRoadBuilder;
import com.irontrainsofthegenerality.railroad.builder.RouteParser;
import com.irontrainsofthegenerality.railroad.graph.DGraph;
import com.irontrainsofthegenerality.railroad.planner.RoutePlanner;
import com.irontrainsofthegenerality.railroad.repository.TownRepository;

@Component
public class RailRoad {

	private DGraph<Town, Track> railRoadgraph ;
	private RailRoadBuilder rrb;
	private RoutePlanner routePlanner;
	private TownRepository townRepository;


	@Autowired
	public RailRoad(RailRoadBuilder rrb, @Qualifier("DirectShortestRoutePlanner") RoutePlanner routePlanner, TownRepository townRepository) {
		super();
		this.rrb = rrb;
		this.routePlanner = routePlanner;
		this.townRepository = townRepository;
		
		String graphString = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
		
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		
		railRoadgraph = rrb.buildGraph(ist);
		
		Set<Town> towns = railRoadgraph.getVertices();
		towns.forEach(t->{
			townRepository.save(t);
		});
	}
	
	/**
	 * Plans the Journey for the given route.
	 * @param route
	 * @return
	 */
	public Journey planJourney(Route route){
		return routePlanner.planRoute(route, railRoadgraph);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Tracks:\n");
		railRoadgraph.getEdges().forEach(e ->{
			sb.append(e.getvS().getName());
			sb.append(" ==> ");
			sb.append(e.getvE().getName());
			sb.append(": ");
			sb.append(e.getWeight());
			sb.append("\n");
		});
		
		return sb.toString();
	}
	
	
	
}
