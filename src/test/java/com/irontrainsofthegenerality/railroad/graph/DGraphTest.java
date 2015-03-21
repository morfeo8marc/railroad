
package com.irontrainsofthegenerality.railroad.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.HashSet;

import org.junit.Test;

import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.Track;
import com.irontrainsofthegenerality.railroad.graph.DGraph;

/**
 * @author morfeo8marc <github.com/morfeo8marc>
 *
 */
public class DGraphTest {
	Exception ex;

	@Test(expected=RuntimeException.class)
	public void GivenNullVerticesShouldReturnRunTimeException() {
		new DGraph<Town, Track>(null, null);
	}
	
	@Test(expected=RuntimeException.class)
	public void GivenNullEdgesShouldReturnRunTimeException() {
		new DGraph<Town, Track>(new HashSet<Town>(), null);
	}
	
	@Test(expected=RuntimeException.class)
	public void ConstructorShouldNotReturnException() {
		try{
			new DGraph<Town, Track>(new HashSet<Town>(), new HashSet<Track>());
		} catch (Exception e){
			ex = e;
		}
		
		assertThat("Now exception should be returned, and got" + ex.getMessage(), ex, is(nullValue()));
		
	}

}
