
public class Assignment extends Statement {

	Var var;
	RightSide rightSide;
	
	Assignment(Var v, RightSide rs) {
		var = v;
		rightSide = rs;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <assignment>");
		String indent2 = indent1 + " ";
		var.printParseTree(indent2);
		IO.displayln(indent2 + indent2.length() + " =");
		rightSide.printParseTree(indent2);
	}
}
