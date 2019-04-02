
public class ReturnVal extends Var{

	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " returnVal");
	}
}
