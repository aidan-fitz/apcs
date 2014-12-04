public class SuperArray {

    protected String[] contents;
    protected int size;

    public SuperArray() {
	this(10);
    }

    public SuperArray(int capacity) {
	contents = new String[capacity];
	size = 0;
    }

    public String toString() {
	StringBuilder yolo = new StringBuilder("[ ");
	for (int i = 0; i < size; i++)
	    yolo.append(contents[i]).append(' ');
	yolo.append(']');
	return yolo.toString();
    }

    public void add(String e) {
	add(size, e);
    }

    public void add(int index, String e) {
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

    public void shift(int beginIndex, int endIndex, int amount) {
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
	String[] newArray = new String[capacity];
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

    public String get(int index) {
	if (index < size && index >= 0)
	    return contents[index];
	else if (index >= -size) // Python-style negative indices
	    return contents[size + index];
	else
	    throw new ArrayIndexOutOfBoundsException(index);
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

    public String remove(int index) {
	if (index < size) {
	    String old = contents[index];
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

    public void badInsertionSort() {
        OrderedSuperArray c = new OrderedSuperArray();
        while( this.size() > 0){ 
            c.add(this.remove(0));
        }
        while(c.size() > 0){
            this.add(c.remove(0));
        }
    }

    public void insertionSort() {

    }

}
