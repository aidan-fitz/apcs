import java.util.ArrayList;

/**
 * Describes how I feel every day
 */
class Listless {

    static void collapseDuplicates(ArrayList list) {
	for (int i = 0; i < list.size() - 1; i++) {
	    while (i < list.size() - 1 && list.get(i).equals(list.get(i+1)) ) {
		list.remove(i+1);
		// diagnostic print
		// System.out.println(list);
	    }
	}
    }

    public static void main(String[] cheese) {
	ArrayList<String> yolo = new ArrayList<String>();
	for (String s: cheese)
	    yolo.add(s);
	System.out.println(yolo);
	collapseDuplicates(yolo);
	System.out.println(yolo);
    }
}
