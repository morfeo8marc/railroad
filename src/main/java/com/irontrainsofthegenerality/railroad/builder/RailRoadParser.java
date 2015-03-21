package com.irontrainsofthegenerality.railroad.builder;

import java.io.InputStream;
import java.util.Set;

import com.irontrainsofthegenerality.railroad.domain.Track;

/**
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public interface RailRoadParser {
	/**
	 * Parse Tracks parse the input stream following the rules that follow,
	 * and returns a set of Tracks. If a track appears more than one time
	 * it will be just omitted.
	 */
	public Set<Track> parseTracks(InputStream is);
}
