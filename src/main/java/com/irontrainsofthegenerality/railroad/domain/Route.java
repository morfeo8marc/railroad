package com.irontrainsofthegenerality.railroad.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

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
		this.towns = new ArrayList<Town>();
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
	
	@Override
	public String toString(){
		 return towns.stream()
				     .map(Town::getName)
				     .collect(Collectors.joining("-"));
	}
	

}
