package search;

import java.util.ArrayList;
/**
 * @file  Solution.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class represents a generic solution
 * 
 * @date    02/09/2016
 */


public class Solution<T> {
	
	private State<T> startState;
	private State<T> goalState;
	private ArrayList<State<T>> solution;
	
	/**
	 * Default constructor.allocating the solution ArrayList
	 */
	public Solution(){
		this.solution= new ArrayList<State<T>>();
	}

	/**
	 *adding a State to the final Solution in a specific index.
	 *
	 * @param i	- int : an index to for the state to be inserted to.
	 * @param s - State.
	 */
	public void add(int i, State<T> s) {
		
		this.solution.add(i , s);
	}

	/**
	 * setters and getters for class variables.
	 */
	public State<T> getStartState() {
		return startState;
	}
	
	public void setStartState(State<T> startState) {
		this.startState = startState;
	}
	
	public State<T> getGoalState() {
		return goalState;
	}
	
	public void setGoalState(State<T> goalState) {
		this.goalState = goalState;
	}

	public ArrayList<State<T>> getSolution() {
		return solution;
	}

	public void setSolution(ArrayList<State<T>> solution) {
		this.solution = solution;
	}

	 /** override "toString" method from object.
	 * @return String
	 */
	@Override
	public String toString(){
		return this.solution.toString();
	}
	
	/**
	 * Print method for printing the solution.
	 */
	public void print(){
		for(int i=0; i< this.solution.size();i++)
			System.out.println(this.solution.get(i));
	}

	 /** add state to ArrayList.
	 * @param state<T>
	 */
	public void addStateToArrayList(State<T> s){
		solution.add(s);
	}
}