/**
 * 
 */
package com.irontrainsofthegenerality.railroad.builder;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.irontrainsofthegenerality.railroad.RailroadApplication;
import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.Track;

/**
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RailroadApplication.class})
public class RailRoadAlphabetParserTest {

	@Autowired
	public RailRoadParser rrp;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rrp = new RailRoadAlphabetParser();
	}

	/**
	 * Test method for {@link RailRoadAlphabetParser#parseTracks(java.io.InputStream)}.
	 */
	@Test
	public void parseTracksSouldReturnEmptyTracks() {
		String graphString = "";
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		Set<Track> parseTracks = rrp.parseTracks(ist);
		
		assertTracksSize(parseTracks, 0);
	}

	/**
	 * Test method for {@link RailRoadAlphabetParser#parseTracks(java.io.InputStream)}.
	 */
	@Test
	public void parseEndWithCommaTracksSouldReturnHaveOneTrack() {
		String graphString = "AB2,";
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		Set<Track> parseTracks = rrp.parseTracks(ist);
		
		assertTracksSize(parseTracks, 1);
		parseTracks.forEach(t-> checkTrackAB2(t));
	}

	/**
	 * Asserts that the size of the tracks is iqual to size
	 * @param tracks 
	 * @param size 
	 */
	private void assertTracksSize(Set<Track> tracks, int size) {
		assertThat("Should have "+size+" track and got "+tracks.size(), tracks, hasSize(size));
	}
	
	/**
	 * Test that repeated tracks are allowed
	 * Test method for {@link RailRoadAlphabetParser#parseTracks(java.io.InputStream)}.
	 */
	@Test
	public void parseTracksSouldAllowRepeatedTracks() {
		String graphString = "AB2,AB2";
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		Set<Track> parseTracks = rrp.parseTracks(ist);
		
		assertTracksSize(parseTracks, 2);
	}
	

	/**
	 * Test method for {@link RailRoadAlphabetParser#parseTracks(java.io.InputStream)}.
	 */
	@Test
	public void parseEndWithOutCommaTracksSouldReturnHaveOneTrack() {
		String graphString = "AB2";
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		Set<Track> parseTracks = rrp.parseTracks(ist);
		
		assertTracksSize(parseTracks, 1);
		parseTracks.forEach(t-> checkTrackAB2(t));
	}
	
	/**
	 * Test method for {@link RailRoadAlphabetParser#parseTracks(java.io.InputStream)}.
	 */
	@Test
	public void parseShouldHaveTwoTracks(){;
		String graphString = "AB2,AB3";
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		Set<Track> parseTracks = rrp.parseTracks(ist);
		
		assertTracksSize(parseTracks, 2);
		
		Object[] tracks = parseTracks.toArray();
		Track track1 = ((Track) tracks[0]);
		Track track2 = ((Track) tracks[1]);
		Town town1 = track1.getvS();
		Town town2 = track2.getvS();
		assertThat("The town A should be the same", town1, is(equalTo(town2)));
		
		town1 = ((Track) tracks[0]).getvE();
		town2 = ((Track) tracks[1]).getvE();
		assertThat("The town B should be the same", town1, is(equalTo(town2)));
	}
	
	@Test
	public void parseShouldHaveNineTracks(){
		String graphString = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		Set<Track> parseTracks = rrp.parseTracks(ist);
		
		assertTracksSize(parseTracks, 9);
		assertTownsExistence(parseTracks, "A", 3l);
		assertTownsExistence(parseTracks, "B", 3l);
		assertTownsExistence(parseTracks, "C", 4l);
		assertTownsExistence(parseTracks, "D", 4l);
		assertTownsExistence(parseTracks, "E", 4l);
		assertTownsExistence(parseTracks, "F", 0l);
	}
	
	/**
	 * Asset Towns Existence checks that the town identified with the name townName
	 * appears exactly numTownAppearance times.
	 * @param parseTracks
	 * @param townName
	 * @param numTownAppearance
	 */
	private void assertTownsExistence(Set<Track> parseTracks, String townName, long numTownAppearance) {
		long count = parseTracks.stream().filter(t -> (t.getvS().getName().equals(townName) || t.getvE().getName().equals(townName)))
		                    .count();
		
		assertThat("Town " + townName + " should exist " + numTownAppearance + " times", count, is(numTownAppearance));
		
	}

	private void checkTrackAB2(Track t) {
		String startTown = t.getvS().getName();
		assertThat("Start town should be A and got " + startTown, startTown, is(equalTo("A")));
		
		String endTown = t.getvE().getName();
		assertThat("End town should be B and got " + endTown, endTown, is(equalTo("B")));
		
		Float distance = t.getWeight().getValue();
		assertThat("Distance should be 2 and got" + distance, distance, is(equalTo(2f)));
	}
}
