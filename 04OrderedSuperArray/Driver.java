class Driver {
    public static void main(String[] arrrgh) {
	OrderedSuperArray<String> words = new OrderedSuperArray<String>();
	for (String s: arrrgh)
	    words.add(s);
	System.out.println(words);
    }
}