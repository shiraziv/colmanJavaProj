package search;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * @file  BFS.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class represents an example of searcher - Best-First-Search (BFS).
 * 
 * Advanteges: Breadth first search will never get trapped exploring the useless path forever.
 * If there is a solution, BFS will definitely find it out.
 * If there is more than one solution then BFS can find the minimal one that requires less number of steps.
 * 
 * @date    02/09/2016
 */

public class BFS<T> extends CommonSearcher<T> {
	
	/**
	 * This function is responsible to act the BFS algorithm itself
	 * @param s - the searchable object to search on.
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		
		addToOpenList(s.getStartState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();
		
		while(this.openList.size() > 0)
		{
			State<T> n = popOpenList();
			closedSet.add(n);
			
			// we are finished. get all the effective way.
			if(n.equals(s.getGoalState())) 
			{
				return backTrace(n , s.getStartState());
			}
			
			ArrayList<State<T>> successors = s.getAllPossibleStates(n);		
			
			
			for(State<T> state : successors) // find all possibles moves
			{
				if(!closedSetContain(closedSet,state) && !openListContain(state)) 
				{
					state.setCameFrom(n);
					setDeterminedCost(state);
					addToOpenList(state);	
				}
				else
				{
					
						// change to way that cost us less
						
						if(state.getCost() < (n.getCost() + s.getMoveCost())){		
							if(!openListContain(state)){
							
								setDeterminedCost(state);
								state.setCameFrom(n);
								addToOpenList(state);
							}
							else
							{
								adjustPriority(state);//searches if the given state is in the priorityQueue.
							}
					}
				}
			}
		}
		return null;// in case the world explodes
		
	}

	@Override
	protected void setDeterminedCost(State<T> s) {
		s.setCost(s.getCameFrom().getCost() + s.getCost());
		
	}
	
	

}
