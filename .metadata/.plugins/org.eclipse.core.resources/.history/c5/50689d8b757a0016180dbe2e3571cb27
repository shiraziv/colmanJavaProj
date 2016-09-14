package mazeGenerator;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @file  Generator.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class contains an implementation to one method that common to all Maze3d objects..
 * 
 * @date    02/09/2016
 */


public abstract class Generator implements Maze3dGenerator{

	
	@Override
	public abstract Maze3d generate(int high,int row,int column);
	@Override
	
	public String measureAlgorithmTime(int high,int row,int column ){ //calculate  the time that takes for the maze generate algorithm
		long beginTime;
		long endTime;
		long calculate;
		beginTime = System.currentTimeMillis();
		generate(high,row,column);
		endTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		calculate = endTime-beginTime;
		Date resultdate = new Date(endTime-beginTime);
		return sdf.format(resultdate);

	}
}
