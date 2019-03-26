
public class IdVar extends Var{
	String id;
	
	IdVar(String i) {
		id = i;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <id var>" + id);
		System.out.println(indent1 + indent1.length() + " <id var>" + id);
	}
}
