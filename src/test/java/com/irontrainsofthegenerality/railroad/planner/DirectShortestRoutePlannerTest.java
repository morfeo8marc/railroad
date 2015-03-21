package com.irontrainsofthegenerality.railroad.planner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.irontrainsofthegenerality.railroad.RailroadApplication;
import com.irontrainsofthegenerality.railroad.builder.RailRoadBuilder;
import com.irontrainsofthegenerality.railroad.domain.Distance;
import com.irontrainsofthegenerality.railroad.domain.Journey;
import com.irontrainsofthegenerality.railroad.domain.Route;
import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.TownInformation;
import com.irontrainsofthegenerality.railroad.domain.Track;
import com.irontrainsofthegenerality.railroad.graph.DGraph;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RailroadApplication.class})
public class DirectShortestRoutePlannerTest {
	
	@Autowired
	@Qualifier("DirectShortestRoutePlanner")
	private RoutePlanner rp;
	
	@Autowired
	private RailRoadBuilder rrb;

	@Test
	public void ImpossibleRoute() {
		DGraph<Town, Track> railRoutesDGraph = buildRailRoad("AB2");
		Route route = new Route();
		
		Town t = new Town(new TownInformation("B", 0, 0), 2);
		route.addTown(t);
		t = new Town(new TownInformation("C", 0, 0), 3);
		route.addTown(t);
		
		Journey planRoute = rp.planRoute(route, railRoutesDGraph);
		assertThat("Route can not be done", planRoute.isCanBeDone(), is(false));
	}
	
	@Test
	public void PossibleRoute() {
		DGraph<Town, Track> railRoutesDGraph = buildRailRoad("AB2");
		Route route = new Route();
		Distance d = new Distance(2f, "km");
		
		Town t = new Town(new TownInformation("A", 0, 0), 0);
		route.addTown(t);
		t = new Town(new TownInformation("B", 0, 0), 1);
		route.addTown(t);
		
		Journey journey = rp.planRoute(route, railRoutesDGraph);
		assertThat("Route can be done", journey.isCanBeDone(), is(true));
		assertThat("Journey is 2 km long", journey.getDistance(), is(equalTo(d)));
	}
	
	
	
	/**
	 * Helper class to reuse code to constructs t a graph
	 * 
	 * @param graphString The String representing the graph
	 * @return A Graph create from the the String that represent the graph
	 */
	public DGraph<Town, Track> buildRailRoad(String graphString) {
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		
		DGraph<Town, Track> buildGraph = rrb.buildGraph(ist);
		return buildGraph;
	}

}
