class MonsterTrucker {
    public static void main(String[] args) {
	String[] numbers = new String[1 << 19];
	for (int i = 0; i < numbers.length; i++) {
	    numbers[i] = String.valueOf(i);
	}
	System.out.println(numbers.length + " items");
	// Insertion sort
	System.out.print("Insertion sort: ");
	OrderedSuperArray insertionSort = new OrderedSuperArray(numbers.length);
	long time = System.currentTimeMillis();
	for (String i: numbers) {
	    insertionSort.add(i);
	}
	time = System.currentTimeMillis() - time;
	System.out.println(time + " ms");
	// Selection sort
	System.out.print("Selection sort: ");
	SuperArray selectionSort = new SuperArray(numbers.length);
	for (String i: numbers) {
	    selectionSort.add(i);
	}
	time = System.currentTimeMillis();
	selectionSort.selectionSort();
	time = System.currentTimeMillis() - time;
	System.out.println(time + " ms");
	// Arrays.sort()
	time = System.currentTimeMillis();
	java.util.Arrays.sort(numbers);
	time = System.currentTimeMillis() - time;
	System.out.println(time + " ms");

    }
}