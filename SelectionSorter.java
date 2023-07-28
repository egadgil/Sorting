package edu.iastate.cs228.hw1;

/**
 * This class implements selection sort.
 * @author Esha Gadgil
 */
public class SelectionSorter extends AbstractSorter {
	// Other private instance variables if needed

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor, and also
	 * sets the instance variable algorithm in the superclass.
	 *
	 * @param pts input array of points
	 */
	public SelectionSorter(Point[] pts) {
		super(pts);
		algorithm = String.valueOf(Algorithm.SelectionSort);
	}

	/**
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.
	 */
	@Override
	public void sort() {
		int n = points.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (points[j].compareTo(points[minIndex]) < 0) {
					minIndex = j;
				}
			}
			swap(i, minIndex);
		}
	}

	// Other private methods if needed ...
}
