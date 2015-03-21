package com.irontrainsofthegenerality.railroad.domain;

import com.irontrainsofthegenerality.railroad.graph.Vertex;

/**
 * A Town is human representation of a Vertex
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public class Town extends Vertex<TownInformation> {

	/**
	 * 
	 * @param content The towns information that can be used for many different purposes.
	 * @param id
	 */
	public Town(TownInformation content, int id) {
		super(content, id);
	}

	public String getName() {
		return getContent().getName();
	}

	public void setName(String name) {
		getContent().setName(name);
	}

	public float getLatitude() {
		return getContent().getLatitude();
	}

	public void setLatitude(float latitude) {
		getContent().setLatitude(latitude);
	}

	public float getLongitud() {
		return getContent().getLongitud();
	}

	public void setLongitud(float longitud) {
		getContent().setLongitud(longitud);
	}
}