import java.util.*;

public class Driver {

    public static void main(String[] arrgh) {
	int size = 25000;
	if (arrgh.length > 0) {
	    size = Integer.parseInt(arrgh[0]);
	}
	else {
	    System.out.println("Usage: java Driver numberOfElements");
	}

	int[] orig = randomArray(size);
	System.out.println(size + " elements");

	int[] copy, ref;
	long ti, tf, dt;

	ref = Arrays.copyOf(orig, orig.length);
	System.out.print("Arrays.sort: ");
	ti = System.currentTimeMillis();
	Arrays.sort(ref);
	tf = System.currentTimeMillis();
	dt = tf - ti;
	System.out.println(dt + " ms (reference implementation)");

	copy = Arrays.copyOf(orig, orig.length);
	System.out.print("Bubble sort: ");
	ti = System.currentTimeMillis();
	Sorter.bubbleSort(copy);
	tf = System.currentTimeMillis();
	if (Arrays.equals(ref, copy)) {
	    System.out.print("PASS, ");
	}
	else {
	    System.out.print("FAIL, ");
	}
	dt = tf - ti;
	System.out.println(dt + " ms");

	System.arraycopy(orig, 0, copy, 0, orig.length);
	System.out.print("Insertion sort: ");
	ti = System.currentTimeMillis();
	Sorter.insertionSort(copy);
	tf = System.currentTimeMillis();
	if (Arrays.equals(ref, copy)) {
	    System.out.print("PASS, ");
	}
	else {
	    System.out.print("FAIL, ");
	}
	dt = tf - ti;
	System.out.println(dt + " ms");

	System.arraycopy(orig, 0, copy, 0, orig.length);
	System.out.print("Selection sort: ");
	ti = System.currentTimeMillis();
	Sorter.selectionSort(copy);
	tf = System.currentTimeMillis();
	if (Arrays.equals(ref, copy)) {
	    System.out.print("PASS, ");
	}
	else {
	    System.out.print("FAIL, ");
	}
	dt = tf - ti;
	System.out.println(dt + " ms");


    }

    private static Random rng = new Random();

    private static int[] randomArray(int length) {
	int[] yolo = new int[length];
	for (int i = 0; i < length; i++) {
	    yolo[i] = rng.nextInt();
	}
	return yolo;
    }
}
