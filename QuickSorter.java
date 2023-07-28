package edu.iastate.cs228.hw1;

/**
 * This class implements the version of the quicksort algorithm presented in the lecture.
 * @author Esha Gadgil
 */
public class QuickSorter extends AbstractSorter {
	// Other private instance variables if needed

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor, and also
	 * sets the instance variable algorithm in the superclass.
	 *
	 * @param pts input array of points
	 */
	public QuickSorter(Point[] pts) {
		super(pts);
		algorithm = String.valueOf(Algorithm.QuickSort);
	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.
	 */
	@Override
	public void sort() {
		quickSortRec(0, points.length - 1);
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 *
	 * @param first starting index of the subarray
	 * @param last  ending index of the subarray
	 */
	private void quickSortRec(int first, int last) {
		if (first < last) {
			int pivotIndex = partition(first, last);
			quickSortRec(first, pivotIndex - 1);
			quickSortRec(pivotIndex + 1, last);
		}
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 *
	 * @param first starting index of the subarray
	 * @param last  ending index of the subarray
	 * @return the pivot index
	 */
	private int partition(int first, int last) {
		Point pivot = points[last];
		int i = first - 1;

		for (int j = first; j < last; j++) {
			if (points[j].compareTo(pivot) <= 0) {
				i++;
				swap(i, j);
			}
		}

		swap(i + 1, last);
		return i + 1;
	}

	// Other private methods if needed ...
}
