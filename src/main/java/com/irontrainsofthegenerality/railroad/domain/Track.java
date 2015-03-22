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

	private int ID;
	
	public Track(int ID, Town tS, Town tE, Distance distance) {
		super(tS, tE, distance);
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	

}
