package com.irontrainsofthegenerality.railroad.builder;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irontrainsofthegenerality.railroad.domain.Route;
import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.repository.TownRepository;

/**
 * A Route Alphabet Parser try to parse route
 * that follows this rules:
 * <ul>
 * <li>One and only one letter of the alphabet [a-zA-Z]</li>
 * <li>followed by a dash "-"</li>
 * <li>One and only one letter of the alphabet [a-zA-Z]</li>
 * <li>A route can not have to same Towns in a row: s-d-A-A-f-d</li>
 * </ul>
 * 
 * Example of valid route:
 * <ul>
 * <li>A-B</li>
 * <li>a-b</li>
 * <li>a-B-a-b</li>
 * </ul>
 * @author morfeo8marc <github.com/morfeo8marc>
 * @see RouteParser
 */
@Component
public class RouteAlphabetParser implements RouteParser {
	private RuntimeException rexcNotValidFormat =  new RuntimeException("Not a valid route format");
	
	private TownRepository townRepository;
	private Pattern townsPattern = Pattern.compile("[a-zA-Z]{1}");
	
	@Autowired
	public RouteAlphabetParser(TownRepository townRepository) {
		this.townRepository = townRepository;
	}

	/**
	 * Parse try to parse the route following the specified format in {@link RouteAlphabetParser}
	 * @see RouteParser#parse(InputStream)
	 * @throws RuntimeException if the are at least not 2 Towns in the route 
	 *         or the the format of the stream is not used
	 * @throws RuntimeExceptionIf the format is not valid
	 */
	@Override
	public Route parse(InputStream is) {
		
		Town town;
		Route route = new Route();
		try(Scanner s = new Scanner(is).useDelimiter("-")) {
			while (s.hasNext()){
				try{
					String townName = s.next(townsPattern);
					
					town = townRepository.findTownByName(townName);
					
					route.addTown(town);
					
				}catch(Exception e){
					throw rexcNotValidFormat;
				}
			}
		}
		return route;
	}

}
