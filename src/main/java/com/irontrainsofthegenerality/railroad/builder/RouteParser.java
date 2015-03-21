package com.irontrainsofthegenerality.railroad.builder;

import java.io.InputStream;

import com.irontrainsofthegenerality.railroad.domain.Route;

/**
 * Parse an {@link InputStream} representing a route.
 * A route must have a least to towns to be a valid route
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public interface RouteParser {

	/**
	 * Parse a Route given an {@link InputStream}
	 * 
	 * @param is The stream for where the Route will be parsed
	 * @return The Route created from the {@link InputStream}
	 */
	Route parse(InputStream is);
	
}
