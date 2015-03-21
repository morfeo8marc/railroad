package com.irontrainsofthegenerality.railroad.domain;

import com.irontrainsofthegenerality.railroad.graph.Weight;


/**
 * Distances is class that represents the separation
 * between two points.
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 * @param <T> The type of the distance.
 */
public class Distance extends Weight<Float> {

	/**
	 * The measurement unit of the {@link #distance}
	 */
	private String unit = "";
	
	/**
	 * Distance constructs a new instance of Distance
	 * with the given distance and his unit.
	 * 
	 * @param distance The distance to be assigned
	 * @param unit The unit to be assigned
	 * @throws @throws RuntimeException if the weight is null or negative
	 * @see Weight
	 */
	public Distance(Float distance, String unit) {
		super(distance);
		this.unit = unit;
	}

	@Override
	public Float getValue() {
		return this.weight;
	}

	@Override
	public boolean isNegative() {
		
		return this.weight.compareTo(0f) == -1;
	}

	@Override
	public int compareTo(Weight<Float> another) {
		return this.weight.compareTo(another.getValue());
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distance other = (Distance) obj;
		if (unit == null) {
			if (other.unit != null)
				return false;
		}
		else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	@Override
	public void setValue(Float weight) {
		this.weight = weight;
	}
	
}
