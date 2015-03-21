package com.irontrainsofthegenerality.railroad.builder;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.irontrainsofthegenerality.railroad.RailroadApplication;
import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.Track;
import com.irontrainsofthegenerality.railroad.graph.DGraph;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RailroadApplication.class})
public class RailRoadBuilderTest {
	
	@Autowired
	private RailRoadBuilder rrb;
		
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowException(){
		rrb.buildGraph(null);
	}
	
	@Test
	public void shouldReturnEmptyGraph(){
		String graphString = "";
		DGraph<Town, Track> buildGraph = buildRailRoad(graphString);
		assertThat("Vertices should be empty", buildGraph.getVertices().isEmpty(), is(equalTo(true)));
		assertThat("Edges should be empty", buildGraph.getEdges().isEmpty(), is(equalTo(true)));
	}

	@Test
	public void railRoadShouldNotHaveDuplciatedTownNames() {
		String graphString = "AB2,AB2";
		DGraph<Town, Track> buildGraph = buildRailRoad(graphString);
		
		long count = countTowns(buildGraph, "A");
		assertThat("Town A should exist only 1 time", count, is(1l));
		count = countTowns(buildGraph, "B");
		assertThat("Town B should exist only 1 time", count, is(1l));
	}

	/**
	 * @param buildGraph
	 * @param townName 
	 * @return The number of towns that has as name townName
	 */
	private long countTowns(DGraph<Town, Track> buildGraph, String townName) {
		return buildGraph.getVertices().stream().filter(v-> v.getName().equals(townName)).count();
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
