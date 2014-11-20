public class WordGrid {
    private char[][] data;

    /**
     * Initialize the grid to the size specified and fill all of the positions
     * with spaces.
     * @param rows the starting height of the WordGrid
     * @param cols the starting width of the WordGrid
     */
    public WordGrid(int rows,int cols) {
	data = new char[rows][cols];
	clear();
    }

    /**Set all values in the WordGrid to spaces ' '*/
    private void clear() {
	for (char[] row: data) {
	    for (int col = 0; col < row.length; col++) {
		row[col] = ' ';
	    }
	}
    }

    /**The proper formatting for a WordGrid is created in the toString.
     *@return a String with each character separated by spaces, and each row
     *separated by newlines.
     */
    public String toString() {
	StringBuilder swag = new StringBuilder();
	for (char[] row: data) {
	    for (char cell: row) {
		swag.append(cell).append(' ');
	    }
	    // Replace the last space with two line breaks
	    swag.replace(swag.length() - 1, swag.length(), "\n\n");
	}
	// Delete the two newlines at the end
	swag.delete(swag.length() - 2, swag.length());
	// ok, swag
	return swag.toString();
    }

    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     */
    public boolean addWordHorizontal(String word,int row, int col){
    }

    /**
     * Checks that a word will fit into the grid starting at the specified {@code row} and {@code col} and going in the specified {@code direction}.
     * The {@code direction} is an integer that specifies one of eight directions in the grid.
     * The directions go clockwise from 0, which is straight up, to 7, which is diagonally up and to the left.
     *
     * @param word the word to be checked
     * @param row the vertical coordinate of the starting position
     * @param col the horizontal coordinate of the starting position
     * @param direction an integer from 0 to 7 that specifies the direction in which to add letters
     * @return {@code true} if the word can be inserted here, {@code false} otherwise
     */
    public boolean wordFits(String word, int row, int col, int direction) {
	try {
	    for (int index = 0; index < word.length; index++) {
		char inGrid = charAt(index, row, col, direction);
    		if (inGrid != ' ' && inGrid != word.charAt(index))) {
		    // not a space and not equal to the corresponding character
		    // in the word
		    return false;
		}
	    }
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    // went past the boundaries of the grid, so the word doesn't fit
	    return false;
	}
	// match
        return true;
    }

    private char charAt(int index, int row, int col, int direction) {
	return data[row + rowIncr(direction)][col + colIncr(direction)];
    }
    private int rowIncr(int direction) {
	switch (direction) {
	case 7: case 0: case 1:  return -1; // go up
	case 6: /*mid*/ case 2:  return 0;
	case 5: case 4: case 3:  return 1; // go down
	default:
	    throw new IllegalArgumentException("direction must be in [0, 8)");
	}
    }
    private int colIncr(int direction) {
	switch (direction) {
	case 1: case 2: case 3:  return 1; // go right
	case 0: /*mid*/ case 4:  return 0;
	case 7: case 6: case 5:  return -1; // go left
	default:
	    throw new IllegalArgumentException("direction must be in [0, 8)");
	}
    }

    //vertical + diagonal should be implemented as well.

}