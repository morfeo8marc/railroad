package com.irontrainsofthegenerality.railroad.domain;
/**
 * The related Information of a town.
 * This is the basic town information. Someone could
 * add more weightable attributes to be used in the route planner if needed
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public class TownInformation {
	
	private String name;
	private float latitude;
	private float longitud;
	
	public TownInformation(String name, float latitude, float longitud) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitud = longitud;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(latitude);
		result = prime * result + Float.floatToIntBits(longitud);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TownInformation other = (TownInformation) obj;
		if (Float.floatToIntBits(latitude) != Float.floatToIntBits(other.latitude))
			return false;
		if (Float.floatToIntBits(longitud) != Float.floatToIntBits(other.longitud))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}