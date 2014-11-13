import java.util.*;

class ArrayListMethods {

    public static void collapseDuplicates(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
	        while (i < list.size() - 1 && list.get(i).equals(list.get(i+1)) ) {
		        list.remove(i+1);
	        }
	    }
    }
    
    Random izer = new Random();
    
    public static void randomize(List list) {
        for (int i = 0; i < list.size(); i++) {
            list.add(izer.nextInt(list.size()), list.get(0));
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
