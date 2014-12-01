import java.io.*;
import java.util.*;

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
	int rows, cols;
	long seed = System.currentTimeMillis();
	boolean cheat = false;
	switch (Math.min(args.length, 4)) {
	case 4:
	    cheat = args[3].equals("1");
	case 3:
	    seed = Long.parseLong(args[2]);
	case 2:
	    rows = Integer.parseInt(args[0]);
	    cols = Integer.parseInt(args[1]);
	    break;
	case 1:
	    rows = Integer.parseInt(args[0]);
	    cols = rows;
	    break;
	default:
	    System.out.println("Usage: java Driver rows [cols [seed [cheat]]]");
	    return;
	}
	List<String> wordList = readWords("akame-words.txt");
	WordGrid grid = new WordGrid(rows, cols, seed);
	grid.add(wordList, 45);
	grid.fillRest(cheat);
	
	System.out.println(grid.words().size() + " words");
	System.out.println(grid.wordsInPuzzle());
	System.out.println();
	System.out.println(grid);
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

}
