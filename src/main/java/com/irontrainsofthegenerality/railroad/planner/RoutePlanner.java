package com.irontrainsofthegenerality.railroad.planner;

import com.irontrainsofthegenerality.railroad.domain.Journey;
import com.irontrainsofthegenerality.railroad.domain.Route;
import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.Track;
import com.irontrainsofthegenerality.railroad.graph.DGraph;

public interface RoutePlanner {

	/**
	 * Plan a Route try to create a journey visiting all the towns
	 * in the route.
	 * If the route is not possible, the journey will be market with
	 * {@link Journey#isCanBeDone} as false.
	 * If the journey can be done, then distance will be calculated
	 * 
	 * @param route The route that will be try to done in the journey
	 * @param railRoad The rails route the travel
	 * @throws RuntimeException if the route has not at least 2 Towns
	 * @throws IllegalArgumentException if the route.
	 * @return Return a journey that can be done or not.
	 */
	Journey planRoute(Route route, DGraph<Town, Track> railRoad);
}
