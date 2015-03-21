/**
 * 
 */
package com.irontrainsofthegenerality.railroad.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

/**
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public class RouteTest {

	
	@Test(expected=RuntimeException.class)
	public void canNotAddANullTown(){
		Route r = new Route();
		r.addTown(null);
	}
	
	@Test
	public void GivenAListOfTownsStringRepresentationShouldMatch() {
		String expected = "A-B-C";
		
		List<Town> towns = Arrays.asList(new Town(new TownInformation("A",0f, 0f), 0), new Town(new TownInformation("B",0f, 0f), 1), new Town(new TownInformation("C",0f, 0f),2));
		
		Route r = new Route(towns);
		
		String got = r.toString();
		
		assertThat("Expected string is " + expected + " and got " + got, got, is(expected));
	}
	
	/**
	 * You can not travel from a town to the same town
	 */
	@Test(expected=RuntimeException.class)
	public void ShouldTrhowExceptionWhenYouTravelFromToTheSameTown() {
		Route r = new Route();
		Town town = new Town(new TownInformation("A",0f, 0f), 0);
		r.addTown(town);
		// Now a RuntimeException should rise
		r.addTown(town);
	}
	
	/**
	 * You can not travel from a town to the same town.
	 * But now with no instances of the same town.
	 */
	@Test(expected=RuntimeException.class)
	public void ShouldTrhowExceptionWhenYouTravelFromToTheSameTown2() {
		Route r = new Route();
		Town town = new Town(new TownInformation("A",0f, 0f), 0);
		r.addTown(town);
		town = new Town(new TownInformation("A",0f, 0f), 0);
		// Now a RuntimeException should rise
		r.addTown(town);
	}

}
