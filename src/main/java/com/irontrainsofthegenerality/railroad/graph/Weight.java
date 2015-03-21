package com.irontrainsofthegenerality.railroad.graph;


/**
 * A class that represent a Weight.
 * A weight can not be negative nor null
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 * @param <T> The type of the weight
 */
public abstract class Weight<T extends Comparable<? super T>> implements Comparable<Weight<T>>, Weightable<T> {

	/**
	 * The amount of the weight
	 */
	protected T weight;
	
	/**
	 * Constructs a Weight
	 * @param weight The weight to be set
	 * @throws RuntimeException if the weight is null or {@link #isNegative()}
	 */
	public Weight(T weight){
		if (weight == null){
			throw new RuntimeException("The weight can not be null");
		}
		this.weight = weight;
		
		if (isNegative()){
			throw new RuntimeException("The weight can not be negative");
		}
	}
	
}
