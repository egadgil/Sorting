package edu.iastate.cs228.hw1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

public class Test1 {
    Point[] points;
    static Boolean sorted;

    public static void mergeSortMCP() {
 Random rand = new Random(100);
Point[] points = new Point[20];
sorted = true;
for (int i = 0; i < points.length; i++) {
 points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
}
 Point.xORy = true;
MergeSorter mergeSorter = new MergeSorter(points);
mergeSorter.sort();
int x = mergeSorter.getMedian().getX();
Point.xORy = false;
mergeSorter = new MergeSorter(points);
mergeSorter.sort();
int y = mergeSorter.getMedian().getY();
System.out.println(x);
assertEquals(x, 6);
 assertEquals(y, 11);
}



    public static void main(String[] args){
        mergeSortMCP();
    }

}


