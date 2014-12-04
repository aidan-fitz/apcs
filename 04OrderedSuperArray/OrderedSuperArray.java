public class OrderedSuperArray extends SuperArray {

    public OrderedSuperArray() {
	super();
    }

    public OrderedSuperArray(int capacity) {
	super(capacity);
    }

    public void add(String e) {
	if (size == 0) {
	    contents[0] = e;
	    size++;
	}
	else
	    super.add(index(e, 0, size), e);
    }

    public void add(int index, String e) {
	add(e);
    }

    public String set(int index, String e) {
	if (index < size) {
	    String old = contents[index];
	    contents[index] = e;
	    return old;
	}
	else {
	    throw new ArrayIndexOutOfBoundsException(index);
	}
    }

    // Implements a recursive binary search
    private int index(String e, int lower, int upper) {
	String cast = e;
	if (cast.compareTo(contents[lower]) <= 0)
	    return lower;
	if (cast.compareTo(contents[upper - 1]) >= 0)
	    return upper;
	int pivot = (lower + upper) / 2,
	    comparison = cast.compareTo(contents[pivot]);
	if (comparison < 0)
	    return index(e, lower, pivot);
	else
	    return index(e, pivot, upper);
    }

}
