package com.irontrainsofthegenerality.railroad.builder;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class PatternTester {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String pattern = "[a-zA-Z]{2}";
		String s = "A";
		assertFalse(s.matches(pattern));
		s = "AB";
		assertTrue(s.matches(pattern));
		
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(s);
		
		assertTrue(matcher.matches());
	}

}
