package com.irontrainsofthegenerality.railroad.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.irontrainsofthegenerality.railroad.domain.Town;

/**
 * A town repository implemented with a Map as the underlying
 * dataStore
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
@Component
public class TownRepository {

	/**
	 * An in memory datastore indexed by town name
	 */
	public Map<String, Town> dataStore;
	
	public TownRepository(){
		dataStore = new HashMap<String, Town>();
	}
	
	/**
	 * Finds the Town by name
	 * 
	 * @param townName
	 * @return A town if it exist, or null if it does not exist
	 */
	public Town findTownByName(String townName){
		return dataStore.get(townName);
	}
	
	/**
	 * Saves a town ind
	 * @param town The town to be saved
	 * @return The saved town
	 */
	public Town save(Town town){
		return dataStore.put(town.getName(), town);
	}
}
