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
			
			if(n.equals(s.getGoalState()))
			{
				return backTrace(n , s.getStartState());
			}
			
			ArrayList<State<T>> successors = s.getAllPossibleStates(n);		
			
			
			for(State<T> state : successors)
			{
				if(!closedSetContain(closedSet,state) && !openListContain(state)) 
				{
					state.setCameFrom(n);
					setDeterminedCost(state);
					addToOpenList(state);			
				}
				else
				{
					
					if(state.getCost() < (n.getCost() + s.getMoveCost())){		
						if(!openListContain(state)){
						
							setDeterminedCost(state);
							state.setCameFrom(n);
							addToOpenList(state);
						}
						else
						{
							adjustPriority(state);
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

	@Override
	public double changeCost(State<T> state, Searchable<T> search) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
