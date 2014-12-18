import java.util.*;

public class Sorter {

    public static String name() {
	return "Fitzgerald,Aidan";
    }
    public static int period() {
	return 6;
    }

    private static void swap(int[] array, int i0, int i1) {
	int j = i0;
	i0 = i1;
	i1 = j;
    }

    public static void bubbleSort(int[] array) {
	int start = 0, end = array.length;
	while (bubbleSortPass(array, start, end)) {
	    end--;
	}
    }

    public static void dubbleBubbleSort(int[] array) {

    }

    /**
     * @return true if a swap was made, false otherwise
     */
    private static boolean bubbleSortPass(int[] array, int start, int end) {
	boolean yolo = false;
	for (int i = start; i < end - 1; i++) {
	    if (array[i] > array[i+1]) {
		swap(array, i, i+1);
		yolo = true;
	    }
	}
	return yolo;
    }

    public static void insertionSort(int[] array) {
	for (int i = 1; i < array.length; i++) {
	    int swag = array[i];
	    // shift array[?:i-1] to array[?:i]
	    int j = i;
	    while (j > 0 && array[j-1] > swag) {
		array[j] = array[j-1];
		j--;
	    }
	    array[j] = swag;
	}
    }

    public static void selectionSort(int[] array) {
	for (int i = 0; i < array.length; i++) {
	    swap(array, i, smallest(array, i));
	}
    }

    /**
     * @return the index of the smallest value in array[start:]
     */
    private static int smallest(int[] array, int start) {
	int min = start;
	for (int i = start; i < array.length; i++) {
	    if (array[i] < array[min]) {
		min = i;
	    }
	}
	return min;
    }

    public static void radixSort(int[] array) {
	ArrayList<ArrayList> buckets = new ArrayList<ArrayList>();
	// [-9, 9]
	for (int i = 0; i < 19; i++) {
	    buckets.add(new ArrayList<Integer>());
	}
	int loops = maxNumDigits(array);// + 1;
	for (int place = 0; place < loops; place++) {
	    for (int n: array) {
		buckets.get(9 + digit(n, place)).add(n);
	    }
	    int index = 0;
	    for (ArrayList<Integer> bucket: buckets) {
		while (bucket.size() > 0) {
		    array[index] = bucket.remove(0).intValue();
		    index++;
		}
	    }
	}
    }

    private static int digit(int n, int place) {
	return digit(n, place, 10);
    }

    private static int digit(int n, int place, int radix) {
	for (int i = 0; i < place; i++) {
	    n /= radix;
	}
	return n % radix;
    }

    private static int maxNumDigits(int[] A) {
	int yolo = 0;
	for (int n: A) {
	    int i = numDigits(n);
	    if (i > yolo) {
		yolo = i;
	    }
	}
	return yolo;
    }

    private static int numDigits(int n) {
	int i = 0;
	for (; n != 0; i++) {
	    n /= 10;
	}
	return i;
    }

}
