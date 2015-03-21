package com.irontrainsofthegenerality.railroad.builder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.irontrainsofthegenerality.railroad.domain.Distance;
import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.TownInformation;
import com.irontrainsofthegenerality.railroad.domain.Track;

@Component
public class RailRoadAlphabetParser implements RailRoadParser {
	private RuntimeException reNotValidFormat =  new RuntimeException("Not a valid format");

	/**
	 * Parse Tracks parse the input stream following the rules that follow,
	 * and returns a set of Tracks. If a track appears more than one time
	 * it will be just omitted.
	 * 
	 * The rules that the InputStream must follow are:
	 * <ul>
	 * <li>A Track between two towns (A to B) with distance 
	 * of 5 is represented	as AB5.</li>
	 * <li>A graph is represented by a comma separated routes</li>
	 * </ul>
	 * Example of a graph:
	 * AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7
	 * 
	 * @param The input Stream to read from.
	 * @return A set with the parsed tracks.
	 * 
	 * @throw RuntimeException if there are not to letters in a route at the beginning.
	 * @throw RuntimeException if there is not one or more numbers representing the distance 
	 * 		  after the 2 characters representing each of the towns.
	 * TODO improve how the scanner reads the parts.
	 */
	@Override
	public Set<Track> parseTracks(InputStream is) {
		
		Map<String, Town> towns = new HashMap<String, Town>();
		Set<Track> tracks = new HashSet<Track>();
		
		Pattern townsPattern = Pattern.compile("[a-zA-Z]{2}\\d+");
		String townsStr;
		String token;
		
		int distance;
		Integer townID = 0;// An incremental identifier for each town
		Track t;
		
		try(Scanner s = new Scanner(is).useDelimiter(",")) {
			while (s.hasNext()){
				try{
					token = s.next(townsPattern);
					townsStr = token.substring(0, 2);
					distance = Integer.valueOf(token.substring(2));
					
				}catch(Exception e){
					throw reNotValidFormat;
				}
				t = buildTrack(townsStr, distance, townID, towns);
				tracks.add(t);
			}
		}
		return tracks;
	}

	/**
	 * Build Track construct a track with the provided information.
	 * <ul>
	 * <li>The first character of the string is the starting Town.</li>
	 * <li>The second letter is the ending Town</li>
	 * <li>The distance is the distance between the two towns</li>
	 * <li>If the Town already exist in the {@link #towns} {@link Set} then no new town is created</li>
	 * <li>The town ID is the townID for the first town<li>
	 * <li>The town ID is the townID+1 for the second town<li>
	 * <ul>
	 * @param towns A 2 chars string representing the towns. 
	 * 		        Each char is the name of the town
	 * @param distance The distance between the 2 towns.
	 * @param townId the last assigned town id. It is incremented by 2 for each call
	 * @param townSMap The created towns so far
	 * @throws IllegalArgumentException if the towns length is not 2 or is null
	 * @TODO Use locale for the metric units.
	 * @return The Builded track
	 */
	private Track buildTrack(String towns, int distance, Integer townID, Map<String, Town> townSMap) {
		
		if (towns == null || towns.length() != 2){
			throw new IllegalArgumentException("Towns must have 2 letters, and got "+ towns);
		}
		
		Town startTown, endTown;
		Track tack;
		Distance d;
		
		startTown = getTown(towns.substring(0, 1), townID, townSMap);
		endTown = getTown(towns.substring(1, 2), townID, townSMap);
		
		d = new Distance((float)distance, "km");
		
		tack = new Track(startTown, endTown, d);
		
		return tack;
	}
	
	/**
	 * Get Town checks if the Town exists by comparing the names.
	 * OtherWise it creates the Town, adds it to the {@link #towns} {@link Map}
	 * @param townName The Towns Name
	 * @param townID The Town's id. It is incremented if the town not exists
	 * @param townSMap The created towns so far
	 * @return The Town
	 */
	private Town getTown(String townName, Integer townID, Map<String, Town> townSMap){
		Town t = townSMap.get(townName);
		
		if (t == null){
			TownInformation townInformation = new TownInformation(townName, 0f, 0f);
			t =  new Town (townInformation, townID);
			townID++;
			townSMap.put(townName, t);
		}
		
		return t;
	}

}
