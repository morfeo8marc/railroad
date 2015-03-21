package com.irontrainsofthegenerality.railroad.domain;

import com.irontrainsofthegenerality.railroad.planner.RoutePlanner;

/**
 * A Journey is as a route to be done.
 * If the route can be done the the total travel distance
 * is calculated by a {@link RoutePlanner}
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public class Journey implements Comparable<Journey>{
	private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
	private Route route;
	private Distance distance;
	private boolean canBeDone;
	
	public Journey(Route route, Distance distance, boolean canBeDone) {
		super();
		this.route = route;
		this.distance = distance;
		this.canBeDone = canBeDone;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public boolean isCanBeDone() {
		return canBeDone;
	}

	public void setCanBeDone(boolean canBeDone) {
		this.canBeDone = canBeDone;
	}
	
	public void addDistance(Distance distance){
		Float addedDistance = this.distance.getValue() + distance.getValue();
		this.distance.setValue(addedDistance);
	}
	
	/**
	 * Result return the distance as a string or NO_SUCH_ROUTE
	 * @return The distance as a string.
	 */
	public String getResult(){
		if (isCanBeDone()){
			return String.valueOf(distance.getValue().intValue());
		}else{
			return NO_SUCH_ROUTE;
		}
		
	}

	/**
	 * Compares 2 Journeys.
	 * @param anotherJourney can not be null
	 * @return -1, 0, 1 if the current journey is shorter, equals, or longer respectively  
	 */
	@Override
	public int compareTo(Journey anotherJourney) {
		
		return this.getDistance().compareTo(anotherJourney.getDistance());
	}
	
	
}
