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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Weight other = (Weight) obj;
		if (weight == null) {
			if (other.weight != null)
				return false;
		}
		else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
	
}
