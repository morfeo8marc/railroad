package com.irontrainsofthegenerality.railroad.domain;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.irontrainsofthegenerality.railroad.domain.Distance;

public class DistanceTest {

	/**
	 * Given a negative Distance the constructor should  throw
	 * a {@link RuntimeException} 
	 * 
	 */
	@Test(expected=RuntimeException.class)
	public void ConstructorShouldThrowException() {
		Distance t = new Distance(-1f, "");
	}
	
	@Test
	public void CompareToShoudlReturnLessThan(){
		Distance d1 = new Distance(1f, "");
		Distance d2 = new Distance(2f, "");
		int c = d1.compareTo(d2);
		
		assertThat("d1 should be less than d2, therefore is must be -1 and not " + c, c, is(-1));
	}
	
	@Test
	public void CompareToShoudlReturnEqual(){
		Distance d1 = new Distance(1f, "");
		Distance d2 = new Distance(1f, "");
		int c = d1.compareTo(d2);
		
		assertThat("d1 should be less than d2, therefore is must be -1 and not " + c, c, is(0));
	}
	
	@Test
	public void CompareToShoudlReturnGreatherThan(){
		Distance d1 = new Distance(2f, "");
		Distance d2 = new Distance(1f, "");
		int c = d1.compareTo(d2);
		
		assertThat("d1 should be less than d2, therefore is must be -1 and not " + c, c, is(1));
	}
}
