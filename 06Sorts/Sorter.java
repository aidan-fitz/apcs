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

    public static void radixSort(int[] array) {
	ArrayList<ArrayList> buckets = new ArrayList<ArrayList>();
	for (int i = 0; i < 10; i++) {
	    buckets.add(new ArrayList<Integer>());
	}
	int loops = maxNumDigits(array) + 1;
	for (int i = 0; i < loops; i++) {
	    
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