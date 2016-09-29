package search;
/**
 * @file  Searcher.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class represents an interface for searcher.
 * 
 * @date    02/09/2016
 */


public interface Searcher<T> {

	/**
	 * Used to find the best solution of a given problem.
	 *
	 * @param s - A searchable problem.
	 * @return Solution type - the sequence of the states needed to solve the problem.
	 */
	public Solution<T> search(Searchable<T> s);
	/**
	 * Used to determine the number of states the search algorithm evaluated.
	 * 
	 * @return The total number of States that were evaluated.
	 */
	public int getNumberOfNodesEvaluated();
	
}
