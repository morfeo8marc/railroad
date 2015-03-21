package com.irontrainsofthegenerality.railroad;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.irontrainsofthegenerality.railroad.builder.RailRoadBuilder;
import com.irontrainsofthegenerality.railroad.builder.RouteParser;
import com.irontrainsofthegenerality.railroad.domain.Journey;
import com.irontrainsofthegenerality.railroad.domain.Route;
import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.Track;
import com.irontrainsofthegenerality.railroad.graph.DGraph;
import com.irontrainsofthegenerality.railroad.planner.RoutePlanner;
import com.irontrainsofthegenerality.railroad.repository.TownRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RailroadApplication.class)
public class RailroadApplicationTests {

	private DGraph<Town, Track> railRoadgraph ;
	@Autowired
	private RailRoadBuilder rrb;
	@Autowired
	@Qualifier("DirectShortestRoutePlanner")
	private RoutePlanner routePlanner;
	@Autowired
	private RouteParser rp;
	@Autowired
	private TownRepository townRepository;
	
    public static Collection<TestResultsWrapper> tests = Arrays.asList(new TestResultsWrapper("A-B-C", "9"),
    		new TestResultsWrapper("A-D", "5"),
    		new TestResultsWrapper("A-D-C", "13"),
    		new TestResultsWrapper("A-E-B-C-D", "22"),
    		new TestResultsWrapper("A-E-D", "NO SUCH ROUTE"));
	
    @Before
	public void RailroadApplicationTests(){
		String graphString = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
		
		InputStream ist = new ByteArrayInputStream(graphString.getBytes());
		
		railRoadgraph = rrb.buildGraph(ist);
		
		Set<Town> towns = railRoadgraph.getVertices();
		towns.forEach(t->{
			townRepository.save(t);
		});
	}
	
	@Test
	public void ExerciceBaseCases() {
		tests.forEach(trw->{
			String routeStr = trw.getRoute();
			Route route = rp.parse(new ByteArrayInputStream(routeStr.getBytes()));
			
			Journey journey = routePlanner.planRoute(route, railRoadgraph);
			
			assertThat("Route result for" +  route , journey.getResult(), is(equalTo(trw.getResult())));
		});
		
	}
	
	private static class TestResultsWrapper {
		private String route;
		private String result;
		
		public TestResultsWrapper(String route, String result) {
			super();
			this.route = route;
			this.result = result;
		}

		public String getRoute() {
			return route;
		}

		public String getResult() {
			return result;
		}
		
		
	}

}
