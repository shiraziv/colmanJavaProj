package search;

import java.util.ArrayList;

/**
 * @file  DFS.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class represents an example of searcher - Depth First Search (DFS).
 * 
 * @date    02/09/2016
 */

public class DFS<T> extends CommonSearcher<T> {


		private ArrayList<State> closedList;
		/**
		 * This method send the searchable object to the DFS algorithm
		 * search and calculate the solution for it
		 * 
		 * @param s - the parameter for search.
		 * @return solution<T> for the algorithm solution search
		 */
	
		@Override
		public Solution<T> search(Searchable<T> s) 
		{
		closedList = new ArrayList<State>();
		this.addToOpenList(s.getStartState());
		State goal = s.getGoalState();
		State<T> temp;
		while(this.openList.size() > 0)
		{
			temp = this.popOpenList();
			if(!closedList.contains(temp))
			{
			closedList.add(temp);
			ArrayList<State<T>> successors = s.getAllPossibleStates(temp);
			for(State state : successors)
				{
					if(!closedList.contains(state))
					{
						if(state.equals(goal))
						{
							state.setCameFrom(temp);
							return this.backTrace(state, s.getStartState());
						}
						else if(!this.openList.contains(state))
						{
							state.setCameFrom(temp);
							this.addToOpenList(state);
						}
					}
				}
			}
		}
		return null;
	}
		@Override
		public double changeCost(State<T> state, Searchable<T> search) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		protected void setDeterminedCost(State<T> s) {
			// TODO Auto-generated method stub
			
		}
}