public class OrderedSuperArray<E extends Comparable<? super E>> {

    private Comparable[] contents;
    private int size;

    public OrderedSuperArray() {
	this(10);
    }

    public OrderedSuperArray(int capacity) {
	contents = new Comparable[capacity];
	size = 0;
    }

    public String toString() {
	StringBuilder yolo = new StringBuilder("[ ");
	for (int i = 0; i < size; i++)
	    yolo.append(contents[i]).append(' ');
	yolo.append(']');
	return yolo.toString();
    }

    public E get(int index) {
	if (index < size && index >= 0)
	    return contents[index];
	else if (index >= -size) // Python-style negative indices
	    return contents[size + index];
	else
	    throw new ArrayIndexOutOfBoundsException(index);
    }

    public void add(E e) {
	add(index(e, 0, size), e);
    }

    public E set(int index, E e) {
	if (index < size) {
	    E old = contents[index];
	    contents[index] = e;
	    return old;
	}
	else {
	    throw new ArrayIndexOutOfBoundsException(index);
	}
    }

    public E remove(int index) {
	if (index < size) {
	    E old = contents[index];
	    shift(index, size - 1, -1);
	    size--;
	    if (size < contents.length >> 2)
		shrink();
	    return old;
	}
	else {
	    throw new ArrayIndexOutOfBoundsException(index);
	}
    }

    // Implements a recursive binary search
    private int index(E e, int lower, int upper) {
	if (e.compareTo(contents[lower]) <= 0)
	    return lower;
	if (e.compareTo(contents[upper]) >= 0)
	    return upper;
	int pivot = (lower + upper) / 2,
	    comparison = e.compareTo(contents[pivot]);
	if (comparison < 0)
	    return index(e, lower, pivot);
	else
	    return index(e, pivot, upper);
    }

    // use with index(E,int,int)
    private void add(int index, E e) {
	if (size < contents.length) {
	    if (index >= size) {
		contents[index] = e;
		size = index + 1;
	    }
	    else {
		shift(index, size - 1, 1);
		contents[index] = e;
		size++;
	    }
	}
	else {
	    grow();
	    add(e);
	}
    }

    private void shift(int beginIndex, int endIndex, int amount) {
	// base case: nothing to shift
	if (beginIndex >= endIndex) {
	    return;
	}
	// make room if needed
	if (endIndex + amount >= contents.length) {
	    grow();
	    shift(beginIndex, endIndex, amount);
	}
	// start from endIndex, then recurse to beginIndex
	else if (contents[beginIndex] != null) {
	    contents[endIndex + amount] = contents[endIndex];
	    shift(beginIndex, endIndex - 1, amount);
	}
    }

    public int size() {
	return size;
    }

    private void resize(int capacity) {
	Comparable[] newArray = new E[capacity];
	int newSize = Math.min(size, capacity);
	System.arraycopy(contents, 0, newArray, 0, newSize);
	contents = newArray;
	size = newSize;
    }
    private void grow() {
	resize(contents.length << 1);
    }
    private void shrink() {
	if (contents.length > 5) {
	    resize(contents.length >> 1);
	}
    }

    public void clear() {
	for (int i = 0; i < contents.length; i++)
	    contents[i] = null;
	size = 0;
    }

}
