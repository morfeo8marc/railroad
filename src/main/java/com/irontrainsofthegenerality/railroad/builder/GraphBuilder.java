package com.irontrainsofthegenerality.railroad.builder;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.irontrainsofthegenerality.railroad.graph.DGraph;

@Component
public interface GraphBuilder {
	/**
	 * Build the graph given an InputStream
	 * 
	 * @param is The representation of the graph
	 */
	DGraph<?, ?> buildGraph(InputStream is);
}
