package search;
import mazeGenerator.*;


/**
 * @file  State.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class represents a single state of the problem - using template
 * 
 * @date    02/09/2016
 */


public class State<T> {
	private T state;
	private double cost;
	private State<T> cameFrom;
	
	/**
	 * A non - default constructor. 
	 * @param state - A String which describes the State
	 */
	
	public State(T state){
		this.state = state;
		this.cost = 0;
		this.cameFrom = null;
		
	}
	
	@Override
	public String toString()
	{
		
		return this.getState().toString();
		
	}
	@Override
	public boolean equals(Object s){
		return state.equals(((State<T>) s).getState());
	}


	/**
	 * Sets the came-from data member.
	 *
	 * @param state value to set
	 */
	public void setCameFrom(State<T> state) 
	{
		this.cameFrom = state;
	}
	/**
	 * Gets the came from.
	 *
	 * @return the came-from data member
	 */
	public State<T> getCameFrom() 
	{
		return this.cameFrom;
	}

	/**
	 * Sets the state.
	 *
	 * @param state.
	 */

	public void setState(T state) 
	{
		this.state = state;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state data member.
	 */

	public T getState() 
	{
		return state;
	}
	/**
	 * Sets the cost data member.
	 *
	 * @param cost - value to set
	 */
	public void setCost(double cost) 
	{
		this.cost = cost;
	}
	/**
	 * Gets the cost.
	 *
	 * @return the cost data member.
	 */
	public double getCost() 
	{
		return cost;
	}


	
}