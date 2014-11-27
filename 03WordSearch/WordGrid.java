import java.io.*;
import java.util.*;

public class WordGrid {

    private char[][] data;
    private Random rand;
    private List<String> wordList;

    /**
     * Initializes the grid to the size specified and fill all of the positions
     * with spaces.
     * @param rows the starting height of the WordGrid
     * @param cols the starting width of the WordGrid
     */
    public WordGrid(int rows, int cols) {
	data = new char[rows][cols];
	clear();
	rand = new Random();
	// Cheating by using a LinkedList because people are adding to the end
	wordList = new LinkedList<String>();
    }

    /**
     * Initializes the grid to the size and random seed specified.
     */
    public WordGrid(int rows, int cols, long seed) {
	this(rows, cols);
	setSeed(seed);
    }

    public void setSeed(long seed) {
	rand.setSeed(seed);
    }

    /** Set all values in the WordGrid to spaces ' ' */
    private void clear() {
	for (char[] row: data) {
	    for (int col = 0; col < row.length; col++) {
		row[col] = ' ';
	    }
	}
    }

    /**
     * @return a string with columns separated by spaces and rows separated by newlines.
     */
    public String toString() {
	StringBuilder swag = new StringBuilder();
	for (char[] row: data) {
	    for (char cell: row) {
		swag.append(cell).append(' ');
	    }
	    // Replace the last space with a line break
	    swag.replace(swag.length() - 1, swag.length(), "\n");
	}
	// ok, swag
	return swag.toString();
    }

    /**
     * Attempts to add a given word to the grid starting at the specified {@code row} and {@code col} and going in the specified {@code direction}.
     * The {@code direction} is an integer that specifies one of eight directions in the grid.
     * The directions go clockwise from 0, which is straight up, to 7, which is up and to the left.
     *
     * @param word the word to be checked
     * @param row the vertical coordinate of the starting position
     * @param col the horizontal coordinate of the starting position
     * @param direction an integer from 0 to 7 that specifies the direction in which to add letters
     * @return {@code true} if the word can be inserted here, {@code false} otherwise
     */
    public boolean add(String word, int row, int col, int direction) {
	try {
	    for (int index = 0; index < word.length(); index++) {
		char inGrid = charAt(index, row, col, direction);
    		if (inGrid != ' ' && inGrid != word.charAt(index)) {
		    // not a space and not equal to the corresponding character
		    // in the word
		    return false;
		}
	    }
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    // went past the boundaries of the grid
	    return false;
	}
	// match - eat it, just eat it
	for (int index = 0; index < word.length(); index++) {
	    addChar(word.charAt(index), index, row, col, direction);
	}
	return true;
    }

    private char charAt(int index, int row, int col, int direction) {
	return data[row + index*rowIncr(direction)][col + index*colIncr(direction)];
    }

    /* WARNING: Does not check first. Be sure you won't f*** up the grid. */
    private void addChar(char c, int index, int row, int col, int direction) {
	data[row + index*rowIncr(direction)][col + index*colIncr(direction)] = c;
    }

    /* +1 is down, -1 is up */
    private int rowIncr(int direction) {
	switch (direction) {
	case 7: case 0: case 1:  return -1; // go up
	case 6: /*mid*/ case 2:  return 0;
	case 5: case 4: case 3:  return 1; // go down
	default:
	    throw new IllegalArgumentException("direction must be in [0, 8)");
	}
    }

    /* +1 is right, -1 is left */
    private int colIncr(int direction) {
	switch (direction) {
	case 1: case 2: case 3:  return 1; // go right
	case 0: /*mid*/ case 4:  return 0;
	case 7: case 6: case 5:  return -1; // go left
	default:
	    throw new IllegalArgumentException("direction must be in [0, 8)");
	}
    }

    public static List<String> readWords(String filename)
	throws FileNotFoundException {

	Scanner scan = new Scanner(new File(filename));
	List<String> wordList = new ArrayList<String>();

	while (scan.hasNextLine()) {
	    String line = scan.nextLine();
	    if (line.length() > 0) {
		wordList.add(line.toUpperCase().trim());
	    }
	}

	return wordList;
    }

    public WordGrid(int rows, int cols, List<String> wordList, long seed, boolean cheat) {
	this(rows, cols, seed);

	for (String i: wordList) {
	    add(i);
	}

	if (!cheat)
	    fillRest();
    }

    /**
     * Attempts, up to four times, to add the given word to a random position going in a random direction.
     * @return {@literal true} if the word was added successfully, {@literal false} otherwise
     */
    public boolean add(String word) {
	for (int i = 0; i < 4; i++) {
	    int row = rand.nextInt(data.length), // rows
		col = rand.nextInt(data[0].length), // cols
		direction = rand.nextInt(8);
	    if (add(word, row, col, direction)) {
		wordList.add(word);
		return true;
	    }
	}
	return false;
    }

    public List<String> words() {
	return wordList;
    }

    public String wordsInPuzzle() {
	return wordList.toString();
    }

    public void fillRest() {
	// TODO stub
    }
}
