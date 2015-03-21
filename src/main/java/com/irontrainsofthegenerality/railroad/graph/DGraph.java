package com.irontrainsofthegenerality.railroad.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * A DGraph is a set of {@link Vertex} that are connected Trough {@link Edge}.
 * This DGraph is implemented using an AdjancecyMatrix. In this DGraph
 * all {@link Edge} are directed.
 * 
 * This implementation of a graph allow multiple edges from 1 {@link Edge} to another {@link Edge}
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public class DGraph<VT extends Vertex<?>, ET extends Edge<?,VT>> {

	/**
	 * The set of {@link Vertex}. It is a Set because a {@link Vertex} can exist only one
	 * time in a graph.
	 */
	protected Set<VT> vertices;
	/**
	 * The set of {@link Edge}. It is a Set because a {@link Edge} can exist only one time
	 * in a graph.
	 */
	protected Set<ET> edges;

	/**
	 * The graphs representation as an adjacencyListwhere each element
	 * stores a set of Edges.
	 */
	protected List<List<Set<ET>>> graph;

	/**
	 * Constructs an empty but initialized graph.
	 */
	public DGraph() {
		vertices = new HashSet<VT>();
		edges = new HashSet<ET>();
		graph = new ArrayList<List<Set<ET>>>();
	}

	/**
	 * Constructs a DGraph with the given {@link Edges}s.
	 * 
	 * @param vertices The set of {@link Vertex}. Can not be null
	 * @param edges The set of {@link Edges}. Can not be null
	 * @throws {@link IllegalArgumentException}if the edges set is null
	 */
	public DGraph(Set<VT> vertices, Set<ET> edges) {
		if (edges == null) {
			throw new IllegalArgumentException("Edges can not be null");
		}
		
		if (vertices == null) {
			throw new IllegalArgumentException("Vertices can not be null");
		}
		this.vertices = vertices;
		this.edges = new HashSet<ET>();
		
		graph = new ArrayList<List<Set<ET>>>(vertices.size());
		initializeMatrix(vertices.size());
		
		edges.forEach(e -> {
			addEdge(e);
		});
	}

	/**
	 * Initialize Matrix create all the rows and columns needed
	 * @param numVertices
	 */
	private void initializeMatrix(int numVertices) {
		IntStream.range(0, numVertices).forEach( r ->{
			
			List<Set<ET>> columns= new ArrayList<>(numVertices);
			
			IntStream.range(0, numVertices).forEach( c ->{
				columns.add(new HashSet<ET>());
			});
			
			graph.add(columns);	
		});
	}

	/**
	 * Add the provided edge to the graph.
	 * If the edge already exist in the graph it will not be added and a false is returned.
	 * 
	 * @param edge to be added
	 * @return true if the edge was added, if not then false is returned
	 * @throws IllegalArgumentException if edge is null
	 */
	private boolean addEdge(ET edge){
		if (edge == null){
			new IllegalArgumentException("The Edge to add can not be null");
		}
		boolean exists = edges.add(edge);
		
		int idS = edge.getvS().getId();
		int idE = edge.getvE().getId();
		
		graph.get(idS).get(idE).add(edge);;
		
		return exists;
	}

	public Set<VT> getVertices() {
		return vertices;
	}

	public void setVertices(Set<VT> vertices) {
		this.vertices = vertices;
	}

	public Set<ET> getEdges() {
		return edges;
	}

	public void setEdges(Set<ET> edges) {
		this.edges = edges;
	}
	
	

}
