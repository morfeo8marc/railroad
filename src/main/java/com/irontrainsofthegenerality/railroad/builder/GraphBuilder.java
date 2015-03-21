package com.irontrainsofthegenerality.railroad.builder;

import java.io.InputStream;

import com.irontrainsofthegenerality.railroad.graph.DGraph;

public interface GraphBuilder {
	/**
	 * Build the graph given an InputStream
	 * 
	 * @param is The representation of the graph
	 */
	DGraph<?, ?> buildGraph(InputStream is);
}
