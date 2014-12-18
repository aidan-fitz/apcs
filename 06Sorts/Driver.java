import java.util.*;

public class Driver {

    public static void main(String[] arrgh) {
	int[] orig = randomArray(25);
	int[] copy;
	long ti, tf, dt;

	copy = Arrays.copyOf(orig, orig.length);
	System.out.print("Bubble sort: ");
	ti = System.currentTimeMillis();
	Sorter.bubbleSort(copy);
	tf = System.currentTimeMillis();
	dt = tf - ti;
	System.out.println(dt + " ms");
    }

    private Random rng = new Random();

    private int[] randomArray(int length) {
	int[] yolo = new int[length];
	for (int i = 0; i < length; i++) {
	    yolo[i] = rng.nextInt();
	}
	return yolo;
    }
}