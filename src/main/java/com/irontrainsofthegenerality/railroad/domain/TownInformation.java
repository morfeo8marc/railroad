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
	
	
}