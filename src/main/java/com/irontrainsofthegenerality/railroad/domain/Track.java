package com.irontrainsofthegenerality.railroad.domain;

import com.irontrainsofthegenerality.railroad.graph.Edge;

/**
 * A Track is a human representation of and Edge.
 * A Track has a distance between two Towns
 * And can be only traveled in one direction
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public class Track extends Edge<Distance, Town> {

	public Track(Town tS, Town tE, Distance distance) {
		super(tS, tE, distance);
	}

}
