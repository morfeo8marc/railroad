package com.irontrainsofthegenerality.railroad.builder;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.Track;
import com.irontrainsofthegenerality.railroad.graph.DGraph;

/**
 * Build a RailRoad Graph. Each {@link Town} is connected with each other 
 * by a {@link Track}.
 * In this RailRoad Graph it can only exist one Town with the same name.
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
@Component
public class RailRoadBuilder implements GraphBuilder {
	
    private RailRoadParser pailRoadParser;
	
	@Autowired
	public RailRoadBuilder(RailRoadParser pailRoadParser){
		this.pailRoadParser = pailRoadParser;
	}
	
	/**
	 * Builds a {@link DGraph} given the {@link InputStream}.
	 * @throws IllegalArgumentException If {@link InputStream} is null
	 */
	@Override
	public DGraph<Town, Track> buildGraph(InputStream is) {
		if (is == null){
			throw new IllegalArgumentException("Build graph need a not null InputSreamd");
		}
		
		DGraph<Town, Track> railRoad;
		
		Set<Track> tracks = pailRoadParser.parseTracks(is);
		Set<Town> towns = getTowns(tracks);
		railRoad = new DGraph<Town, Track>(towns, tracks);
		return railRoad;
	}
	
	/**
	 * Get Towns returns a {@link Set} of {@link Town} that are exist in 
	 * then given {@link Track}s
	 * @param tracks The tracks from where the towns will be extracted
	 * @return The {@link Set} of {@link Town}s
	 */
	private Set<Town> getTowns(Set<Track> tracks){
		Set<Town> towns = new HashSet<Town>();
		tracks.forEach(t -> {
			towns.add(t.getvS());
			towns.add(t.getvE());
		});
		return towns;
		
	}

	

}
