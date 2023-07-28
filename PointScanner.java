package edu.iastate.cs228.hw1;

/**
 *
 @author Esha Gadgil
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException("Invalid input array.");
		}
		points = Arrays.copyOf(pts, pts.length);
		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file.
	 *
	 * @param  inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		File inputFile = new File(inputFileName);
		Scanner scanner = new Scanner(inputFile);

		ArrayList<Point> pointList = new ArrayList<>();
		while (scanner.hasNextInt()) {
			int x = scanner.nextInt();
			if (!scanner.hasNextInt()) {
				throw new InputMismatchException("Input file contains an odd number of integers.");
			}
			int y = scanner.nextInt();
			Point point = new Point(x, y);
			pointList.add(point);
		}

		scanner.close();

		if (pointList.isEmpty()) {
			throw new IllegalArgumentException("Input file is empty.");
		}

		points = pointList.toArray(new Point[0]);
		sortingAlgorithm = algo;
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate.
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter;

		// Create an object to be referenced by aSorter according to sortingAlgorithm.
		switch (sortingAlgorithm) {
			case SelectionSort:
				aSorter = new SelectionSorter(points.clone());
				break;
			case InsertionSort:
				aSorter = new InsertionSorter(points.clone());
				break;
			case MergeSort:
				aSorter = new MergeSorter(points.clone());
				break;
			case QuickSort:
				aSorter = new QuickSorter(points.clone());
				break;
			default:
				throw new IllegalArgumentException("Invalid sorting algorithm.");
		}

		// Sort by x-coordinate
		aSorter.setComparator(0);
		long startTime = System.nanoTime();
		aSorter.sort();
		long xSortTime = System.nanoTime() - startTime;

		// Get median x-coordinate
		int medianIndex = points.length / 2;
		int medianX = points[medianIndex].getX();

		// Sort by y-coordinate
		aSorter.setComparator(1);
		startTime = System.nanoTime();
		aSorter.sort();
		long ySortTime = System.nanoTime() - startTime;

		// Get median y-coordinate
		medianIndex = points.length / 2;
		int medianY = points[medianIndex].getY();

		// Construct medianCoordinatePoint using the obtained median x- and y-coordinates
		medianCoordinatePoint = new Point(medianX, medianY);

		// Set the scan time as the sum of the two sorting times
		scanTime = xSortTime + ySortTime;
	}


	/**
	 * Outputs performance statistics in the format:
	 *
	 * <sorting algorithm> <size>  <time>
	 *
	 * For instance,
	 *
	 * selection sort   1000	  9200867
	 *
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {
		String algorithmName = sortingAlgorithm.toString();
		int size = points.length;
		String time = Long.toString(scanTime);

		return String.format("%-16s %d %s", algorithmName, size, time);
	}

	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space
	 * in between.
	 */
	@Override
	public String toString() {
		int x = medianCoordinatePoint.getX();
		int y = medianCoordinatePoint.getY();

		return "MCP: (" + x + ", " + y + ")";
	}

	/**
	 * This method, called after scanning, writes point data into a file by outputFileName. The format
	 * of data in the file is the same as printed out from toString().  The file can help you verify
	 * the full correctness of a sorting result and debug the underlying algorithm.
	 *
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException {
		String outputFileName = "output.txt";
		PrintWriter writer = new PrintWriter(outputFileName);

		writer.println(toString());

		writer.close();
	}



}
