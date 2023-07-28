package edu.iastate.cs228.hw1;

/**
 * This class implements the mergesort algorithm.
 * @author Esha Gadgil
 */
public class MergeSorter extends AbstractSorter {
	// Other private instance variables if needed

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor, and also
	 * sets the instance variable algorithm in the superclass.
	 *
	 * @param pts input array of points
	 */
	public MergeSorter(Point[] pts) {
		super(pts);
		algorithm = String.valueOf(Algorithm.MergeSort);
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 */
	@Override
	public void sort() {
		mergeSortRec(points);
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points.
	 * One way is to make copies of the two halves of pts[], recursively call mergeSort on them,
	 * and merge the two sorted subarrays into pts[].
	 *
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts) {
		if (pts.length <= 1) {
			return;
		}

		int mid = pts.length / 2;
		Point[] leftArray = new Point[mid];
		Point[] rightArray = new Point[pts.length - mid];

		System.arraycopy(pts, 0, leftArray, 0, mid);
		System.arraycopy(pts, mid, rightArray, 0, pts.length - mid);

		mergeSortRec(leftArray);
		mergeSortRec(rightArray);

		merge(pts, leftArray, rightArray);
	}

	/**
	 * Merge the two sorted subarrays leftArray and rightArray into the original array pts[].
	 *
	 * @param pts        original point array
	 * @param leftArray  left subarray
	 * @param rightArray right subarray
	 */
	private void merge(Point[] pts, Point[] leftArray, Point[] rightArray) {
		int leftSize = leftArray.length;
		int rightSize = rightArray.length;
		int i = 0, j = 0, k = 0;

		while (i < leftSize && j < rightSize) {
			if (leftArray[i].compareTo(rightArray[j]) <= 0) {
				pts[k++] = leftArray[i++];
			} else {
				pts[k++] = rightArray[j++];
			}
		}

		while (i < leftSize) {
			pts[k++] = leftArray[i++];
		}

		while (j < rightSize) {
			pts[k++] = rightArray[j++];
		}
	}

	// Other private methods if needed ...
}



