package com.irontrainsofthegenerality.railroad.domain;

import java.util.ArrayList;
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
	 * @param t The Town to be added
	 */
	public void addTown(Town t){
		towns.add(t);
	}
	
	@Override
	public String toString(){
		 return towns.stream()
				     .map(Town::getName)
				     .collect(Collectors.joining("-"));
	}
	

}
