
package view;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import mazeGenerator.*;

public class GameCharacter{
	private Image img;
	private Position pos;
	
	//private Image goalImg;
	//private Position posT;


	public GameCharacter() {
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setPosition(Position pos) {
		this.pos = pos;
	}
	
	public void setPosition2(int a, int b, int c) {
		this.pos.setX(c);
		this.pos.setY(b);
		this.pos.setZ(a);
	}
	
	public Position getPosition(){
		return pos;
	}
	
//	public void setTweetyPosition(Position pos) {
//		this.posT = pos;
//	}
//	public Position getTweetyPosition(){
//		return posT;
//	}
	
	public boolean win() 
	{
		if(!getPosition().equals(pos))
		{
			return false;
			
		}
		return true;
	}

	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 
				cellWidth * pos.getX(), cellHeight * pos.getY(), cellWidth, cellHeight);
	}
	
//	public void drawTweety(int cellWidth, int cellHeight, GC gc) {
//		gc.drawImage(goalImg, 0, 0, goalImg.getBounds().width, goalImg.getBounds().height, 
//				cellWidth * posT.x, cellHeight * posT.y, cellWidth, cellHeight);
//	}

	public void moveUp(){
		Position newP = new Position(pos.getZ(), pos.getY()-1, pos.getX());

	}

	public void moveDown(){
		Position newP = new Position(pos.getZ(), pos.getY()+1, pos.getX());
	}

	public void moveLeft(){
		Position newP = new Position(pos.getZ(), pos.getY(), pos.getX()-1);
	}

	public void moveRight(){
		Position newP = new Position(pos.getZ(), pos.getY(), pos.getX()+1);
	}

	public void moveFloorUp(){
		Position newP = new Position(pos.getZ()-1, pos.getY(), pos.getX());
	}

	public void moveFloorDown(){
		Position newP = new Position(pos.getZ()+1, pos.getY(), pos.getX());
	}
}