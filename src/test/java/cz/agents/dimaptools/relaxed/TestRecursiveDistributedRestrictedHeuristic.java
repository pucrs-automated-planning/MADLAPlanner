package cz.agents.dimaptools.relaxed;

import org.junit.Test;

import cz.agents.alite.configurator.MapConfiguration;
import cz.agents.dimaptools.DIMAPWorldInterface;
import cz.agents.dimaptools.heuristic.relaxed.RecursiveDistributedRelaxationRestrictedReplyHeuristic;
import cz.agents.dimaptools.heuristic.relaxed.RecursiveDistributedRelaxationRestrictedRequestHeuristic;
import cz.agents.dimaptools.heuristic.relaxed.evaluator.AddEvaluator;
import cz.agents.dimaptools.search.AbstractDistributedAStarTest;
import cz.agents.dimaptools.search.DistributedBestFirstSearch;

public class TestRecursiveDistributedRestrictedHeuristic extends AbstractDistributedAStarTest {

	@Test
	public void test() {
//		testProblem("truck-crane-a2");
//		testProblem("truck-crane-factory-a3");
//		testProblem("logistics-a2");
//		testProblem("logistics-a4");
//		testProblem("deconfliction-a4");
//		testProblem("rovers-a4");
//		testProblem("sokoban-a1");
//		testProblem("sokoban-a2");
	}

	@Override
	public void runSearch(DIMAPWorldInterface world){
		DistributedBestFirstSearch search = new DistributedBestFirstSearch(world);
//		AStar search = new AStar(problem);

		RecursiveDistributedRelaxationRestrictedRequestHeuristic req = new RecursiveDistributedRelaxationRestrictedRequestHeuristic(world, new AddEvaluator(world.getProblem()),60);
		RecursiveDistributedRelaxationRestrictedReplyHeuristic rep = new RecursiveDistributedRelaxationRestrictedReplyHeuristic(world, new AddEvaluator(world.getProblem()),req.getRequestProtocol());
		req.setReplyProtocol(rep.getReplyProtocol());
		

		search.plan(new MapConfiguration("heuristic",req,"requestHeuristic",rep), searchCallback);
	}

}