package edu.iastate.cs228.hw1;

/**
 *
 @author Esha Gadgil
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{
		Random rand = new Random();

		// Conduct multiple rounds of comparison of four sorting algorithms
		for (int round = 1; round <= 4; round++) {
			System.out.println("Round " + round + ":");
			Point[] points = generateRandomPoints(10, rand); // Generate 10 random points

			// Initialize the array of scanners
			PointScanner[] scanners = new PointScanner[4];
			scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
			scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
			scanners[2] = new PointScanner(points, Algorithm.MergeSort);
			scanners[3] = new PointScanner(points, Algorithm.QuickSort);

			// Iterate through the array of scanners and call the scan method
			for (PointScanner scanner : scanners) {
				scanner.scan();
			}

			// Print the statistics table
			System.out.println("Algorithm     | # of Points | Distance to Median");
			System.out.println("--------------------------------------");
			for (PointScanner scanner : scanners) {
				System.out.println(scanner.stats());
			}
			System.out.println();
		}
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		if (numPts < 1) {
			throw new IllegalArgumentException("Number of points must be greater than or equal to 1.");
		}

		Point[] points = new Point[numPts];
		for (int i = 0; i < numPts; i++) {
			int x = rand.nextInt(101) - 50; // Generates a random number between -50 and 50 (inclusive)
			int y = rand.nextInt(101) - 50; // Generates a random number between -50 and 50 (inclusive)
			points[i] = new Point(x, y);
		}

		return points;
	}
	
}
