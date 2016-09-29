package mazeGenerator;

import java.util.Random;


import search.*;

/**
 * @file  Position.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class represents a position in 3d maze.
 * 
 * @date    02/09/2016
 */

public class Position {

	private int z;
	private int y;
	private int x;
	

	/**
	 * Constractor.
	 * @param x - the x coordinate of columns' maze.
	 * @param y - the y coordinate of rows' maze.
	 * @param z - the z coordinate of highs' maze
	 */
	public Position(int z,int y,int x){
		this.z=z;
		this.y=y;
		this.x=x;
		
	}
	
	
	/**
	 * Copy constractor.
	 * @param x - the x coordinate of columns' maze.
	 * @param y - the y coordinate of columns' maze.
	 * @param z - the z coordinate of columns' maze
	 */	
	public Position(Position p){
		this.x=p.getX();
		this.y=p.getY();
		this.z=p.getZ();
			
	}
	
	/**
	 * prints the Position with uniq format: {x,y,z}.
	 */
	public void print(){						
		
		System.out.println("{"+this.z+","+this.x+","+this.y+"}");
	}
	@Override
	public boolean equals(Object obj){	
		
		Position pos = (Position)obj;
		if(this.getX() == pos.getX() && this.getY() == pos.getY() && this.getZ() == pos.getZ() )
		{
			return true;
		}
		return false;
	}

	/**
	 * The method forward to the next clear move.
	 *  
	 * @param column - the x coordinate of columns' maze.
	 * @param i  - the possible move index.
	 * @return Position - the clear cell according to the index.
	 */
	public Position move(int i) {			
		if(i == 0){
			this.x++;
		}
		if(i == 1){
			this.x--;
		}
		if(i == 2){
			this.y++;
		}
		if(i == 3){
			this.y--;
		}
		if(i == 4){
			this.z++;
		}
		if(i == 5){
			this.z--;
		}
		
		Position newPos = new Position(z,y,x);
		return newPos;
	}
	@Override
	public String toString() {
		String output;
			
		output = "{"+this.getZ()+","+this.getY()+","+this.getX()+"}";
		return output ;
		}
	 /**
	 * @return a State- with uniq format: x,y,z. and update the cost to 10.
	 */	

	//getters and setters
	/**
	 * Gets the Z coordinate of the position.
	 * 
	 * @return The value of the Z coordinate.
	 */
	public int getZ() {
		return z;
	}
	
	/**
	 * Sets the Z coordinate of the position.
	 * 
	 * @param z	The desired X coordinate.
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	/**
	 * Gets the Y coordinate of the position.
	 * 
	 * @return The value of the Y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the Y coordinate of the position.
	 * 
	 * @param y	The desired X coordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Gets the X coordinate of the position.
	 * 
	 * @return The value of the X coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the X coordinate of the position.
	 * 
	 * @param x	The desired X coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
}