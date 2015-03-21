package com.irontrainsofthegenerality.railroad.planner;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.irontrainsofthegenerality.railroad.domain.Distance;
import com.irontrainsofthegenerality.railroad.domain.Journey;
import com.irontrainsofthegenerality.railroad.domain.Route;
import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.Track;
import com.irontrainsofthegenerality.railroad.graph.DGraph;

/**
 * Direct shortest route planner, plans a route that must be direct
 * from one town to the other without passing by any intermediate
 * {@link Town}.
 * Off all the possible calculated journeys it returns the shortest.
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
@Qualifier("DirectShortestRoutePlanner")
@Component
public class DirectShortestRoutePlanner implements RoutePlanner {

	
	@Override
	public Journey planRoute(Route route, DGraph<Town, Track> graph) {
		if (route == null){
			throw new IllegalArgumentException("The route planner need a not null route to plan");
		}
		if (route.getNumberOTowns()< 2){
			throw new RuntimeException("To plan a route at least 2 town mut be in the route, and it only has " + route.getNumberOTowns());
		}
		Journey shortestJourney;
		Journey actualJorney;
		
		Distance dZero = new Distance(0f, "km");
		actualJorney = new Journey(route, dZero, false);	
		
		shortestJourney = travelRoute(route.getStartTown(), route, graph, actualJorney);
		
		return shortestJourney;
	}
	
	/**
	 * 
	 * @param actualTown The actual town from where we must travel to the Route next stop
	 * @param route The Route to be traveled
	 * @param graph The graph with the town and the connections
	 * @return A journey that can be done or not.
	 */
	private Journey travelRoute(Town actualTown, Route route, DGraph<Town, Track> graph, Journey actualJorney){
		Town nextTown = route.getNextTownToVisit();
		Optional<Set<Track>> tracks = graph.getEdges(actualTown, nextTown);
		// Its impossible to go to the destination if there are no tracks. Maybe call to the governator to build one
		if (! tracks.isPresent() || tracks.get().isEmpty()){
			actualJorney.setCanBeDone(false);
			return actualJorney;
		}
		
		Track shortestTrack = getShortestTrack(tracks.get());
		actualJorney.addDistance(shortestTrack.getWeight());
		
		Route remainRoute = route.markNextTownAsVisited();
		nextTown = route.getNextTownToVisit();
		
		if (remainRoute.getNumberOTowns() > 1){
			// There are more towns to visit, lets enjoy the journey 
			actualJorney = travelRoute(nextTown, remainRoute, graph, actualJorney);
		}else{
			// We are done we have reached our destination
			actualJorney.setCanBeDone(true);
		}
		
		
		return actualJorney;
	}

	/**
	 * Get Shortest track return the shortest track.
	 * @param tracks The tracks used to find the shortest. Can not be empty
	 * @return The shortest track in tracks
	 * @throws RuntimeException if tracks is an empty set or null
	 */
	private Track getShortestTrack(Set<Track> tracks) {
		if (tracks == null ||  tracks.isEmpty()){
			throw new RuntimeException("Tracks can not be empty nor null");
		}
		Optional<Track> findFirst = tracks.stream().sorted().findFirst();
		// We can safely return the first element because the set is not empty and it is the shortest 
		return findFirst.get();
	}
	
}
