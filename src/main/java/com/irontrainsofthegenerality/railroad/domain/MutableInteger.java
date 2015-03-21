package com.irontrainsofthegenerality.railroad.domain;

/**
 * A wrapper for a integer.
 * Allowing it to be muttated
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public class MutableInteger {
	private Integer i;

	public MutableInteger(Integer i) {
		super();
		this.i = i;
	}

	public Integer getInteger() {
		return i;
	}

	public void setInteger(Integer i) {
		this.i = i;
	}
	
	
}
