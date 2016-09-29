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
	private static final double DIAGONAL_MOVE_COST =10;
	private Maze3d maze;
	private State<Position> startState;
	private State<Position> goalState;
	private double getCost= 0;
	
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
		String[] moves = maze.getPossibleMoves(pos);
		ArrayList<State<Position>> possibleStates = new ArrayList<State<Position>>();
		State<Position> tempState = null;
		
			for(int i=0; i<moves.length; i++)	
			{
				switch (i) {
				case 4:
					tempState = new State<Position>(new Position(s.getState().getZ()-1, s.getState().getY(), s.getState().getX()));
					tempState.setCost(DIAGONAL_MOVE_COST);
					
					break;
				case 5:
					tempState = new State<Position>(new Position(s.getState().getZ()+1, s.getState().getY(), s.getState().getX()));
					tempState.setCost(DIAGONAL_MOVE_COST);
					
					break;
				case 2:
					tempState = new State<Position>(new Position(s.getState().getZ(), s.getState().getY()+1, s.getState().getX()));
					tempState.setCost(STANDARD_MOVE_COST);
					
					break;
				case 3:
					tempState = new State<Position>(new Position(s.getState().getZ(), s.getState().getY()-1, s.getState().getX()));
					tempState.setCost(STANDARD_MOVE_COST);
					
					break;
				case 0:
					tempState = new State<Position>(new Position(s.getState().getZ(), s.getState().getY(), s.getState().getX()+1));
					tempState.setCost(STANDARD_MOVE_COST);
					
					break;
				case 1:
					tempState = new State<Position>(new Position(s.getState().getZ(), s.getState().getY(), s.getState().getX()-1));
					tempState.setCost(STANDARD_MOVE_COST);
					
					break;
				default:
					break;
				}
				tempState.setCameFrom(s);	
				// add to arraylist all the right next states.
				if(this.maze.cellFree(tempState.getState())){
					possibleStates.add(tempState);
				}
			}
			return possibleStates;

	}

	@Override
	public double getMoveCost(){
		
		return this.getCost;
	}
}
