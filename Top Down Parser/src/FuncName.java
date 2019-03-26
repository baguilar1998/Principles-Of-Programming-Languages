
public class FuncName {
	String id;
	
	FuncName(String i) {
		id = i;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <func name> " + id);
		System.out.println(indent + indent.length() + " <func name> " + id);
	}
}
