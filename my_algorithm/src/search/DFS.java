package search;

import java.awt.List;
import java.util.ArrayList;


/**
 * @file  DFS.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class represents an example of searcher - Depth First Search (DFS).
 * 
 * Advanteges:  Memory requirement is Linear WRT Nodes.
 * Less time and space complexity rather than BFS.
 * Solution can be found out by without much more search.
 * 
 * @date    02/09/2016
 */
public class DFS<T> extends CommonSearcher<T> {

private ArrayList<State> visitedStates = new ArrayList<State>();
private Solution <T> solution;


@Override
public Solution<T> search(Searchable<T> s) {
	dfs (s, s.getStartState());
	return solution;
}


public void dfs (Searchable<T> s, State<T> state)
{
	if (state.equals(s.getGoalState()))
	{
		this.solution = backTrace(state, s.getStartState());
		return;
	}

	visitedStates.add(state);
	ArrayList<State<T>> neighbors = s.getAllPossibleStates(state);

	for (State<T> neighbor : neighbors)
	{

		if (!visitedStates.contains(neighbor))
		{	
			neighbor.setCameFrom(state);
			setDeterminedCost(neighbor);
			this.evaluatedNodes++;
			dfs(s, neighbor);	

		} 			
	}
	return;

}

		@Override
		protected void setDeterminedCost(State<T> s) {
			s.setCost(s.getCameFrom().getCost() + s.getCost());
			
		}
}