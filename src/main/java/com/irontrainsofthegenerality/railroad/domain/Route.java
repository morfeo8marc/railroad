package com.irontrainsofthegenerality.railroad.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A Route represent a {@link List} of {@link Town} where someone wants to go.
 * In a route can exist more than once a Town, but not in a row.
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public class Route {

	private List<Town> towns;
	
	/**
	 * Constructs and empty List of towns;
	 */
	public Route() {
		this.towns = new LinkedList<Town>();
	}

	/**
	 * 
	 * @param towns The towns that will have the route
	 */
	public Route(List<Town> towns) {
		this.towns = towns;
	}
	
	/**
	 * Adds a town to the route.
	 * @param town The Town to be added
	 * @throws RuntimeException if town is null or if the town is added
	 * 		   twice in a row
	 */
	public void addTown(Town town){
		if (town == null){
			throw new RuntimeException("town can not be null");
		}
		if (towns.size() > 0) {
			Town lastAddedTown = towns.get(towns.size()-1);
			if (lastAddedTown.equals(town)){
				throw new RuntimeException("Can make a route from a town to the same town");
			}
			
		}
		towns.add(town);
	}
	
	public int getNumberOTowns(){
		return towns.size();
	}
	
	@Override
	public String toString(){
		 return towns.stream()
				     .map(Town::getName)
				     .collect(Collectors.joining("-"));
	}

	/**
	 * Return the next Town to visit without removing it.
	 * @return A town no visit.
	 * @throws RuntimeException if no more towns are to visit
	 */
	public Town getNextTownToVisit() {
		if (getNumberOTowns() < 2){
			throw new RuntimeException("There are no more Towns to visit");
		}
		return towns.get(1);
	}
	
	/**
	 * Mark next town as visited, creates a new route
	 * without the first Town
	 * @return A new Route without the Fist Town
	 * @throws RuntimeException if no more towns are left.
	 */
	public Route markNextTownAsVisited(){
		if (getNumberOTowns() == 0){
			throw new RuntimeException("There are no towns to mark as visited");
		}
		List<Town> townsTovisit = new LinkedList<Town>(towns);
		townsTovisit.remove(0);
		
		Route route = new Route(townsTovisit);
		return route;
	}

	/**
	 * Start Town return the first town in the  route
	 * @returnThe start town of the route
	 * @throws RuntimeException if there are no towns
	 */
	public Town getStartTown() {
		if (getNumberOTowns() == 0){
			throw new RuntimeException("There are no towns to mark as visited");
		}
		return towns.get(0);
	}
	
}
