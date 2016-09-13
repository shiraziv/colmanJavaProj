package search;


import java.util.ArrayList;

import mazeGenerator.*;

/**
 * @file  Maze3dSearchable.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class implements Searchable interface.
 * 
 * @date    02/09/2016
 */

public class Maze3dSearchable implements Searchable<Position> 
{
	private static final double STANDARD_MOVE_COST =10;
	private Maze3d maze;
	private State<Position> startState;
	private State<Position> goalState;
	
	/**
	 *Constractor.
	 *The constractor initialize the maze and the enter and exit.
	*/
	public Maze3dSearchable (Maze3d maze3d)
	{
		this.maze = maze3d;
		maze3d.randomEnterAndExit();
		this.startState = new State<Position>(maze3d.getStartPosition());
		this.goalState = new State<Position>(maze3d.getGoalPosition());
	}
	/** 
	 * Getting the initial state
	 */
	@Override
	public State getStartState() 
	{
		return startState;
	}
	
	 /** 
	 Getting the goal state
	*/
	@Override
	public State getGoalState() 
	{	
		return goalState;
	}

	/** 
	 * The method cheaking all the possible next states
	 * 
	 * @param state for the check
	 * @return list of the possible next states
	*/
	
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) 
	{
		Position pos = s.getState();
		ArrayList<Position> possiblePos = this.getAllPositions(pos);
		ArrayList<State<Position>> states = new ArrayList<State<Position>>();
		State<Position> tempState = null;
		for(Position p : possiblePos )
		{
			tempState = new State<Position>(p);
			tempState.setCost(getMoveCost());
			tempState.setCameFrom(s);
			states.add(tempState);
		}
		return states;
		
	}
	
	/** 
	 * The method checking what are all possible next Positions, includes wrong ones(walls and states which are out of the maze). 
	 *  
	 * @param Position for the check
	 * @return list of the possible next Positions
	*/
	public ArrayList<Position> getAllPositions(Position p)
	{
		//Getting all possibles states
		Position up = new Position(p);
		up.setX(up.getX()+1);
		Position down = new Position(p);
		down.setX(down.getX()-1);
		Position forward = new Position(p);
		forward.setY(forward.getY()+1);
		Position backward = new Position(p);
		backward.setY(backward.getY()-1);
		Position right = new Position(p);
		right.setZ(right.getZ()+1);
		Position left = new Position(p);
		left.setZ(left.getZ()-1);
				
		// Creates an array thats symbolize the all possibles moves 
		ArrayList<Position> allMoves= new ArrayList<Position>();
		// add to arraylist all the right next states.
		if (this.maze.cellFree(up))
		{
			allMoves.add(up);
		 }
		if (this.maze.cellFree(down))
		{
			allMoves.add(down);
		}
		if (this.maze.cellFree(forward))
		{
			allMoves.add(forward);
		}
		if (this.maze.cellFree(backward))
		{
			allMoves.add(backward);
		}
		if (this.maze.cellFree(right))
		{
			allMoves.add(right);
		}
		if (this.maze.cellFree(left))
		{
			allMoves.add(left);
		}
		return allMoves;	
		}
	@Override
	public double getMoveCost(){
		
		return STANDARD_MOVE_COST;
	}
}
