package search;


import java.util.Comparator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @file  CommonSearcher.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class represents an abstract class that contain the common details of all the searchers
 * 
 * @date    02/09/2016
 */


public abstract class CommonSearcher<T> implements Searcher<T> {


	protected PriorityQueue<State<T>> openList;
	protected int evaluatedNodes;
	
	/**
	 * Default Constractor, using Comparator to compare two states to decide order in priorityQueue.
	 * initialize evaluatedNodes to 0. 
	 */
	public CommonSearcher(){
		openList = new PriorityQueue<State<T>>(new Comparator<State<T>>(){
		
			@Override
			public int compare(State<T> s1, State<T> s2) {
				
				return (int)((s1.getCost() - s2.getCost()));
			}
			
		});
		
		this.evaluatedNodes = 0; 
	}
	
	
	 
	 /**
	  * Pop the priority list and increase the counter
	  * @return the popped state
	  */
	protected State<T> popOpenList(){
		evaluatedNodes++; //updates nodes according to steps
		return openList.poll();
	}
	 /**
	  * Add new state to the priority list
	  * @param s - the state to push
	  */
	protected void addToOpenList(State<T> s){
		
		this.openList.add(s);
	}
	
	/**
	 * The method searches if the given state is in the priorityQueue.update the priority state.
	 * @param s - A State to be searched in the queue.
	 */
	public void adjustPriority(State<T> s) {
		if (openListContain(s)) {
			Iterator<State<T>> itr = openList.iterator();
			State<T> temp = null;
			while (itr.hasNext()) {
				temp = itr.next();
				if (s.getState().equals(temp.getState())) {
					if (s.getCost() < temp.getCost()) {
						itr.remove();
						addToOpenList(s);
					}
					return;
				}
			}
		}
		addToOpenList(s);
	}
			
	 /**
	  * This function calculates the path from goal state to start state
	  * @param goalState   - the goal state
	  * @param startState  - the start state
	  * @return the path between them (solution object)
	  */
	protected Solution<T> backTrace(State<T> goalState, State<T> startState) {
		Solution<T> solution = new Solution<T>();
		State<T> currentState = goalState;
		
		//set start & goal state.
		solution.setStartState(startState);
		solution.setGoalState(goalState);

		while (currentState.getCameFrom() != null) {              // insert the path into array list  // 
			solution.addStateToArrayList(currentState);
			currentState = currentState.getCameFrom();
		}
		
		solution.addStateToArrayList(currentState);    		//add the last state also // 

		return solution;
	}
	
	/**
	 * searches the openlist for the given state.
	 * @param state - State - the checked state.
	 * @return - boolean - true if the given state exists within the openList.
	 */
	protected boolean openListContain(State<T> state) {
		for(State<T> currentState : this.openList)
		{
			if(currentState.equals(state))
			{
				return true;
			}	
		}
		return false;
		
	}
	
	/**
	 * Searches for state inside the closedSet.
	 * @param closedSet - HashSet.
	 * @param state - State
	 * @return true if found.
	 */
	protected boolean closedSetContain(HashSet<State<T>> closedSet, State<T> state) {
		
		for(State<T> currentState : closedSet)
		{
			if(currentState.equals(state))
			{
				return true;
			}	
		}
		return false;
	}
	
	
	/**
	 * abstract method, solving any Searchable problem and return the solution.
	 */
	@Override
	public abstract Solution<T> search(Searchable<T> s);
	
	/**
	 * setting the given state.
	 * @param s - State. 
	 */
	protected abstract void setDeterminedCost(State<T> s);

	@Override
	public int getNumberOfNodesEvaluated() {
		return this.evaluatedNodes;
	}
}