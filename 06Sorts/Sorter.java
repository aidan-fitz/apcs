public class Sorter {

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

}