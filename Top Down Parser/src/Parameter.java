
public class Parameter {
	String id;
	
	Parameter(String i) {
		id = i;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <parameter> " +id);
		System.out.println(indent + indent.length() + " <parameter> " +id);
	}
	
}
