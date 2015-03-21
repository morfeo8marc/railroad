package com.irontrainsofthegenerality.railroad.graph;

/**
 * An Edge represent a path between to {@link Vertex}
 * This Edge must have in both ends a {@link Vertex}
 * 
 * @author morfeo8marc
 * @param <T> The Edge's content type
 * @param <VT> The Vertex's content type
 */
public class Edge<T extends Weight<?>, VT> implements Comparable<Edge<T,VT>> {
	
	/**
	 * The edge's start
	 */
	private VT vS;
	
	/**
	 * The edge's end
	 */
	private VT vE;
	
	/**
	 * The edge's weight

	 */
	private T weight;

	/**
	 * Construct an Edge with the given vS, vE and weight
	 * 
	 * @param vS The Starting Vertex
	 * @param vE The Ending Vertex
	 * @param weight The Weight of the Edge
	 * 
	 * @throws RuntimeException if {@link #vE}, {@link #vE} or {@link #weight} is null
	 */
	public Edge(VT vS, VT vE, T weight) {
		
		if (vS == null){
			throw new RuntimeException("Starting Vertex can no be null");
		}
		
		if (vE == null){
			throw new RuntimeException("Ending Vertex can no be null");
		}
		
		if (weight == null){
			throw new RuntimeException("Ending Vertex can no be null");
		}
		
		this.vS = vS;
		this.vE = vE;
		this.weight = weight;
	}

	public VT getvS() {
		return vS;
	}

	public void setvS(VT vS) {
		this.vS = vS;
	}

	public VT getvE() {
		return vE;
	}

	public void setvE(VT vE) {
		this.vE = vE;
	}

	public T getWeight() {
		return weight;
	}

	public void setWeight(T weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vE == null) ? 0 : vE.hashCode());
		result = prime * result + ((vS == null) ? 0 : vS.hashCode());
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
		Edge other = (Edge) obj;
		if (vE == null) {
			if (other.vE != null)
				return false;
		}
		else if (!vE.equals(other.vE))
			return false;
		if (vS == null) {
			if (other.vS != null)
				return false;
		}
		else if (!vS.equals(other.vS))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		}
		else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public int compareTo(Edge<T, VT> o) {
		return this.weight.compareTo(o.getWeight());
	}

	
	
	
}
