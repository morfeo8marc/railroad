package com.irontrainsofthegenerality.railroad.graph;

/**
 * A weighatable represents something that has a weight
 * of whatever unit or type we want.
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 * @param <T> The type of the weight
 */
public interface Weightable<T> {
	/**
	 * 
	 * @return The amount of the weight
	 */
	T getValue();
	
	/**
	 *Set the amount of the weight.
	 * @return 
	 */
	void setValue(T weight);
	
	/**
	 * Checks if the weightable is negative
	 * @return true if the weight is negative
	 */
	boolean isNegative();
}
