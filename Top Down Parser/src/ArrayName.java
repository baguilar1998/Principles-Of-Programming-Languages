
public class ArrayName {
	String id;
	
	ArrayName(String i) {
		id = i;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <array name> " + id);
		System.out.println(indent + indent.length() + " <array name> " + id);
	}
}
