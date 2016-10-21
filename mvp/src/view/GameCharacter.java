package view;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import mazeGenerator.*;

/**
 * @file CommonCommand.java
 * 
 * @author Shira Ziv
 * 
 * @description This class represents a charcter in a Maze3d game.
 * 				
 * @date    02/09/2016
 * */
public class GameCharacter {
	private Position pos;
	private Image img;
	
	public GameCharacter() {
		this.img = new Image(null, "lib/images/pokemon.jpg");
	}

	public Image getImage(){
		return this.img;
	}
	
	public Position getPos() {
		return this.pos;
	}


	public void setPos(Position pos) {
		this.pos = pos;
	}

	// Draws a game character
	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width,
				img.getBounds().height, cellWidth * pos.getX(),
				cellHeight * pos.getY(), cellWidth, cellHeight);
	}
	
}