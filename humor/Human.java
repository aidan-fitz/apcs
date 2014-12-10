/**
 * Objectifying human beings
 */
public class Human implements Comparable<Human> {

    /**
     * Oh yeah, all humans are supposed to be equal
     */
    public boolean equals(Human other) {
	return true;
    }

    /**
     * I compare myself to a {@code Human}.
     */
    public int compareTo(Human other) {
	if (this == other)
	    return -1; // I hate myself
	else
	    // return this.money - other.money;
	    return 1; // I'm better than you
    }

}