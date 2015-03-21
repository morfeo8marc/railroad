package com.irontrainsofthegenerality.railroad.builder;



import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.irontrainsofthegenerality.railroad.RailroadApplication;
import com.irontrainsofthegenerality.railroad.domain.Route;
import com.irontrainsofthegenerality.railroad.repository.TownRepositoryStub;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RailroadApplication.class})
public class RouteAlphabetParserTest {

	private RouteParser rp;
	
	@Autowired
	public TownRepositoryStub townRepositoryStub;
	
	@Before
	public void setUp(){
		rp = new RouteAlphabetParser(townRepositoryStub);
	}

	@Test(expected=RuntimeException.class)
	public void ParseANumberRouteShouldThrowException() {
		String graphString = "1-2";
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		
		rp.parse(ist);
	}
	
	@Test
	public void ParseEmptyRoute() {
		String graphString = "";
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		
		Route route = rp.parse(ist);
		
		assertThat("Route shoulb be empty", route.getNumberOTowns(), is(0));
	}
	
	
	@Test
	public void ParsValidRoute() {
		String graphString = "A-B-c-Z";
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		
		Route route = rp.parse(ist);
		
		assertThat("Route shoulb have 4 towns", route.getNumberOTowns(), is(4));
	}
	
}
